package edu.chl.blastinthepast;

import java.util.Timer;

/**
 * Created by Shif on 21/04/15.
 */
public class Weapon {

    private float fireRate = 1.337f;
    private Projectile projectile = new Projectile();
    //private final int MAGAZINE_CAPACITY = 20;
    private int bulletsLeftInMagazine = 20;
    private int totalBullets = 200;

    public float getFireRate() {
        return fireRate;
    }

    public Projectile fire() {
        if(bulletsLeftInMagazine > 0) {
            bulletsLeftInMagazine--;
            return new Projectile();
        } else if(totalBullets > 0) {
            reload();
            return new Projectile();
        } else{
            throw new NullPointerException("No bullets left");
        }
    }

    public void addAmmo(int amount) {
        totalBullets += amount;
    }

    public void reload() {
        if(totalBullets >= 20) {
            bulletsLeftInMagazine = 20;
            totalBullets -=20;
        }
        else {
            bulletsLeftInMagazine = totalBullets;
            totalBullets = 0;
        }
    }

    public Projectile getProjectile() {
        return projectile;
    }

}
