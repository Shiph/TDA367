package edu.chl.blastinthepast.model.projectiles;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.position.Position;

/**
 * Created by Mattias on 15-05-05.
 */
public class AK47Projectile extends Projectile {

    public AK47Projectile(Position pos, Vector2 aimDirection, Vector2 movementDirection, int bonusDamage) {
        super(pos, aimDirection, movementDirection, 800, 1, bonusDamage, 4, 4);
    }

    @Override
    public ProjectileTypeEnum getProjectileType() {
        return ProjectileTypeEnum.AK47;
    }
}
