package edu.chl.blastinthepast.model.projectile;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-06.
 */
public interface ProjectileInterface {

    public void setSpeed(int newSpeed);
    public void move(float dt);
    public int getDamage();
    public void setDamage(int damage);
    public Vector2 getAimVector();
    public float getSpeed();
    public PositionInterface getPosition();
    public enum ProjectileType {
        AK47, MAGNUM
    }
    ProjectileType getProjectileType();
}
