package edu.chl.blastinthepast.model.projectiles;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Shif on 12/05/15.
 */
public class MagnumProjectile extends Projectile {

    public MagnumProjectile(PositionInterface pos, Vector2 aimDirection, Vector2 movementDirection, int bonusDamage) {
        super(pos, aimDirection, movementDirection, 1500, 2, bonusDamage, 4 , 4);
    }

    @Override
    public ProjectileTypeEnum getProjectileType() {
        return ProjectileTypeEnum.MAGNUM;
    }
}