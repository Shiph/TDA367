package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.ProjectileView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel implements Observer {

    private Player player;
    private ArrayList<ProjectileInterface> projectiles = new ArrayList<ProjectileInterface>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Boss boss;
    private ArrayList<PowerUp> powerUps= new ArrayList<PowerUp>();
    private ArrayList<Character> characters;


    public BPModel() {
        player=new Player();
        player.addObserver(this);
        spawnBoss();
        spawnEnemies();
    }

    public void spawnBoss() {
        boss = new Boss(player);
        boss.getPosition().setX(500);
        boss.getPosition().setY(500);
        boss.addObserver(this);
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
        Iterator<ProjectileInterface> iter = projectiles.iterator();
        while(iter.hasNext()) {
            ProjectileInterface p = iter.next();
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
        if (enemies.size()<1){
            spawnEnemies();
        }
    }

    private void spawnEnemies() {
        for (int i = 0; i < 10; i++) {
            enemies.add(new Enemy(player));
        }
        for (Enemy e : enemies) {
            Random r = new Random();
            float x=r.nextFloat()*Constants.MAP_WIDTH;
            float y=r.nextFloat()*Constants.MAP_HEIGHT;
            while (x<=player.getPosition().getX()+Constants.CAMERA_WIDTH && //Makes enemies spawn outside the players view
                    x>=player.getPosition().getX()-Constants.CAMERA_WIDTH){
                while (y<=player.getPosition().getY()+Constants.CAMERA_HEIGHT &&
                        y>=player.getPosition().getY()-Constants.CAMERA_HEIGHT){
                    y=r.nextFloat()*Constants.MAP_HEIGHT;
                }
                x=r.nextFloat()*Constants.MAP_WIDTH;
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

    public ArrayList<ProjectileInterface> getProjectiles(){
        return projectiles;
    }

    public Player getPlayer(){
        return player;
    }

    public Boss getBoss() {
        return boss;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void addProjectile(ProjectileInterface p) {
        projectiles.add(p);
    }

    public void removeProjectile(Projectile pp) {
        for (ProjectileInterface p : projectiles) {
            if (p == pp) {
                projectiles.remove(pp);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Projectile && o instanceof Character) {
            addProjectile((Projectile)arg);
        }
        if (arg instanceof PowerUp){
            PowerUp powerUp=(PowerUp)arg;
            powerUps.add(powerUp);
        }
    }

}
