package edu.chl.blastinthepast.model.loot;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jonas on 2015-05-29.
 *
 * Returns an instance of a random concrete loot class.
 *
 */
public class RandomLootGenerator {

    private RandomLootGenerator(){}

    public static LootInterface generateLoot(){
        ArrayList<LootInterface> lootTypes = new ArrayList<LootInterface>();
        lootTypes.add(new StandardLoot());
        lootTypes.add(new GenerousLoot());
        Random random = new Random();
        return lootTypes.get(random.nextInt(lootTypes.size()));
    }
}
