package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import edu.chl.blastinthepast.model.ProjectileInterface;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by Shif on 12/05/15.
 */
public class AK47ProjectileView extends ProjectileView {

    public AK47ProjectileView(ProjectileInterface projectile) {
        super(projectile, GraphicalAssets.TRIFORCE_BULLET);
    }

}
