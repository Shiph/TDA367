package edu.chl.blastinthepast.view.projectileviews;

import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.utils.GraphicalAssets;
import edu.chl.blastinthepast.utils.SoundAssets;

/**
 * Created by Shif on 12/05/15.
 */
public class AK47ProjectileView extends ProjectileView {

    public AK47ProjectileView(ProjectileInterface projectile) {
        super(projectile, GraphicalAssets.TRIFORCE_BULLET, SoundAssets.WOW_SOUND);
    }

}