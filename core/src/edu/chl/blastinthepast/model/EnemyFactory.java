package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.model.entities.Boss;
import edu.chl.blastinthepast.model.entities.Enemy;
import edu.chl.blastinthepast.model.entities.Character;
import edu.chl.blastinthepast.model.entities.Pleb;

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
