package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public interface WeaponInterface {

    enum WeaponType {
        AK47, MAGNUM
    }

    void setFireRate(int fireRate);
    int getFireRate();
    boolean hasAmmo();
    ProjectileInterface fire();
    void addAmmo(int amount);
    ProjectileInterface getProjectile();
    void setPosition(PositionInterface position);
    void setPosition(int x, int y);
    PositionInterface getPosition();
    ProjectileInterface pullTrigger();
    void reloadIfNeeded();
    Vector2 getAimVector();
    Vector2 getMovementVector();
    int getbulletsLeftInMagazine();
    void reload();
    int getTotalBullets();
    int getBonusDamage();
    void addBonusDamage(int bonusDamage);
    void addBonusFireRate(int bonusFireRate);
    int getBonusFireRate();
    int getTotalFireRate();
    void resetBonuses();
    ProjectileInterface getNewProjectile();
    int getMagazineCapacity();
    WeaponType getWeaponType();

}
