package edu.chl.blastinthepast.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel {
    private Player player;
    private Array<Projectile> projectiles = new Array<Projectile>();
    private Array<Enemy> enemies;


    public BPModel() {
        player=new Player();
    }

    public void update(){
        updateProjectilePos();
        player.getWeapon().setPosition(player.getPosition());
    }

    /**
     * Checks if a projectile is outside the map and if so removes it
     */
    private void updateProjectilePos() {
        Iterator<Projectile> iter = projectiles.iterator();
        while(iter.hasNext()) {
            Projectile p = iter.next();
            p.getPosition().setY(p.getPosition().getY() + ((float)Math.cos(Math.toRadians(p.getDirection()))) * p.getSpeed() * Gdx.graphics.getDeltaTime());
            p.getPosition().setX(p.getPosition().getX() - ((float) Math.cos(Math.toRadians(p.getDirection()))) * p.getSpeed() * Gdx.graphics.getDeltaTime());
            if((p.getPosition().getY() < 0) || (p.getPosition().getY() > Constants.MAP_HEIGHT) ||
                    (p.getPosition().getX() > Constants.MAP_WIDTH) || (p.getPosition().getX() < 0)) {
                iter.remove();
            }
        }
    }

    private void spawnEnemies() {
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy());
        }
        for (Enemy e : enemies) {
            Random r = new Random();
            e.getPosition().setX(r.nextFloat() * 800);
            e.getPosition().setY(r.nextFloat() * 480);
        }
    }

    private void updateEnemyPos(){

    }

    public void spawnProjectile() {
        if (player.getWeapon().hasAmmo()) {
            Projectile newProjectile = player.getWeapon().fire();
            projectiles.add(newProjectile);

        }
    }

    public Array<Projectile> getProjectiles(){
        return projectiles;
    }

    public Player getPlayer(){
        return player;
    }

    public Array<Enemy> getEnemies(){
        return enemies;
    }

}
