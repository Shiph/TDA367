package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Created by Shif on 21/04/15.
 */
public abstract class Weapon implements WeaponInterface {

    protected Projectile projectile;
    protected int magazineCapacity;
    protected int reloadTIme = 1500; //Reload time in milliseconds
    protected int bulletsLeftInMagazine = 20;
    protected int totalBullets = 200;
    protected int fireRate; //Time between shots in milliseconds
    protected long latestShot = 0;
    protected boolean isReloading = false;
    protected Timer reloadTimer;
    protected Position position;
    protected Vector2 direction;

    public Weapon (Position pos, Vector2 direction, int reloadTime, int fireRate, int magazineCapacity) {
        position = new Position(pos);
        this.direction = direction;
        this.fireRate = fireRate;
        this.reloadTIme = reloadTime;
        this.magazineCapacity = magazineCapacity;

        ActionListener reloading = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(totalBullets >= magazineCapacity) {
                    bulletsLeftInMagazine = magazineCapacity;
                    totalBullets -= magazineCapacity;
                } else {
                    bulletsLeftInMagazine = totalBullets;
                    totalBullets = 0;
                }
                isReloading=false;
            }
        };

        reloadTimer = new Timer(reloadTime, reloading);
        reloadTimer.setRepeats(false);
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

    public abstract Projectile fire();

    public void addAmmo(int amount) {
        totalBullets += amount;
    }

    public void reload() {
        isReloading = true;
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

    public void reloadIfNeeded() {
        if (bulletsLeftInMagazine == 0) {
            reload();
        }
    }

    public Vector2 getDirection(){
        return direction;
    }

}
