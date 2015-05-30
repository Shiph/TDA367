package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.loot.LootInterface;
import edu.chl.blastinthepast.model.loot.StandardLoot;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public class StandardLootTest {

    private LootInterface lootType;

    @Before
    public void setUp() {
        lootType = new StandardLoot();
    }

    /**
     * Whether or not loot is created (since there's 25% chance that you'll get no loot)
     * we assert that at least a HashMap is created and returned.
     */
    @Test
    public void testGenerateLoot() {
        HashMap<String, ArrayList<? extends Object>> loot;
        loot = lootType.generateLoot(new MockPosition(), new MockWeapon());
        assertFalse(loot == null);
    }

}