package edu.chl.blastinthepast.model.projectile;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 12/05/15.
 */
public class MagnumProjectile extends Projectile {

    public MagnumProjectile(Position pos, Vector2 aimDirection, Vector2 movementDirection, int bonusDamage) {
        super(pos, aimDirection, movementDirection, 600, 2, bonusDamage);
    }

    @Override
    public ProjectileType getProjectileType() {
        return ProjectileType.MAGNUM;
    }
}
