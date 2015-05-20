package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.view.characterviews.BossView;
import edu.chl.blastinthepast.view.characterviews.EnemyView;
import edu.chl.blastinthepast.view.characterviews.PlebView;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class EnemyViewFactory {

    public EnemyView getEnemyView(String enemyType, Enemy enemy) {
        if(enemyType == null) {
            return null;
        }
        if(enemyType.equals("Pleb")) {
            return new PlebView(enemy);
        } else if(enemyType.equals("Boss")) {
            return new BossView(enemy);
        }
        return null;
    }
}
