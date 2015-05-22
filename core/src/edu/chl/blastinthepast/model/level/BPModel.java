package edu.chl.blastinthepast.model.level;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.ammunition.Ammunition;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.enemy.EnemyFactory;
import edu.chl.blastinthepast.model.projectile.Projectile;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.Magnum;
import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.chest.*;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel extends Observable implements Observer, PropertyChangeListener {

    private Player player;
    private EnemyFactory enemyFactory;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private HashMap<String, ArrayList<Object>> dropList = new HashMap<String, ArrayList<Object>>();
    private ArrayList<Character> characters;
    private Boss boss;
    private Chest chest;
    private boolean isPaused;
    private ArrayList<PowerUpI> powerUps=new ArrayList<PowerUpI>();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private ArrayList<PowerUpI> powerUpDrops = new ArrayList<PowerUpI>();
    private ArrayList<AmmunitionInterface> ammunitionDrops = new ArrayList<AmmunitionInterface>();


    public BPModel() {
        chest = new Chest(new Magnum(new Position(1000,1500), new Vector2()));
        characters = new ArrayList<Character>();
        player = new Player();
        enemyFactory = new EnemyFactory();
        setChanged();
        notifyObservers(player);
        player.addObserver(this);
        player.addListener(this);
        characters.add(player);
        dropList.put("PowerUp", new ArrayList<Object>());
        dropList.put("Ammunition", new ArrayList<Object>());
    }

    public void spawnBoss(Position pos) {
        boss = (Boss)enemyFactory.getEnemy("Boss", player);
        boss.addObserver(this);
        enemies.add(boss);
        characters.add(boss);
        setChanged();
        notifyObservers(boss);
        boss.addListener(this);
    }

    public void spawnEnemies(int amount) {
        for (int i = 0; i < amount; i++) {
            Enemy e = enemyFactory.getEnemy("Pleb", player);
            enemies.add(e);
            characters.add(e);
            setChanged();
            notifyObservers(e);
            e.addObserver(this);
            e.addListener(this);
        }
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
            }
            checkForCollision();
        }
    }

    /**
     * Checks if a projectile is outside the map and if so, it is removed
     */
    private void removeProjectiles() {
        for (Character c: characters) {
            Iterator<ProjectileInterface> iter = c.getProjectiles().iterator();
            while (iter.hasNext()) {
                ProjectileInterface p = iter.next();
                if ((p.getPosition().getY() < 0) || (p.getPosition().getY() > Constants.MAP_HEIGHT) ||
                        (p.getPosition().getX() > Constants.MAP_WIDTH) || (p.getPosition().getX() < 0)) {
                    iter.remove();
                    c.getProjectiles().remove(p);
                    setChanged();
                    notifyObservers(p);
                }
            }
        }
    }

    private void updatePowerUps(){
        Iterator<PowerUpI> powerUpIterator = powerUps.iterator();
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
            Enemy e=iter.next();
            if (e.getHealth() <= 0) {
                //ArrayList<Object> drop = e.die();
                e.die();
                if(e.toString().equals("Boss")) {
                    player.setScore(player.getScore() + 50);
                } else if (e.toString().equals("Pleb")) {
                    player.setScore(player.getScore() + 10);
                }
                /*if (drop!=null){
                    for (Object o : drop){
                        dropList.add(o);
                        setChanged();
                        notifyObservers(o);
                    }
                }*/
                iter.remove();
                characters.remove(e);
                enemies.remove(e);
                setChanged();
                notifyObservers(e);
            }
        }
        if (enemies.size()<1){
            spawnEnemies();
        }
    }

    private void spawnEnemies() {
        for (int i = 0; i < 5; i++) {
            Enemy e = enemyFactory.getEnemy("Pleb", player);
            enemies.add(e);
            characters.add(e);
            setChanged();
            notifyObservers(e);
        }
        for (Enemy e : enemies) {
            Random r = new Random();
            float x = r.nextFloat() * Constants.MAP_WIDTH;
            float y = r.nextFloat() * Constants.MAP_HEIGHT;
            e.getPosition().setX(x);
            e.getPosition().setY(y);
            e.addObserver(this);
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
                if (c1.isColliding(c2)){
                    characterCollision(c1, c2);
                }
            }
        }
    }

    private void checkForProjectileCollision(){
        Iterator<ProjectileInterface> projIter = projectiles.iterator();
        Iterator<Character> charIter = characters.iterator();
        while (charIter.hasNext()){
            while (projIter.hasNext()){
                ProjectileInterface projectile = projIter.next();
                Character character = charIter.next();
                if (!character.getProjectiles().contains(projectile)) {
                    character.setHealth(character.getHealth() - projectile.getDamage());
                    projIter.remove();
                    character.getProjectiles().remove(projectile);
                    setChanged();
                    notifyObservers(projectile);
                }

            }
        }
    }

    public void checkForAmmoCollision(){
        for (Character c : characters) {
            for (AmmunitionInterface a : ammunitionDrops) {
                if (c.isColliding(a)) {
                    player.getCurrentWeapon().addAmmo(a.getAmount());
                    dropList.remove(a);
                    setChanged();
                    notifyObservers(a);
                }
            }
        }
    }

    public void checkForPowerUpCollision(){
        for (Character c : characters) {
            for (PowerUpI p : powerUpDrops) {
                if (c.isColliding(p)) {
                    p.init(player);
                    powerUps.add(p);
                    dropList.remove(p);
                    setChanged();
                    notifyObservers(p);
                }
            }
        }
    }

    private void characterCollision(Character character1, Character character2){
        character1.setPosition(character1.getPrevPos());
        character2.setPosition(character2.getPrevPos());
    }

    public ArrayList<ProjectileInterface> getProjectiles(){
        return projectiles;
    }

    public Player getPlayer(){
        return player;
    }

    public Boss getBoss() {
        return boss;
    }

    public Chest getChest() {
        return chest;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void addProjectile(ProjectileInterface p) {
        projectiles.add(p);
        setChanged();
        notifyObservers(p);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ProjectileInterface && o instanceof Character) {
            //addProjectile((ProjectileInterface) arg);
        } else if(arg instanceof String) {
            if (arg.equals("player is kill")) {
                setChanged();
                notifyObservers("player is kill");
            }
        }
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

    public void pause() {
        isPaused = true;
        setChanged();
        notifyObservers("paused");
    }

    public void unPause() {
        isPaused = false;
        setChanged();
        notifyObservers("unpaused");
    }

    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "New Projectile":
                ArrayList<Projectile> projectileArray = (ArrayList<Projectile>)evt.getNewValue();
                for (ProjectileInterface p : projectileArray){
                    addProjectile(p);
                }
                break;
            case "PowerUp drops":
                ArrayList<PowerUpI> powerUpArray = (ArrayList<PowerUpI>)evt.getNewValue();
                powerUpDrops.addAll(powerUpArray);
                break;
            case "Ammunition drops":
                ArrayList<AmmunitionInterface> ammoArray = (ArrayList<AmmunitionInterface>)evt.getNewValue();
                ammunitionDrops.addAll(ammoArray);
                break;
        }
    }
}
