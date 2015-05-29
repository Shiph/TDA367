package edu.chl.blastinthepast.model.projectiles;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-06.
 */
public interface ProjectileInterface extends Collidable{

    void setSpeed(int newSpeed);
    void move(float dt);
    int getDamage();
    void setDamage(int damage);
    Vector2 getAimVector();
    float getSpeed();
    PositionInterface getPosition();
    ProjectileTypeEnum getProjectileType();

}
