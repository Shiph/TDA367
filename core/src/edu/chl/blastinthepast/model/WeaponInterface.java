package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;

/**
 * Created by Shif on 06/05/15.
 */
public interface WeaponInterface {

    public void setFireRate(int fireRate);
    public int getFireRate();
    public boolean hasAmmo();
    public ProjectileInterface fire();
    public void addAmmo(int amount);
    public ProjectileInterface getProjectile();
    public void setPosition(PositionInterface position);
    public void setPosition(int x, int y);
    public PositionInterface getPosition();
    public ProjectileInterface pullTrigger();
    public void reloadIfNeeded();
    public Vector2 getDirection();
    public int getbulletsLeftInMagazine();
    public void reload();

}
