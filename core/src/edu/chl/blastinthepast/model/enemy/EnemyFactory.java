package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.enemy.Pleb;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class EnemyFactory {

    public Enemy getEnemy(String enemyType, Character player) {
        if(enemyType == null) {
            return null;
        }
        if(enemyType.equals("Pleb")) {
            return new Pleb(player);
        } else if(enemyType.equals("Boss")) {
            return new Boss(player);
        }
        return null;
    }
}
