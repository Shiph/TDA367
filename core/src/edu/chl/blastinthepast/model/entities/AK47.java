package edu.chl.blastinthepast.model.entities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-05-05.
 */
public class AK47 extends Weapon {

    public AK47(PositionInterface position, Vector2 direction) {
        super(position, direction, 1000, 5, 20, 200, new Position(60, 60));
    }

    @Override
    public Projectile fire() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - getLatestShot()) >= 1000/getTotalFireRate()) {
            setLatestShot(System.currentTimeMillis());
            setBulletsLeftInMagazine(getbulletsLeftInMagazine()-1);
            return new AK47Projectile(getPosWithOffset(), getDirection(), getBonusDamage());
        }
        return null;
    }

    @Override
    public Projectile getProjectile() {
        return new AK47Projectile(getPosition(), getDirection(), getBonusDamage());
    }

    public String toString() {
        return "AK47";
    }

}
