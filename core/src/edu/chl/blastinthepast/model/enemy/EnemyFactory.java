package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.loot.GenerousLoot;
import edu.chl.blastinthepast.model.loot.RandomLootGenerator;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class EnemyFactory {

    public Enemy getEnemy(CharacterI player, CharacterTypeEnum characterType, PositionInterface position) {
        if(player == null) {
            return null;
        }
        switch(characterType) {
            case PLEB:
                return new Pleb(player, RandomLootGenerator.generateLoot(), position);
            case BOSS:
                return new Boss(player, new GenerousLoot(), position);
        }
        return null;
    }

}