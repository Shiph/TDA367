package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectile.AK47Projectile;
import edu.chl.blastinthepast.model.projectile.Projectile;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

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
    public String toString() {
        return "AK47";
    }

    @Override
    public WeaponType getWeaponType() {
        return WeaponType.AK47;
    }

}
