package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectiles.MagnumProjectile;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Shif on 12/05/15.
 */
public class Magnum extends Weapon {

    public Magnum(PositionInterface position, Vector2 aimDirection, Vector2 movementDirection) {
        super(position, aimDirection, movementDirection, 850, 2, 16, 16*16, new Position(0, 0));
    }

    @Override
    public ProjectileInterface getNewProjectile() {
        return new MagnumProjectile(getPosWithOffset(), getAimVector(), getMovementVector(), getBonusDamage());

    }

    @Override
    public WeaponTypeEnum getWeaponType() {
        return WeaponTypeEnum.MAGNUM;
    }

}
