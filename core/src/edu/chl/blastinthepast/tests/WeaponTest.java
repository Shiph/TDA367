package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.entities.Projectile;
import edu.chl.blastinthepast.model.entities.Weapon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mattias on 15-05-06.
 */
public class WeaponTest {

    Weapon weapon;

    @Before
    public void setUp() {
        weapon = new Weapon(new MockPosition(), new Vector2(0,0), 0, 1000, 20, 150, new MockPosition());
    }

    /**
     * Tests that the pull trigger-method returns an instance of a projectile, which it should unless you're out of ammo.
     * We can presume that the weapon actually has ammo and thus a projectile to return since the weapon instance was just initialized.
     */
    @Test
    public void testPullTrigger() {
        assertTrue(weapon.pullTrigger() instanceof Projectile);
    }

    /**
     * Checks if the magazine reloads the amount that the magazine can hold.
     * In this case we know that there is enough bullets to fill the magazine.
     */
    @Test
    public void testReload() {
        weapon.reload();
        assertTrue(weapon.getbulletsLeftInMagazine() == weapon.getMagazineCapacity());
    }

    /**
     * Tests that after the weapon has fired all of its ammo it should also accordingly have no ammo left in the magazine.
     */
    @Test
    public void testHasAmmo() {
        while(weapon.hasAmmo()) {
            weapon.pullTrigger();
        }
        assertTrue(weapon.getbulletsLeftInMagazine() == 0);
    }

    @After
    public void tearDown() {}

}