package edu.chl.blastinthepast.model.projectiles;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.position.Position;

/**
 * Created by Shif on 12/05/15.
 */
public class MagnumProjectile extends Projectile {

    public MagnumProjectile(Position pos, Vector2 aimDirection, Vector2 movementDirection, int bonusDamage) {
        super(pos, aimDirection, movementDirection, 600, 2, bonusDamage, 4 , 4);
    }

    @Override
    public ProjectileTypeEnum getProjectileType() {
        return ProjectileTypeEnum.MAGNUM;
    }
}