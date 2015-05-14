package edu.chl.blastinthepast.model.entities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 12/05/15.
 */
public class MagnumProjectile extends Projectile {

    public MagnumProjectile(Position pos, Vector2 direction) {
        super(pos, direction, 600, 20);
    }

}
