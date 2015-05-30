package edu.chl.blastinthepast.model.level;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.projectiles.Projectile;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.Magnum;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.chest.*;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import edu.chl.blastinthepast.model.position.Position;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel extends Observable implements PropertyChangeListener {

    private Player player;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<CharacterI> characterIs;
    private Chest chest;
    private boolean isPaused;
    private ArrayList<PowerUpI> activePowerUps =new ArrayList<PowerUpI>();
    private ArrayList<PowerUpI> powerUpDrops = new ArrayList<PowerUpI>();
    private ArrayList<AmmunitionInterface> ammunitionDrops = new ArrayList<AmmunitionInterface>();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private LevelInterface level;
    private boolean playerBlocked = false;

    public BPModel(LevelInterface level) {
        this.level = level;
        chest = new Chest(new Magnum(new Position(1000,1500), new Vector2(), new Vector2()));
        characterIs = new ArrayList<CharacterI>();
        player = level.getPlayer();
        newCharacter(player);
        enemies = level.getEnemies();
        for (Enemy e : enemies) {
            newCharacter(e);
        }
    }

    public void update(float dt){
        if (!isPaused) {
            removeProjectiles();
            removeDeadEnemies();
            if (enemies.size() < 1) {
                System.out.println("all enemies are dead");
                level.spawnNewEnemies();
                enemies = level.getEnemies();
                for (Enemy e : enemies) {
                    newCharacter(e);
                }
            }
            updatePowerUps();
            if (!playerBlocked && level.playerIsColliding()) {
                pcs.firePropertyChange("blocked", false, "");
                playerBlocked = true;
            } else if (playerBlocked && !level.playerIsColliding()) {
                pcs.firePropertyChange("unblocked", false, "");
                playerBlocked = false;
            }
            player.update(dt);
            for (ProjectileInterface p : projectiles) {
                p.move(dt);
            }
            for (Enemy e : enemies) {
                e.update(dt);
                int i = e.getMovementDirection();
                if (i == 0 && !(e.getPosition().getX() > 0)) {
                    e.setMovementDirection(1);
                } else if (i == 1 && !(e.getPosition().getX() < level.getMapWidth())) {
                    e.setMovementDirection(0);
                } else if (i == 2 && !(e.getPosition().getY() < level.getMapHeight())) {
                    e.setMovementDirection(3);
                } else if (i == 3 && !(e.getPosition().getY() > 0)) {
                    e.setMovementDirection(2);
                } else {
                    e.move(dt);
                }
            }
            checkForCollision();
        }
    }

    /**
     * Checks if a projectile is outside the map and if so, removes it
     */
    private void removeProjectiles() {
        for (CharacterI c: characterIs) {
            Iterator<ProjectileInterface> iter = c.getProjectiles().iterator();
            while (iter.hasNext()) {
                ProjectileInterface p = iter.next();
                if ((p.getPosition().getY() < 0) || (p.getPosition().getY() > level.getMapHeight()) ||
                        (p.getPosition().getX() > level.getMapWidth()) || (p.getPosition().getX() < 0)) {
                    iter.remove();
                    c.getProjectiles().remove(p);
                    projectiles.remove(p);
                    pcs.firePropertyChange("Remove Projectile", null, p);
                }
            }
        }
    }

    private void updatePowerUps(){
        Iterator<PowerUpI> powerUpIterator = activePowerUps.iterator();
        while(powerUpIterator.hasNext()){
            PowerUpI powerUp = powerUpIterator.next();
            powerUp.update();
            if (powerUp.getHasExpired()){
                powerUpIterator.remove();
            }
        }
    }

    private void removeDeadEnemies(){
        Iterator<Enemy> iter = enemies.iterator();
        while (iter.hasNext()){
            Enemy e = iter.next();
            if (e.getHealth() <= 0) {
                e.die();
                if(e.getCharacterType().getID().equals("Boss")) {
                    player.setScore(player.getScore() + 50);
                } else if (e.getCharacterType().getID().equals("Pleb")) {
                    player.setScore(player.getScore() + 10);
                }
                iter.remove();
                characterIs.remove(e);
                enemies.remove(e);
                pcs.firePropertyChange("Remove Character", null, e);
            }
        }
    }

    public void checkForCollision(){
        checkForCharacterCollision();
        checkForProjectileCollision();
        checkForAmmoCollision();
        checkForPowerUpCollision();
    }

    public void checkForCharacterCollision(){
        for (CharacterI c1 : characterIs){
            for (CharacterI c2 : characterIs){
                if (c1.isColliding(c2) && c1!=c2){
                    characterCollision(c1, c2);
                }
            }
        }
    }

    public void checkForProjectileCollision(){
        Iterator<ProjectileInterface> projIter = projectiles.iterator();
        while (projIter.hasNext()){
            ProjectileInterface projectile = projIter.next();
            for (CharacterI characterI : characterIs){
                if (characterI.isColliding(projectile) && !characterI.getProjectiles().contains(projectile)) {
                    characterI.setHealth(characterI.getHealth() - projectile.getDamage());
                    projIter.remove();
                    pcs.firePropertyChange("Remove Projectile", null, projectile);
                }
            }
        }
    }

    public void checkForAmmoCollision(){
        Iterator<AmmunitionInterface> ammoIter = ammunitionDrops.iterator();
        while (ammoIter.hasNext()) {
            AmmunitionInterface ammo = ammoIter.next();
            if (player.isColliding(ammo)) {
                player.getCurrentWeapon().addAmmo(ammo.getAmount());
                ammoIter.remove();
                pcs.firePropertyChange("Remove Ammunition", null, ammo);
            }
        }
    }

    public void checkForPowerUpCollision(){
        Iterator<PowerUpI> powerUpIter = powerUpDrops.iterator();
        if (powerUpIter.hasNext()) {
            PowerUpI powerUp = powerUpIter.next();
            if (player.isColliding(powerUp)) {
                powerUp.init(player);
                activePowerUps.add(powerUp);
                powerUpIter.remove();
                pcs.firePropertyChange("Remove PowerUp", null, powerUp);
            }
        }
    }

    private void characterCollision(CharacterI characterI1, CharacterI characterI2) {
        characterI1.setPosition(characterI1.getPrevPos());
        characterI2.setPosition(characterI2.getPrevPos());
    }

    public Player getPlayer(){
        return player;
    }

    public Chest getChest() {
        return chest;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void addProjectile(ProjectileInterface p) {
        projectiles.add(p);
    }

    public ArrayList<CharacterI> getCharacterIs(){
        return characterIs;
    }

    public void pause() {
        isPaused = true;
        setChanged();
    }

    public void unPause() {
        isPaused = false;
        setChanged();
    }

    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "New Projectile":
                ProjectileInterface p = (ProjectileInterface)evt.getNewValue();
                addProjectile(p);
                pcs.firePropertyChange("New Projectile", null, p);
                break;
            case "PowerUp drops":
                ArrayList<PowerUpI> powerUpArray = (ArrayList<PowerUpI>)evt.getNewValue();
                powerUpDrops.addAll(powerUpArray);
                for (PowerUpI powerUp : powerUpArray) {
                    pcs.firePropertyChange("New PowerUp", null, powerUp);
                }
                break;
            case "Ammunition drops":
                ArrayList<AmmunitionInterface> ammoArray = (ArrayList<AmmunitionInterface>)evt.getNewValue();
                ammunitionDrops.addAll(ammoArray);
                for (AmmunitionInterface ammo : ammoArray) {
                    pcs.firePropertyChange("New Ammunition", null, ammo);
                }
                break;
            case "Player died":
                pcs.firePropertyChange(evt);
                break;
        }
    }

    public void newCharacter(CharacterI characterI){
        characterI.addListener(this);
        characterIs.add(characterI);
        pcs.firePropertyChange("New Character", null, characterI);
    }

    public ArrayList<ProjectileInterface> getProjectiles() {
        return projectiles;
    }

    public void addListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

}
