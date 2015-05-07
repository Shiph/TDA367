package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.Boss;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by Mattias on 15-05-07.
 */
public class BossView extends EnemyView {

    public BossView(Boss boss) {
        super(boss);
        getSprite().setTexture(GraphicalAssets.DONKIM);
    }
}
