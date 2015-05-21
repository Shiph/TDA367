package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.weapon.AK47;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mattias on 15-05-06.
 */
public class WeaponTest {

    WeaponInterface weapon;

    @Before
    public void setUp() {
        weapon = new AK47(new MockPosition(), new Vector2(0,0), new Vector2(0,0));
    }

    /**
     * Tests that the pull trigger-method returns an instance of a projectile, which it should unless you're out of ammo.
     * We can presume that the weapon actually has ammo and thus a projectile to return since the weapon instance was just initialized.
     */
    @Test
    public void testPullTrigger() {
        assertTrue(weapon.pullTrigger() instanceof ProjectileInterface);
    }

    /**
     * Checks if the magazine reloads the amount that the magazine can hold.
     * In this case we know that there is enough bullets to fill the magazine.
     */
    @Test
    public void testReload() {
        weapon.pullTrigger();
        weapon.reload();
        assertTrue(weapon.getbulletsLeftInMagazine() == weapon.getMagazineCapacity());
    }

    /**
     * Tests that after the weapon has fired all of its ammo it should also accordingly have no ammo left in the magazine (or at all).
     * Takes some time to perform this test due to a timer depending on the firerate.
     */
    @Test
    public void testHasAmmo() {
        while(weapon.hasAmmo()) {
            weapon.pullTrigger();
        }
        assertTrue(weapon.getbulletsLeftInMagazine() == 0);
        assertTrue(weapon.getTotalBullets() == 0);
    }

    /**
     * Asserts that a negative value won't be "added" to the total amount of bullets
     * and that the total amounts of bullets will change if ammo is actually added.
     */
    @Test
    public void testAddAmmo() {
        int currentAmmo = weapon.getTotalBullets();

        weapon.addAmmo(-100);
        assertTrue(currentAmmo == weapon.getTotalBullets());

        weapon.addAmmo(10);
        assertFalse(currentAmmo == weapon.getTotalBullets());
    }

    @After
    public void tearDown() {}

}