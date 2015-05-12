package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.ProjectileView;
import javafx.geometry.Pos;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel extends Observable implements Observer {

    private Player player;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Character> characters;
    private Boss boss;
    private Chest chest;
    private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();


    public BPModel() {
        player = new Player();
        chest = new Chest(new AK47(new Position(300,300), new Vector2()));
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
        characters.add(boss);
        setChanged();
        notifyObservers(boss);
    }

    public void update(float dt){
        removeProjectiles();
        removeDeadEnemies();
        player.update(dt);
        for (ProjectileInterface p : projectiles) {
            p.move(dt);
        }
        for (Enemy e : enemies) {
            e.update();
            e.move(dt);
        }
        boss.update();
        boss.move(dt);
    }

    /**
     * Checks if a projectile is outside the map and if so, it is removed
     */
    private void removeProjectiles() {
        HashMap<Character, ArrayList<ProjectileInterface>> projectileMap=new HashMap<Character, ArrayList<ProjectileInterface>>();
        for (Character c: characters) {
            Iterator<ProjectileInterface> iter = c.getProjectiles().iterator();
            while (iter.hasNext()) {
                ProjectileInterface p = iter.next();
                p.getDamage();
                if ((p.getPosition().getY() < 0) || (p.getPosition().getY() > Constants.MAP_HEIGHT) ||
                        (p.getPosition().getX() > Constants.MAP_WIDTH) || (p.getPosition().getX() < 0)) {
                    iter.remove();
                    c.getProjectiles().remove(p);
                    setChanged();
                    notifyObservers(p);
                }
            }
        }
        /*Iterator<ProjectileInterface> iter = projectiles.iterator();
        while(iter.hasNext()) {
            ProjectileInterface p = iter.next();
            if((p.getPosition().getY() < 0) || (p.getPosition().getY() > Constants.MAP_HEIGHT) ||
                    (p.getPosition().getX() > Constants.MAP_WIDTH) || (p.getPosition().getX() < 0)) {
                iter.remove();
            }
        }*/
    }

    private void removeDeadEnemies(){
        Iterator<Enemy> iter= enemies.iterator();
        while (iter.hasNext()){
            Enemy e=iter.next();
            if (e.getHealth()<=0) {
                iter.remove();
                characters.remove(e);
                setChanged();
                notifyObservers(e);
            }
        }
        if (enemies.size()<1){
            spawnEnemies();
        }
    }

    private void spawnEnemies() {
        for (int i = 0; i < 10; i++) {
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
            while (x <= player.getPosition().getX() + Constants.CAMERA_WIDTH && //Makes enemies spawn outside the players view
                    x >= player.getPosition().getX() - Constants.CAMERA_WIDTH) {
                while (y <= player.getPosition().getY() + Constants.CAMERA_HEIGHT &&
                        y >= player.getPosition().getY() - Constants.CAMERA_HEIGHT) {
                    y = r.nextFloat() * Constants.MAP_HEIGHT;
                }
                x = r.nextFloat() * Constants.MAP_WIDTH;
            }
            e.getPosition().setX(x);
            e.getPosition().setY(y);
            e.addObserver(this);
        }
    }

    public void collision(Object o1, Object o2){ //temporary collision detection
        if ((o1 instanceof Character && o2 instanceof Projectile)){
            Character character=(Character) o1;
            Projectile projectile = (Projectile) o2;
            hit(character, projectile);
        } else if (o2 instanceof Character && o1 instanceof ProjectileInterface){
            Character character=(Character) o2;
            ProjectileInterface projectile = (ProjectileInterface) o1;
            hit(character, projectile);
        }
    }

    public void hit(Character character, ProjectileInterface projectile){ //collision for bullets
        character.setHealth(character.getHealth()-projectile.getDamage());
        projectiles.remove(projectile);
        character.getProjectiles().remove(projectile);
        setChanged();
        notifyObservers(projectile);
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
        }
        if (arg instanceof PowerUp){
            PowerUp powerUp=(PowerUp)arg;
            powerUps.add(powerUp);
        }
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

}
