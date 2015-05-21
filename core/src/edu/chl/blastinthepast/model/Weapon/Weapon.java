
package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
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

    @Override
    public void reload() {
        isReloading = true;
        reloadTimer.start();
        reloadWeapon();
    }

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
    public void reloadIfNeeded() {
        if (bulletsLeftInMagazine == 0) {
            reload();
        }
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