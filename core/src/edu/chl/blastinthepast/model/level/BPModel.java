package edu.chl.blastinthepast.model.level;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.enemy.EnemyFactory;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.Magnum;
import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.chest.*;
import edu.chl.blastinthepast.model.player.Character;
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
    private EnemyFactory enemyFactory;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Character> characters;
    private Chest chest;
    private Boss boss;
    private boolean isPaused;
    private ArrayList<PowerUpI> activePowerUps =new ArrayList<PowerUpI>();
    private ArrayList<PowerUpI> powerUpDrops = new ArrayList<PowerUpI>();
    private ArrayList<AmmunitionInterface> ammunitionDrops = new ArrayList<AmmunitionInterface>();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private int mapHeight;
    private int mapWidth;

    public BPModel() {
        chest = new Chest(new Magnum(new Position(1000,1500), new Vector2(), new Vector2()));
        characters = new ArrayList<Character>();
        player = new Player(new Position(0, 0));
        newCharacter(player);
        enemyFactory = new EnemyFactory();
        spawnEnemies(5);
    }

    public void spawnBoss(Position pos) {
        boss = (Boss)enemyFactory.getEnemy(player, CharacterTypeEnum.BOSS);
        newEnemy(boss);
    }

    public void update(float dt){
        if (!isPaused) {
            removeProjectiles();
            removeDeadEnemies();
            if (enemies.size() < 1) {
                setChanged();
                notifyObservers("all enemies is kill");
            }
            updatePowerUps();
            player.update(dt);
            for (ProjectileInterface p : projectiles) {
                p.move(dt);
            }
            for (Enemy e : enemies) {
                e.update(dt);
                int i = e.getMovementDirection();
                if (i == 0 && !(e.getPosition().getX() > 0)) {
                    e.setMovementDirection(1);
                } else if (i == 1 && !(e.getPosition().getX() < mapWidth)) {
                    e.setMovementDirection(0);
                } else if (i == 2 && !(e.getPosition().getY() < mapHeight)) {
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
        for (Character c: characters) {
            Iterator<ProjectileInterface> iter = c.getProjectiles().iterator();
            while (iter.hasNext()) {
                ProjectileInterface p = iter.next();
                if ((p.getPosition().getY() < 0) || (p.getPosition().getY() > mapHeight) ||
                        (p.getPosition().getX() > mapWidth) || (p.getPosition().getX() < 0)) {
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
                characters.remove(e);
                enemies.remove(e);
                pcs.firePropertyChange("Remove Character", null, e);
            }
        }
        if (enemies.size()<1){
            spawnEnemies(5);
        }
    }

    public void spawnEnemies(int amount) {
        for (int i = 0; i < amount; i++) {
            Enemy e = enemyFactory.getEnemy(player, CharacterTypeEnum.PLEB);
            newEnemy(e);
            Random r = new Random();
            float x = r.nextFloat() * mapWidth;
            float y = r.nextFloat() * mapHeight;
            e.getPosition().setX(x);
            e.getPosition().setY(y);
        }
    }

    public void checkForCollision(){
        checkForCharacterCollision();
        checkForProjectileCollision();
        checkForAmmoCollision();
        checkForPowerUpCollision();
    }

    private void checkForCharacterCollision(){
        for (Character c1 : characters){
            for (Character c2 : characters){
                if (c1.isColliding(c2) && c1!=c2){
                    characterCollision(c1, c2);
                }
            }
        }
    }

    private void checkForProjectileCollision(){
        Iterator<ProjectileInterface> projIter = projectiles.iterator();
        while (projIter.hasNext()){
            ProjectileInterface projectile = projIter.next();
            for (Character character : characters){
                if (character.isColliding(projectile) && !character.getProjectiles().contains(projectile)) {
                    character.setHealth(character.getHealth() - projectile.getDamage());
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

    private void characterCollision(Character character1, Character character2) {
        character1.setPosition(character1.getPrevPos());
        character2.setPosition(character2.getPrevPos());
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

    public ArrayList<Character> getCharacters(){
        return characters;
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

    public void newCharacter(Character character){
        character.addListener(this);
        characters.add(character);
        pcs.firePropertyChange("New Character", null, character);
    }

    public void newEnemy(Enemy enemy){
        enemies.add(enemy);
        newCharacter(enemy);
    }

    public void addListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
