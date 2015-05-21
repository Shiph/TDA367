package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.Weapon;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public class MockWeapon implements WeaponInterface {

    private int fireRate = 100;
    private MockProjectile projectile = new MockProjectile();
    private MockPosition position = new MockPosition();
    private Vector2 direction = new Vector2();
    private int magazineCapacity;
    private int ammo = 100;
    private int bulletsInMag = 10;

    @Override
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    @Override
    public int getFireRate() {
        return fireRate;
    }

    @Override
    public boolean hasAmmo() {
        if (!(ammo > 0)) {
            return false;
        }
        return true;
    }

    @Override
    public ProjectileInterface fire() {
        ammo--;
        bulletsInMag--;
        return new MockProjectile();
    }

    @Override
    public void addAmmo(int amount) {
        ammo = ammo + amount;
    }

    @Override
    public ProjectileInterface getProjectile() {
        return projectile;
    }

    @Override
    public void setPosition(PositionInterface position) {
        if (position instanceof MockPosition) {
            this.position = (MockPosition)position;
        }
    }

    @Override
    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public ProjectileInterface pullTrigger() {
        if (hasAmmo()) {
            return fire();
        }
        return null;
    }

    @Override
    public void reloadIfNeeded() {
        if(!(bulletsInMag > 0) && hasAmmo()) {
            reload();
        }
    }

    @Override
    public Vector2 getDirection() {
        return null;
    }

    @Override
    public int getTotalBullets() {
        return 0;
    }

    @Override
    public int getBonusDamage() {
        return 0;
    }

    @Override
    public void addBonusDamage(int bonusDamage) {
    }

    @Override
    public int getMagazineCapacity() {
        return magazineCapacity;
    }

    @Override
    public Weapon.WeaponType getWeaponType() {
        return null;
    }

    @Override
    public void addBonusFireRate(int bonusFireRate) {

    }

    @Override
    public int getBonusFireRate() {
        return 0;
    }

    @Override
    public int getTotalFireRate() {
        return 0;
    }

    @Override
    public void resetBonuses() {

    }

    @Override
    public ProjectileInterface getNewProjectile() {
        return null;
    }

    @Override
    public int getbulletsLeftInMagazine() {
        return bulletsInMag;
    }

    @Override
    public void reload() {
        if (ammo >= 10) {
            ammo = ammo - 10;
            bulletsInMag = 10;
        } else {
            bulletsInMag = ammo;
            ammo = 0;
        }
    }

}
