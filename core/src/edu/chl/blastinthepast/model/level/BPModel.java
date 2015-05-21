package edu.chl.blastinthepast.model.level;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.ammunition.Ammunition;
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
import edu.chl.blastinthepast.model.weapon.WeaponFactory;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel extends Observable implements Observer {

    private Player player;
    private EnemyFactory enemyFactory;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Object> dropList = new ArrayList<Object>();
    private ArrayList<Character> characters;
    private Chest chest;
    private Boss boss;
    private boolean isPaused;
    private ArrayList<PowerUpI> powerUps=new ArrayList<PowerUpI>();

    public BPModel() {
        chest = new Chest(new Magnum(new Position(1000,1500), new Vector2()));
        characters = new ArrayList<Character>();
        player = new Player();
        enemyFactory = new EnemyFactory();
        setChanged();
        notifyObservers(player);
        player.addObserver(this);
        characters.add(player);
    }

    public void spawnBoss(Position pos) {
        boss = (Boss)enemyFactory.getEnemy(player, Character.CharacterType.BOSS);
        boss.addObserver(this);
        enemies.add(boss);
        characters.add(boss);
        setChanged();
        notifyObservers(boss);
    }

    public void spawnEnemies(int amount) {
        for (int i = 0; i < amount; i++) {
            Enemy pleb = enemyFactory.getEnemy(player, Character.CharacterType.PLEB);
            enemies.add(pleb);
            characters.add(pleb);
            setChanged();
            notifyObservers(pleb);
            pleb.addObserver(this);
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
                ArrayList<Object> drop = e.die();
                if(e.toString().equals("Boss")) {
                    player.setScore(player.getScore() + 50);
                } else if (e.toString().equals("Pleb")) {
                    player.setScore(player.getScore() + 10);
                }
                if (drop!=null){
                    for (Object o : drop){
                        dropList.add(o);
                        setChanged();
                        notifyObservers(o);
                    }
                }
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
            Enemy e = enemyFactory.getEnemy(player, Character.CharacterType.PLEB);
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

    public void collision(Object o1, Object o2){
        if ((o1 instanceof Character && o2 instanceof Projectile)){
            Character character=(Character) o1;
            Projectile projectile = (Projectile) o2;
            hit(character, projectile);
        }
        if (o1 instanceof Ammunition && o2 instanceof Player){
            Ammunition a = (Ammunition) o1;
            Player p = (Player) o2;
            pickUpAmmunition(a, p);
        }
        if (o1 instanceof Character && o2 instanceof Character){
            Character c1 = (Character) o1;
            Character c2 = (Character) o2;
            characterCollision(c1, c2);
        }
        if (o1 instanceof PowerUpI && o2 instanceof Player){
            PowerUpI powerUp = (PowerUpI) o1;
            Player player = (Player) o2;
            pickUpPowerUp(powerUp, player);
        }
    }

    public void hit(Character character, ProjectileInterface projectile){ //collision for bullets
        if (!character.getProjectiles().contains(projectile)) {
            character.setHealth(character.getHealth() - projectile.getDamage());
            projectiles.remove(projectile);
            character.getProjectiles().remove(projectile);
            setChanged();
            notifyObservers(projectile);
        }
    }

    public void pickUpAmmunition(Ammunition ammo, Player player){
        player.getCurrentWeapon().addAmmo(ammo.getAmount());
        dropList.remove(ammo);
        setChanged();
        notifyObservers(ammo);
    }

    private void characterCollision(Character character1, Character character2){
        character1.setPosition(character1.getPrevPos());
        character2.setPosition(character2.getPrevPos());
    }

    private void pickUpPowerUp(PowerUpI powerUp, Player player){
        powerUp.init(player);
        powerUps.add(powerUp);
        dropList.remove(powerUp);
        setChanged();
        notifyObservers(powerUp);
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
            addProjectile((ProjectileInterface) arg);
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

}
