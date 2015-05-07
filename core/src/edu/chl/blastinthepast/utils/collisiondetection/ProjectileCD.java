package edu.chl.blastinthepast.utils.collisiondetection;

import edu.chl.blastinthepast.view.Collidable;
import edu.chl.blastinthepast.view.EnemyView;
import edu.chl.blastinthepast.view.PlayerView;
import edu.chl.blastinthepast.view.ProjectileView;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 5/7/15.
 */
public class ProjectileCD extends CollisionDetection {

    public ProjectileCD(PlayerView player, ArrayList<EnemyView> enemies, ArrayList<ProjectileView> projectiles) {
        super();
        collision.addAll(projectilesVSPlayer(projectiles, player));
        collision.addAll(projectilesVSEnemies(projectiles, enemies));
        collision = clean(collision);
    }

    private ArrayList<ArrayList<Collidable>> projectilesVSPlayer (ArrayList<ProjectileView> projectiles, PlayerView player) {
        ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
        ArrayList<ArrayList<Collidable>> tCD = new ArrayList<ArrayList<Collidable>>();

        for (ProjectileView p : projectiles) {
            tCollision.addAll(collisionDetector(p, player));
        }

        for (ArrayList<Collidable> e : tCD) {
            for (Collidable c : e) {
                if (c instanceof ProjectileView) {
                    tCollision.get(0).add(c);
                } else if (c instanceof PlayerView) {
                    tCollision.get(1).add(c);
                }
            }
        }
        tCollision = clean(tCollision);
        if (tCollision.size() > 0) {
            System.out.println(tCollision);
        }
        return tCollision;
    }

    private ArrayList<ArrayList<Collidable>> projectilesVSEnemies (ArrayList<ProjectileView> projectiles, ArrayList<EnemyView> enemies ) {
        ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>(2);
        tCollision.add(new ArrayList<Collidable>());
        tCollision.add(new ArrayList<Collidable>());
        ArrayList<ArrayList<Collidable>> tCD = new ArrayList<ArrayList<Collidable>>();

        for (ProjectileView p : projectiles) {
            for (EnemyView e : enemies) {
                tCD.addAll(collisionDetector(p, e));
            }
        }

        for (ArrayList<Collidable> e : tCD) {
            for (Collidable c : e) {
                if (c instanceof ProjectileView) {
                    tCollision.get(0).add(c);
                } else if (c instanceof EnemyView) {
                    tCollision.get(1).add(c);
                }
            }
        }
        tCollision = clean(tCollision);
        if (tCollision.size() > 0) {
            System.out.println(tCollision);
        }
        return tCollision;
    }
}
