package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public interface WeaponInterface {

    /**
     * This is the method that is called when the mouse is clicked in game.
     * It will first ensure that the weapon actually has ammo and then reload if nessecary.
     * After all that is passed, it will call the fire method.
     * @return a new projectile if the player has ammo left.
     */
    ProjectileInterface pullTrigger();

    /**
     * Checks if the magazine is empty and will in that case call the reload method.
     */
    void reloadIfNeeded();

    /**
     * This method flags the Weapon for reloading and starts a timer.
     * It then calls the actual reload method, which is reloadWeapon().
     */
    void reload();

    /**
     * The method takes the weapons fire rate into account and assures that it isn't exceeded.
     * If it isn't, it will return a new projectile.
     * @return a new projectile if the fire rate isn't exceeded.
     */
    ProjectileInterface fire();

    void resetBonuses();
    int getTotalBullets();
    int getBonusDamage();
    int getFireRate();
    boolean hasAmmo();
    void addAmmo(int amount);
    ProjectileInterface getProjectile();
    void setPosition(PositionInterface position);
    void setPosition(int x, int y);
    PositionInterface getPosition();
    void addBonusDamage(int bonusDamage);
    void addBonusFireRate(int bonusFireRate);
    int getBonusFireRate();
    int getTotalFireRate();
    Vector2 getAimVector();
    Vector2 getMovementVector();
    int getbulletsLeftInMagazine();
    ProjectileInterface getNewProjectile();
    int getMagazineCapacity();
    WeaponTypeEnum getWeaponType();
}
