package edu.chl.blastinthepast.model.projectile;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-06.
 */
public interface ProjectileInterface {

    public enum ProjectileType {
        AK47, MAGNUM
    }

    void setSpeed(int newSpeed);
    void move(float dt);
    int getDamage();
    void setDamage(int damage);
    Vector2 getDirection();
    float getSpeed();
    PositionInterface getPosition();
    ProjectileType getProjectileType();
}
