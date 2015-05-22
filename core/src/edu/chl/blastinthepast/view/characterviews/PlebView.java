package edu.chl.blastinthepast.view.characterviews;

import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.enemy.Pleb;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class PlebView extends EnemyView {

    private Pleb pleb;

    public PlebView(Pleb pleb) {
        super(pleb, GraphicalAssets.PLEBDOWN);
        this.pleb = pleb;
    }

    @Override
    public void updateDirection() {
        try {
            switch (pleb.getMovementDirection()) {
                case 0:
                    getSprite().setTexture(GraphicalAssets.PLEBLEFT);
                    break;
                case 1:
                    getSprite().setTexture(GraphicalAssets.PLEBRIGHT);
                    break;
                case 2:
                    getSprite().setTexture(GraphicalAssets.PLEBUP);
                    break;
                case 3:
                    getSprite().setTexture(GraphicalAssets.PLEBDOWN);
                    break;
            }
        } catch (NullPointerException e) {}
    }

}
