package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Projectile;
import edu.chl.blastinthepast.model.Weapon;
import org.junit.Before;
import edu.chl.blastinthepast.model.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mattias on 15-05-06.
 */
public class WeaponTest {

    // creating a new weapon with 100 reload time, 100 fire rate, and 100 magazine capacity
    WeaponInterface weapon;

    @Before
    public void before() {
        weapon = new Weapon(new MockPosition(), new Vector2(), new MockPosition());
    }

    @Test
    public void testFire() {
        if(weapon.hasAmmo()) {
            assertTrue(weapon.fire() instanceof Projectile);
        } else {
            assertTrue(weapon.fire() == null);
        }
    }

    @Test
    public void testReload() {
        weapon.fire();
        int amount = weapon.getbulletsLeftInMagazine();
        weapon.reload();
        assertTrue(weapon.getbulletsLeftInMagazine() > amount);
    }

    @Test
    public void testFireRate () {
        int fireRate = weapon.getFireRate();
        weapon.setFireRate(320523);
        assertFalse(fireRate == weapon.getFireRate());
    }

    @Test
    public void testPosition() {
        PositionInterface p = new MockPosition(13,37);
        weapon.setPosition(13,37);
        assertTrue(p.equals(weapon.getPosition()));
        assertFalse(p == weapon.getPosition());
        p = weapon.getPosition();
        assertTrue(p == weapon.getPosition());
    }

}