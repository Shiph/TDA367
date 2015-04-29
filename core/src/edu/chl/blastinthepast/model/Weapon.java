package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Created by Shif on 21/04/15.
 */
public class Weapon {

    private Projectile projectile;
    private final int MAGAZINE_CAPACITY = 20;
    private final int RELOAD_TIME = 1500; //Reload time in milliseconds
    private int bulletsLeftInMagazine = 20;
    private int totalBullets = 200;
    private int fireRate = 100; //Time between shots in milliseconds
    private long latestShot=0;
    private boolean isReloading=false;
    private Timer reloadTimer;
    private Position position;
    private Vector2 direction;


    public Weapon(Position pos, Vector2 direction){
        //Here we create the reloading timer which will be used in the reload function
        ActionListener reloading=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(totalBullets >= MAGAZINE_CAPACITY) {
                    bulletsLeftInMagazine = MAGAZINE_CAPACITY;
                    totalBullets -= MAGAZINE_CAPACITY;
                }
                else {
                    bulletsLeftInMagazine = totalBullets;
                    totalBullets = 0;
                }
                isReloading=false;
            }
        };
        reloadTimer=new Timer(RELOAD_TIME, reloading);
        reloadTimer.setRepeats(false);

        position=new Position(pos);
        this.direction=direction;
    }

    public void setFireRate(int newFireRate) {
        fireRate = newFireRate;
    }

    public int getFireRate() {
        return fireRate;
    }

    public boolean hasAmmo() {
        if(totalBullets > 0) {
            return true;
        }
        return false;
    }

    public Projectile fire() {
        long currentTime=System.currentTimeMillis();
        if ((currentTime - latestShot) >= fireRate) {
            latestShot = System.currentTimeMillis();
            bulletsLeftInMagazine--;
            return new Projectile(position, direction);
        }
        return null;
    }

    public void addAmmo(int amount) {
        totalBullets += amount;
    }

    public void reload() {
        isReloading=true;
        reloadTimer.start();
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setPosition(Position newPosition){
        position.setPos(newPosition);
    }

    public void setPosition(int x, int y){
        position.setPos(x, y);
    }

    public Position getPosition(){
        return position;
    }

    public Projectile pullTrigger() {
        if (hasAmmo()) {
            reloadIfNeeded();
            if (isReloading) {
                return null;
            }
            return fire();
        } else {
            return null;
        }
    }

    private void reloadIfNeeded() {
        if (!(bulletsLeftInMagazine > 0)) {
            System.out.println("reloading...");
            reload();
        }
    }

}
