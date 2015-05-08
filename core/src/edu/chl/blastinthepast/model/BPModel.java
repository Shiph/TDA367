package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.view.Collidable;
import edu.chl.blastinthepast.view.Environment;

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


    public BPModel() {
        player=new Player();
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
        removeOldProjectiles();
        removeDeadEnemies();
        player.update();
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
     * Checks if a projectile is outside the map and if so removes it
     */
    private void removeOldProjectiles() {
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
    }

    private void spawnEnemies() {
        for (int i = 0; i < 1; i++) {
            enemies.add(new Enemy(player));
        }
        for (Enemy e : enemies) {
            Random r = new Random();
            e.getPosition().setX(r.nextFloat() * 800);
            e.getPosition().setY(r.nextFloat() * 480);
            e.addObserver(this);
        }
    }

    public void collision(Projectile p, Collidable c) { //temporary collision detection
        if ((p instanceof Projectile && c instanceof Character)) {
            hit(p,(Character) c);
        } else if (p instanceof Projectile && c instanceof Environment) {
            hit(p,(Environment) c);
        }
    }

    public void hit(Projectile projectile, Character character) { //collision for bullets + characters
        character.setHealth(character.getHealth()-projectile.getDamage());
        projectiles.remove(projectile);
    }

    public void hit(Projectile projectile, Environment environment) { //Collision for bullets + environment
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
        if (arg instanceof Projectile) {
            addProjectile((Projectile)arg);
        }
        if (arg instanceof PowerUp){
            PowerUp powerUp=(PowerUp)arg;
            powerUps.add(powerUp);
        }
    }

}
