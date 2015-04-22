package edu.chl.blastinthepast;

/**
 * Created by Shif on 21/04/15.
 */
public class Weapon {

    private float fireRate = 1.337f;
    private Projectile projectile = new Projectile();
    private final int MAGAZINE_CAPACITY = 1337;
    private int bulletsLeft = 1337;

    public float getFireRate() {
        return fireRate;
    }

    public Projectile fire() {
        if(bulletsLeft > 0) {
            bulletsLeft--;
            return new Projectile();
        } else {
            throw new NullPointerException("No bullets left");
        }
    }

    public Projectile getProjectile() {
        return projectile;
    }

}
