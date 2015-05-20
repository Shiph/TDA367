package edu.chl.blastinthepast.view.characterviews;

import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by Mattias on 15-05-07.
 */
public class BossView extends EnemyView {

    private Enemy boss;

    public BossView(Enemy boss) {
        super(boss, GraphicalAssets.BOSSDOWN);
        this.boss = boss;
    }

    @Override
    public void updateDirection() {
        try {
            switch (boss.getMovementDirection()) {
                case 0:
                    getSprite().setTexture(GraphicalAssets.BOSSLEFT);
                    break;
                case 1:
                    getSprite().setTexture(GraphicalAssets.BOSSRIGHT);
                    break;
                case 2:
                    getSprite().setTexture(GraphicalAssets.BOSSUP);
                    break;
                case 3:
                    getSprite().setTexture(GraphicalAssets.BOSSDOWN);
                    break;
            }
        } catch (NullPointerException e) {}
    }
}
