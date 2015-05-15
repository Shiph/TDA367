package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.entities.Chest;
import edu.chl.blastinthepast.model.entities.WeaponInterface;
import org.junit.Test;
import static junit.framework.TestCase.*;

/**
 * Created by Mattias on 15-05-12.
 */
public class ChestTest {

    private MockPlayer mockPlayer = new MockPlayer();
    private Chest chest = new Chest(new MockWeapon());

    /**
     * Tests that the player equips a weapon when the chest is looted.
     */
    @Test
    public void testOpen() {
        assertTrue(mockPlayer.getWeapon() == null);
        mockPlayer.setWeapon(chest.open(mockPlayer));
        assertTrue(mockPlayer.getWeapon() instanceof WeaponInterface);
    }

    /**
     * Once the player loots a chest it's critical that isOpened() will return the correct value so that the player can't loot a weapon again.
     */
    @Test
    public void testIsOpened() {
        chest = new Chest(new MockWeapon());
        assertFalse(chest.isOpened());
        chest.open(mockPlayer);
        assertTrue(chest.isOpened());
    }

}