package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.chest.Chest;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 * Created by Mattias on 15-05-12.
 */
public class ChestTest {

    private MockPlayer mockPlayer;
    private Chest chest;
    private MockWeapon mockWeapon;

    @Before
    public void setUp() {
        mockPlayer = new MockPlayer();
        mockWeapon = new MockWeapon();
        chest = new Chest(mockWeapon, new Position(0,0));
    }

    /**
     * Tests that the player equips a weapon when the chest is looted.
     */
    @Test
    public void testOpen() {
        assertTrue(mockPlayer.getCurrentWeapon() == null);
        mockPlayer.setWeapon(chest.open(mockPlayer));
        assertTrue(mockPlayer.getCurrentWeapon() instanceof WeaponInterface);
    }

    /**
     * Once the player loots a chest it's important that isOpened() will return the correct value so that the player can't loot a weapon again.
     */
    @Test
    public void testIsOpened() {
        chest = new Chest(mockWeapon, new Position(0,0));
        assertFalse(chest.isOpened());
        chest.open(mockPlayer);
        assertTrue(chest.isOpened());
    }

    @Test
    public void testCollision(){
        chest.getPosition().setPosition(0, 0);
        MockCollidable collidable = new MockCollidable();
        collidable.rectangle.set(0, 0, 100, 100);
        assertTrue(chest.isColliding(collidable));
    }

    @After
    public void tearDown() {}

}