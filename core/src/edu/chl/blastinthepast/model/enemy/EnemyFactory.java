package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.enemy.Pleb;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class EnemyFactory {

    public Enemy getEnemy(Character player, Character.CharacterType characterType) {
        if(player == null) {
            return null;
        }
        switch(characterType) {
            case PLEB:
                return new Pleb(player);
            case BOSS:
                return new Boss(player);
        }
        return null;
    }
}
