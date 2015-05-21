package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.projectile.ProjectileInterface;

/**
 * Created by jonas on 2015-05-21.
 */
public class ProjectileViewFactory {

    public ProjectileView getProjectileView(ProjectileInterface projectile){
        if (projectile==null){
            return null;
        }
        switch (projectile.toString()){
            case "AK47Projectile":
                return new AK47ProjectileView(projectile);
            case "MagnumProjectile":
                return new MagnumProjectileView(projectile);
        }
        return null;
    }
}
