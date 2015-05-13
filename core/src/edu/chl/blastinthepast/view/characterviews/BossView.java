package edu.chl.blastinthepast.view.characterviews;

import edu.chl.blastinthepast.model.entities.Boss;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by Mattias on 15-05-07.
 */
public class BossView extends EnemyView {

    private Boss boss;


    public BossView(Boss boss) {
        super(boss);
        this.boss = boss;
        getSprite().setTexture(GraphicalAssets.BOSSDOWN);
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
