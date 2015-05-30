package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.position.PositionInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This is a generic class including all the functionality for a weapon.
 * If a weapon were to be added to the game, it should extend this class
 * and call the super constructor with the fitting values for such as reload time and fire rate.
 * It should also override the abstract method getNewProjectile().
 *
 * Created by Shif on 21/04/15.
 */
public abstract class Weapon implements WeaponInterface {

    private int magazineCapacity;
    private int reloadTime; //Reload time in milliseconds
    private ProjectileInterface projectile;
    private int bulletsLeftInMagazine;
    private int totalBullets;
    private int fireRate; //Shots per second
    private long latestShot = 0;
    private boolean isReloading = false;
    private Timer reloadTimer;
    private Position position;
    private Vector2 aimVector;
    private Vector2 movementVector;
    private Position offset;
    private int bonusDamage = 0;
    private int bonusFireRate = 0;

    /**
     * Creates a new weapon with given values.
     * @param pos Position that the weapon will be placed at.
     * @param aimVector
     * @param movementVector
     * @param reloadTime The time it should take to reaload the weapon (in milliseconds).
     * @param fireRate
     * @param magazineCapacity
     * @param totalBullets
     * @param offset Position offset to make it look like the character is actually holding the weapon.
     */
    public Weapon(PositionInterface pos, Vector2 aimVector, Vector2 movementVector, int reloadTime, int fireRate, final int magazineCapacity, int totalBullets, PositionInterface offset) {
        position = new Position(pos);
        this.aimVector = aimVector;
        this.movementVector = movementVector;
        this.fireRate = fireRate;
        this.reloadTime = reloadTime;
        this.totalBullets = totalBullets - magazineCapacity;
        this.magazineCapacity = magazineCapacity;
        bulletsLeftInMagazine = magazineCapacity;
        this.offset = new Position(offset);

        ActionListener reloading = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isReloading = false;
            }
        };

        reloadTimer = new Timer(reloadTime, reloading);
        reloadTimer.setRepeats(false);
    }

    @Override
    public void setFireRate(int newFireRate) {
        fireRate = newFireRate;
    }

    @Override
    public int getFireRate() {
        return fireRate;
    }

    @Override
    public int getMagazineCapacity() {
        return magazineCapacity;
    }

    @Override
    public ProjectileInterface getProjectile() {
        return projectile;
    }

    @Override
    public boolean hasAmmo() {
        return ((totalBullets + bulletsLeftInMagazine) > 0);
    }

    /**
     * The method takes the weapons fire rate into account and assures that it isn't exceeded.
     * If it isn't, it will return a new projectile.
     * @return a new projectile if the fire rate isn't exceeded.
     */
    @Override
    public ProjectileInterface fire() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - latestShot) >= 1000 / getTotalFireRate()) {
            latestShot = System.currentTimeMillis();
            bulletsLeftInMagazine--;
            return getNewProjectile();
        }
        return null;
    }

    @Override
    public void addAmmo(int amount) {
        if (amount > 0) {
            totalBullets += amount;
        }
    }

    /**
     * Checks if the magazine is empty and will in that case call the reload method.
     */
    @Override
    public void reloadIfNeeded() {
        if (bulletsLeftInMagazine == 0) {
            reload();
        }
    }

    /**
     * This method flags the Weapon for reloading and starts a timer.
     * It then calls the actual reload method, which is reloadWeapon().
     */
    @Override
    public void reload() {
        isReloading = true;
        reloadTimer.start();
        reloadWeapon();
    }

    /**
     * Checks that the weapon has enough bullets to reload a full magazine.
     * If not, the magazine will be filled with what's left of the total bullets.
     */
    private void reloadWeapon() {
        if ((totalBullets + bulletsLeftInMagazine) >= magazineCapacity) {
            totalBullets -= (magazineCapacity - bulletsLeftInMagazine);
            bulletsLeftInMagazine = magazineCapacity;
        } else {
            bulletsLeftInMagazine += totalBullets;
            totalBullets = 0;
        }
    }

    @Override
    public void setPosition(PositionInterface position) {
        this.position.setPosition(position);
    }


    @Override
    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int getbulletsLeftInMagazine() {
        return bulletsLeftInMagazine;
    }

    @Override
    public int getTotalBullets() {
        return totalBullets;
    }

    /**
     * This is the method that is called when the mouse is clicked in game.
     * It will first ensure that the weapon actually has ammo and then reload if nessecary.
     * After all that is passed, it will call the fire method.
     * @return a new projectile if the player has ammo left.
     */
    @Override
    public ProjectileInterface pullTrigger() {
        if (hasAmmo()) {
            reloadIfNeeded();
            if (isReloading) {
                return null;
            }
            return fire();
        }
        return null;
    }

    @Override
    public Vector2 getAimVector() {
        return aimVector;
    }

    public PositionInterface getOffset() {
        return offset;
    }

    public Position getPosWithOffset() {
        if (getAimVector() != null) {
            Vector2 v2 = new Vector2(getAimVector());
            v2.scl(1 / v2.len());
            v2.scl(getOffset().getX(), getOffset().getY());
            return new Position(position.getX() + v2.x, position.getY() + v2.y);
        }
        return null;
    }

    @Override
    public int getBonusFireRate() {
        return bonusFireRate;
    }

    @Override
    public int getTotalFireRate() {
        return fireRate + bonusFireRate;
    }

    @Override
    public void resetBonuses() {
        bonusFireRate = 0;
        bonusDamage = 0;
    }


    @Override
    public int getBonusDamage() {
        return bonusDamage;
    }

    @Override
    public void addBonusDamage(int bonusDamage) {
        this.bonusDamage += bonusDamage;
    }

    @Override
    public void addBonusFireRate(int bonusFireRate) {
        this.bonusFireRate = bonusFireRate;
    }

    @Override
    public Vector2 getMovementVector() {
        return movementVector;
    }

    public abstract ProjectileInterface getNewProjectile();

}