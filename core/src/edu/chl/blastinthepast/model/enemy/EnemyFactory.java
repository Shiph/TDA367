package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.loot.GenerousLoot;
import edu.chl.blastinthepast.model.loot.RandomLootGenerator;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class EnemyFactory {

    public Enemy getEnemy(CharacterI player, CharacterTypeEnum characterType) {
        if(player == null) {
            return null;
        }
        switch(characterType) {
            case PLEB:
                return new Pleb(player, RandomLootGenerator.generateLoot());
            case BOSS:
                return new Boss(player, new GenerousLoot());
        }
        return null;
    }

}