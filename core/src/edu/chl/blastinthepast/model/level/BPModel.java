package edu.chl.blastinthepast.model.level;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Ammunition;
import edu.chl.blastinthepast.model.entities.*;
import edu.chl.blastinthepast.model.entities.Character;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel extends Observable implements Observer {

    private Player player;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Object> dropList = new ArrayList<Object>();
    private ArrayList<Character> characters;
    private Boss boss;
    private Chest chest;



    public BPModel() {
        player = new Player();
        chest = new Chest(new Magnum(new Position(300,300), new Vector2()));
        characters = new ArrayList<Character>();
        player = new Player();
        setChanged();
        notifyObservers(player);
        player.addObserver(this);
        characters.add(player);
        spawnBoss();
        spawnEnemies();
    }

    public void spawnBoss() {
        boss = new Boss(player, new Position(500, 500));
        boss.addObserver(this);
        enemies.add(boss);
        characters.add(boss);
        setChanged();
        notifyObservers(boss);
    }

    public void update(float dt){
        removeProjectiles();
        removeDeadEnemies();
        if (enemies.size()<1){
            spawnEnemies();
        }
        player.update(dt);
        for (ProjectileInterface p : projectiles) {
            p.move(dt);
        }
        for (Enemy e : enemies) {
            e.update(dt);
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

    private void removeDeadEnemies(){
        Iterator<Enemy> iter= enemies.iterator();
        while (iter.hasNext()){
            Enemy e=iter.next();
            if (e.getHealth()<=0) {
                ArrayList<Object> drop = e.die();
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
    }

    private void spawnEnemies() {
        for (int i = 0; i < 2; i++) {
            Enemy e = new Enemy(player, new Position(0, 0));
            enemies.add(e);
            characters.add(e);
            setChanged();
            notifyObservers(e);
        }
        for (Enemy e : enemies) {
            Random r = new Random();
            float x = r.nextFloat() * Constants.MAP_WIDTH;
            float y = r.nextFloat() * Constants.MAP_HEIGHT;
            while (x <= player.getPosition().getX() + Constants.CAMERA_WIDTH/2 && //Makes enemies spawn outside the players view
                    x >= player.getPosition().getX() - Constants.CAMERA_WIDTH/2) {
                while (y <= player.getPosition().getY() + Constants.CAMERA_HEIGHT/2 &&
                        y >= player.getPosition().getY() - Constants.CAMERA_HEIGHT/2) {
                    y = r.nextFloat() * Constants.MAP_HEIGHT;
                }
                x = r.nextFloat() * Constants.MAP_WIDTH;
            }
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
        } else if (o2 instanceof Character && o1 instanceof ProjectileInterface){
            Character character=(Character) o2;
            ProjectileInterface projectile = (ProjectileInterface) o1;
            hit(character, projectile);
        }
        if (o1 instanceof Ammunition && o2 instanceof Player){
            Ammunition a = (Ammunition) o1;
            Player p = (Player) o2;
            pickUpAmmunition(a, p);
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
        player.getWeapon().addAmmo(ammo.getAmount());
        dropList.remove(ammo);
        setChanged();
        notifyObservers(ammo);
    }

    public void newGame() {
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
        } else if (arg instanceof PowerUp){
            PowerUp powerUp=(PowerUp)arg;
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

}