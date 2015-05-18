package edu.chl.blastinthepast.model.entities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public interface WeaponInterface {

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
    Vector2 getDirection();
    int getbulletsLeftInMagazine();
    void reload();
    int getTotalBullets();
    int getBonusDamage();
    void setBonusDamage(int bonusDamage);
    void setBonusFireRate(int bonusFireRate);
    int getBonusFireRate();
    int getTotalFireRate();
}
