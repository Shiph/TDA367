package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.ProjectileInterface;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by Shif on 12/05/15.
 */
public class MagnumProjectileView extends ProjectileView {

    public MagnumProjectileView(ProjectileInterface projectile) {
        super(projectile, GraphicalAssets.MAGNUM);
    }

}
