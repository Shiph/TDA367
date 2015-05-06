package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.AK47;
import edu.chl.blastinthepast.model.AK47Projectile;
import edu.chl.blastinthepast.model.Projectile;
import edu.chl.blastinthepast.model.Weapon;
import edu.chl.blastinthepast.utils.Position;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by Mattias on 15-05-06.
 */
public class WeaponTest {

    Weapon weapon = new AK47(new Position(0,0), new Vector2());

    @Test
    public void testFire() {
        Projectile projectile = new AK47Projectile(weapon.getPosition(), weapon.getDirection());
        Projectile projectile2 = weapon.fire();
        assertFalse(projectile == projectile2);
    }

}