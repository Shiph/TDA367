package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

import javax.swing.*;

/**
 * Created by Shif on 06/05/15.
 */
public interface WeaponInterface {

    public void setFireRate(int newFireRate);
    public int getFireRate();
    public boolean hasAmmo();
    public Projectile fire();
    public void addAmmo(int amount);
    public Projectile getProjectile();
    public void setPosition(Position newPosition);
    public void setPosition(int x, int y);
    public Position getPosition();
    public Projectile pullTrigger();
    public void reloadIfNeeded();
    public Vector2 getDirection();

}
