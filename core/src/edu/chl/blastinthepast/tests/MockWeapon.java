package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.weapon.WeaponTypeEnum;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public class MockWeapon implements WeaponInterface {

    public int fireRate = 100;
    public MockProjectile projectile = new MockProjectile();
    public MockPosition position = new MockPosition();
    public Vector2 direction = new Vector2();
    public int magazineCapacity=10;
    public int ammo = 100;
    public int bulletsInMag = 10;
    public int bonusDamage = 0;
    public int bonusFireRate = 0;

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
        if (bulletsInMag>0) {
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
    public Vector2 getAimVector() {
        return null;
    }

    @Override
    public Vector2 getMovementVector() {
        return null;
    }

    public int getTotalBullets() {
        return ammo;
    }

    @Override
    public int getBonusDamage() {
        return bonusDamage;
    }

    @Override
    public void addBonusDamage(int bonusDamage) {
        this.bonusDamage+=bonusDamage;
    }

    @Override
    public int getMagazineCapacity() {
        return magazineCapacity;
    }

    @Override
    public WeaponTypeEnum getWeaponType() {
        return null;
    }

    @Override
    public void addBonusFireRate(int bonusFireRate) {
        this.bonusFireRate+=bonusFireRate;
    }

    @Override
    public int getBonusFireRate() {
        return bonusFireRate;
    }

    @Override
    public int getTotalFireRate() {
        return fireRate+bonusFireRate;
    }

    @Override
    public void resetBonuses() {
        bonusDamage=0;
        bonusFireRate=0;
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
        if ((ammo + bulletsInMag) >= magazineCapacity) {
            ammo -= (magazineCapacity - bulletsInMag);
            bulletsInMag = magazineCapacity;
        } else {
            bulletsInMag += ammo;
            ammo = 0;
        }
    }

}
