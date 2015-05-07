package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel implements Observer {
    private Player player;
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<PowerUp> powerUps= new ArrayList<PowerUp>();


    public BPModel() {
        player=new Player();
        spawnEnemies();
    }

    public void update(float dt){
        removeOldProjectiles();
        removeDeadEnemies();
        player.update();
        for (Projectile p : projectiles) {
            p.move(dt);
        }
        for (Enemy e : enemies) {
            e.update();
            e.move(dt);
        }
    }

    /**
     * Checks if a projectile is outside the map and if so removes it
     */
    private void removeOldProjectiles() {
        Iterator<Projectile> iter = projectiles.iterator();
        while(iter.hasNext()) {
            Projectile p = iter.next();
            if((p.getPosition().getY() < 0) || (p.getPosition().getY() > Constants.MAP_HEIGHT) ||
                    (p.getPosition().getX() > Constants.MAP_WIDTH) || (p.getPosition().getX() < 0)) {
                iter.remove();
            }
        }
    }

    private void removeDeadEnemies(){
        Iterator<Enemy> iter= enemies.iterator();
        while (iter.hasNext()){
            Enemy e=iter.next();
            if (e.getHealth()<=0){
                System.out.println("Ded");
                iter.remove();
            }
        }
    }

    private void spawnEnemies() {
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(player));
        }
        for (Enemy e : enemies) {
            Random r = new Random();
            e.getPosition().setX(r.nextFloat() * 800);
            e.getPosition().setY(r.nextFloat() * 480);
            e.addObserver(this);
        }
    }

    public void collision(Object o1, Object o2){ //temporary collision detection
        if ((o1 instanceof Character && o2 instanceof Projectile)){
            Character character=(Character) o1;
            Projectile projectile = (Projectile) o2;
            hit(character, projectile);
        } else if (o2 instanceof Character && o1 instanceof Projectile){
            Character character=(Character) o2;
            Projectile projectile = (Projectile) o1;
            hit(character, projectile);
        }
    }

    public void hit(Character character, Projectile projectile){ //collision for bullets
        character.setHealth(character.getHealth()-projectile.getDamage());
        projectiles.remove(projectile);
    }

    public void newGame() {
    }

    public ArrayList<Projectile> getProjectiles(){
        return projectiles;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Projectile) {
            addProjectile((Projectile)arg);
        }
        if (arg instanceof PowerUp){
            PowerUp powerUp=(PowerUp)arg;
            powerUps.add(powerUp);
        }
    }

}
