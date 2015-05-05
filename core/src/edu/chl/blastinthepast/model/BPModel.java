package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel {
    private Player player;
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();


    public BPModel() {
        player=new Player();
        spawnEnemies();
    }

    public void update(float dt){
        removeOldProjectiles();
        player.getWeapon().setPosition(player.getPosition());
        for (Projectile p : projectiles) {
            p.move(dt);
        }
        for (Enemy e : enemies) {
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

    private void spawnEnemies() {
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(player));
        }
        for (Enemy e : enemies) {
            Random r = new Random();
            e.getPosition().setX(r.nextFloat() * 800);
            e.getPosition().setY(r.nextFloat() * 480);
        }
    }

    public void newGame() {
        player = new Player();
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

}
