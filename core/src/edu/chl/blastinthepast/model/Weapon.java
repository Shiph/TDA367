package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Created by Shif on 21/04/15.
 */
public class Weapon implements WeaponInterface {

    private Projectile projectile;
    private int magazineCapacity;
    private int reloadTime; //Reload time in milliseconds
    private int bulletsLeftInMagazine = 20;
    private int totalBullets = 200;
    private int fireRate; //Time between shots in milliseconds
    private long latestShot = 0;
    private boolean isReloading = false;
    private Timer reloadTimer;
    private Position position;
    private Vector2 direction;
    private Position offset;

    public Weapon (PositionInterface pos, Vector2 direction, PositionInterface offset) {
        this(pos, direction, 1500, 100, 20, offset);
    }

    public Weapon (PositionInterface pos, Vector2 direction, int reloadTime, int fireRate, final int magazineCapacity, PositionInterface offset) {
        position = new Position(pos);
        this.direction = direction;
        this.fireRate = fireRate;
        this.reloadTime = reloadTime;
        this.magazineCapacity = magazineCapacity;
        this.offset= new Position(offset);

        ActionListener reloading = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public Projectile fire() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - latestShot) >= fireRate) {
            latestShot = System.currentTimeMillis();
            bulletsLeftInMagazine--;
            return new Projectile(new Position(position.getX()+offset.getX(), position.getY()+offset.getY()), direction);
        }
        return null;
    }

    public void addAmmo(int amount) {
        totalBullets += amount;
    }

    public void reload() {
        isReloading = true;
        reloadTimer.start();
        reloadWeapon();
    }

    private void reloadWeapon(){
        if(totalBullets >= magazineCapacity) {
            bulletsLeftInMagazine = magazineCapacity;
            totalBullets -= magazineCapacity;
        } else {
            bulletsLeftInMagazine = totalBullets;
            totalBullets = 0;
        }
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setLatestShot(long newValue) {
        latestShot = newValue;
    }

    public long getLatestShot () {
        return latestShot;
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

    public void setBulletsLeftInMagazine(int newValue) {
        bulletsLeftInMagazine = newValue;
    }

    public int getbulletsLeftInMagazine() {
        return bulletsLeftInMagazine;
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

    public PositionInterface getOffset(){
        return offset;
    }

}
