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

    @Before
    public void before() {
        weapon = new Weapon(new MockPosition(), new Vector2(), new MockPosition());
    }
    Weapon weapon = new Weapon(new MockPosition(), new Vector2(0,0), new MockPosition());

    @Test
    public void testFire() {
        for(int i = 0; i< weapon.getMagazineCapacity(); i++) { //Empties the magazine
            weapon.fire();
        }
        assertTrue(weapon.fire() == null); //Should return null if there's no bullets left in magazine.
    }

    @Test
    public void testReload() {
        weapon.reload();
        assertTrue(weapon.getbulletsLeftInMagazine() == weapon.getMagazineCapacity());
    }

    @Test
    public void testAddAmmo() {
        //Tests that you can't add more ammo than the magazine can "hold".
        weapon.addAmmo(420);
        assertTrue(weapon.getTotalBullets() == weapon.getMagazineCapacity());
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