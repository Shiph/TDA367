package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.AK47;
import edu.chl.blastinthepast.model.AK47Projectile;
import edu.chl.blastinthepast.model.Projectile;
import edu.chl.blastinthepast.model.Weapon;
import edu.chl.blastinthepast.utils.Position;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mattias on 15-05-06.
 */
public class WeaponTest {

    Weapon weapon = new AK47(new MockPosition(), new Vector2());

    @Test
    public void testFire() {
        Projectile projectile = new AK47Projectile(weapon.getPosition(), weapon.getDirection());
        Projectile projectile2 = weapon.fire();
        assertFalse(projectile == projectile2);
    }

    @Test
    public void testFireRate () {
        int fireRate = weapon.getFireRate();
        weapon.setFireRate(320523);
        assertFalse(fireRate == weapon.getFireRate());
    }

    @Test
    public void testHasAmmo() {
        assertTrue(weapon.hasAmmo()); //Should be true when initialized.
    }

    @Test
    public void testPosition() {
        Position p = new Position(13,37);
        weapon.setPosition(13,37);
        assertTrue(p.equals(weapon.getPosition()));
        assertFalse(p == weapon.getPosition());
        p = weapon.getPosition();
        assertTrue(p == weapon.getPosition());
    }

}