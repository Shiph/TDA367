package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Mattias on 15-05-05.
 */
public class AK47Projectile extends Projectile {

    public AK47Projectile(Position pos, Vector2 direction) {
        super(pos, direction);
        setSpeed(400);
        setDamage(1337);
    }

}
