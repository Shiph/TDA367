package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.level.LevelInterface;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.Magnum;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.chest.*;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 *
 * BPModel is the class that ties the other model classes together to make a functioning game.
 *
 */
public class BPModel extends Observable implements PropertyChangeListener {

    private CharacterI player;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<CharacterI> enemies = new ArrayList<CharacterI>();
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
        characterIs = new ArrayList<CharacterI>();
        player = level.getPlayer();
        chest = new Chest(new Magnum(new Position(1000,1500), player.getAimVector(), player.getMovementVector()), new Position(1000,1500));
        newCharacter(player);
        enemies = level.getEnemies();
        for (CharacterI e : enemies) {
            newCharacter(e);
        }
    }

    /**
     * Updates and checks up on objects that need to constantly be updated.
     * Remove projectiles that have characters or are outside the map, dead enemies are removed from the list of enemies.
     * Applies power-ups to the player and updates the player and enemies, also checks for collisions.
     */
    public void update(float dt){
        if (!isPaused) {
            removeProjectiles();
            removeDeadEnemies();
            checkIfAllEnemiesDead();
            updatePowerUps();
            checkIfPlayerBlocked();
            player.update(dt);
            for (ProjectileInterface p : projectiles) {
                p.move(dt);
            }
            checkEnemyMapCollision(dt);
            checkForCollision();
        }
    }

    public void checkIfAllEnemiesDead() {
        if (enemies.size() < 1) {
            level.spawnNewEnemies();
            enemies = level.getEnemies();
            for (CharacterI e : enemies) {
                newCharacter(e);
            }
        }
    }

    /**
     *
     */
    public void checkIfPlayerBlocked() {
        if (!playerBlocked && level.playerIsColliding()) {
            pcs.firePropertyChange("blocked", false, "");
            playerBlocked = true;
        } else if (playerBlocked && !level.playerIsColliding()) {
            pcs.firePropertyChange("unblocked", false, "");
            playerBlocked = false;
        }
    }

    public void checkEnemyMapCollision(float dt) {
        for (CharacterI e : enemies) {
            e.update(dt);
            int i = ((Enemy)e).getMovementDirection();
            if (i == 0 && !(e.getPosition().getX() > 0)) {
                ((Enemy)e).setMovementDirection(1);
            } else if (i == 1 && !(e.getPosition().getX() < level.getMapWidth())) {
                ((Enemy)e).setMovementDirection(0);
            } else if (i == 2 && !(e.getPosition().getY() < level.getMapHeight())) {
                ((Enemy)e).setMovementDirection(3);
            } else if (i == 3 && !(e.getPosition().getY() > 0)) {
                ((Enemy)e).setMovementDirection(2);
            } else {
                e.move(dt);
            }
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

    /**
     * Applies active power-ups to the player and removes them if they've expired
     */
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
        Iterator<CharacterI> iter = enemies.iterator();
        while (iter.hasNext()){
            Enemy e = (Enemy)iter.next();
            if (e.getHealth() <= 0) {
                e.die();
                if(e.getCharacterType().getID().equals("Boss")) {
                    ((Player)player).setScore(((Player)player).getScore() + 50);
                } else if (e.getCharacterType().getID().equals("Pleb")) {
                    ((Player)player).setScore(((Player)player).getScore() + 10);
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

    /**
     * Checks if two characters are colliding and if sets the character positions to the
     * position they had the previous update cycle
     */
    public void checkForCharacterCollision(){
        for (CharacterI c1 : characterIs){
            for (CharacterI c2 : characterIs){
                if (c1.isColliding(c2) && c1!=c2){
                    characterCollision(c1, c2);
                }
            }
        }
    }

    /**
     * Checks if a character has been hit by a projectile. If the character that has collided with the projectile is the one
     * who fired it, the collision is ignored. Otherwise, the character takes damage from the projectile and the projectile
     * is then removed.
     */
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

    /**
     * Checks if the player is colliding with an ammunition object, if so the ammunition is added
     * to the weapon which uses the type of ammunition that the player has collided with.
     * The ammunition object is then removed.
     */
    public void checkForAmmoCollision(){
        Iterator<AmmunitionInterface> ammoIter = ammunitionDrops.iterator();
        while (ammoIter.hasNext()) {
            AmmunitionInterface ammo = ammoIter.next();
            if (player.isColliding(ammo)) {
                for (WeaponInterface w : player.getAllWeapons()) {
                    if (w.getNewProjectile().getProjectileType().getID().equals(ammo.getType().getProjectileType().getID())) {
                        w.addAmmo(ammo.getAmount());
                    }
                }
                ammoIter.remove();
                pcs.firePropertyChange("Remove Ammunition", null, ammo);
            }
        }
    }

    /**
     * Checks if the player is colliding with a power-up object. If so, the power-up is initialized and the power-up
     * is removed from the list of dropped power-ups and added to the list of active power-ups.
     */
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
        return ((Player)player);
    }

    public Chest getChest() {
        return chest;
    }

    public ArrayList<CharacterI> getEnemies(){
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
