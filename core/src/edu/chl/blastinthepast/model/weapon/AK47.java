package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectiles.AK47Projectile;
import edu.chl.blastinthepast.model.projectiles.Projectile;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Mattias on 15-05-05.
 */
public class AK47 extends Weapon {

    public AK47(PositionInterface position, Vector2 aimDirection, Vector2 movementDirection) {
        super(position, aimDirection, movementDirection, 1000, 5, 20, 200, new Position(60, 60));
    }

    @Override
    public Projectile getNewProjectile() {
        return new AK47Projectile(getPosWithOffset(), getAimVector(), getMovementVector(), getBonusDamage());
    }

    @Override
    public WeaponTypeEnum getWeaponType() {
        return WeaponTypeEnum.AK47;
    }

}
