package edu.chl.blastinthepast.model.entities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Mattias on 15-05-05.
 */
public class AK47Projectile extends Projectile {

    public AK47Projectile(Position pos, Vector2 direction, int bonusDamage) {
        super(pos, direction, 800, 10, bonusDamage);
    }

    public AK47Projectile(Position pos, Vector2 direction) {
        super(pos, direction, 800, 10);
    }

}
