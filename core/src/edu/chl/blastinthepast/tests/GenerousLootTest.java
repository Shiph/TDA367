package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.loot.GenerousLoot;
import edu.chl.blastinthepast.model.loot.LootInterface;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public class GenerousLootTest {

    private LootInterface lootType;

    @Before
    public void setUp() {
        lootType = new GenerousLoot();
    }

    /**
     * Generous loot is guaranteed to return two loot types: ammunition and power-up.
     * Therefore we test that the HashMap actually contains those two power-ups.
     */
    @Test
    public void testGenerateLoot() {
        HashMap<String, ArrayList<? extends Object>> loot;
        loot = lootType.generateLoot(new MockPosition(), new MockWeapon());
        for (int i = 0; i < loot.size(); i++) {
            assertTrue(loot.get("Ammunition Loot").get(0) instanceof AmmunitionInterface);
            assertTrue(loot.get("PowerUp Loot").get(0) instanceof PowerUpI);
        }
    }

}
