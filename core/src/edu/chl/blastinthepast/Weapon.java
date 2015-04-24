package edu.chl.blastinthepast;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Created by Shif on 21/04/15.
 */
public class Weapon {

    private Projectile projectile = new Projectile();
    private final int MAGAZINE_CAPACITY = 20;
    private final int RELOAD_TIME = 1500; //Reload time in milliseconds
    private int bulletsLeftInMagazine = 20;
    private int totalBullets = 200;
    private int fireRate = 100; //Time between shots in milliseconds
    private long latestShot=0;
    private boolean isReloading=false;
    private Timer reloadTimer;

    public Weapon(){
        //Here we create the reloading timer which will be used in the reload function
        ActionListener reloading=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(totalBullets >= MAGAZINE_CAPACITY) {
                    bulletsLeftInMagazine = MAGAZINE_CAPACITY;
                    totalBullets -=MAGAZINE_CAPACITY;
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
    }

    public int getFireRate() {
        return fireRate;
    }

    public boolean hasAmmo() {
        long currentTime=System.currentTimeMillis();
        if((currentTime - latestShot)>=fireRate && bulletsLeftInMagazine>0) {
            latestShot = System.currentTimeMillis();
            bulletsLeftInMagazine--;
            return true;
        } else if(totalBullets>0 && bulletsLeftInMagazine<=0 && !isReloading) {
            reload();
            return true;
        }
        return false;
    }

    public Projectile fire() {
        return new Projectile();
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

}
