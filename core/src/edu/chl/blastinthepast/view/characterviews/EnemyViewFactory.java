package edu.chl.blastinthepast.view.characterviews;

import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.view.characterviews.BossView;
import edu.chl.blastinthepast.view.characterviews.EnemyView;
import edu.chl.blastinthepast.view.characterviews.PlebView;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class EnemyViewFactory{

    public EnemyView getEnemyView(Enemy enemy) {
        if(enemy == null) {
            return null;
        }
        switch (enemy.toString()) {
            case "Pleb":
                return new PlebView(enemy);
            case "Boss":
                return new BossView(enemy);
        }
        return null;
    }
}
