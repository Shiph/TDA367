package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.ammunition.Ammunition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 * Created by Mattias on 15-05-25.
 */
public class AmmunitionTest {

    private Ammunition ammo;
    private MockPlayer player;
    private MockPosition position;

    @Before
    public void setUp() {
        position = new MockPosition(100,100);
        player = new MockPlayer();
        ammo = new Ammunition(position, new MockProjectile(), 100);
    }

    @Test
    public void testIsColliding() {
        player.setPosition(position);
        assertTrue(ammo.isColliding(player));
        player.setPosition(new MockPosition(-100, -100));
        assertFalse(ammo.isColliding(player));
    }

    @After
    public void tearDown() {

    }

}
