package edu.chl.blastinthepast.model.enemy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jonas on 2015-05-29.
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
