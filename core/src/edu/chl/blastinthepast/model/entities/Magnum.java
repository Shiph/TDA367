package edu.chl.blastinthepast.model.entities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 12/05/15.
 */
public class Magnum extends Weapon {

    public Magnum(PositionInterface position, Vector2 direction) {
        super(position, direction, 850, 2, 16, 16*16, new Position(0, 0));
    }

    @Override
    public ProjectileInterface getNewProjectile() {
        return new MagnumProjectile(getPosWithOffset(), getDirection(), getBonusDamage());
    }

    @Override
    public String toString() {
        return "Magnum";
    }

}
