package edu.chl.blastinthepast.view.projectileviews;

import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;

/**
 * Created by jonas on 2015-05-21.
 */
public class ProjectileViewFactory {

    public ProjectileView getProjectileView(ProjectileInterface projectile){
        if (projectile == null) {
            return null;
        }
        switch (projectile.getProjectileType()){
            case AK47:
                return new AK47ProjectileView(projectile);
            case MAGNUM:
                return new MagnumProjectileView(projectile);
        }
        return null;
    }

}
