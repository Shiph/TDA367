package edu.chl.blastinthepast.collisiondetection;

import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 5/7/15.
 */
public class ProjectileCD extends CollisionDetection {

    public ProjectileCD(PlayerView player, ArrayList<EnemyView> enemies, ArrayList<ProjectileView> projectiles) {
        super();
        collision.addAll(projectilesVSPlayer(projectiles, player));
        collision.addAll(projectilesVSEnemies(projectiles, enemies));
        //collision = clean(collision);
        if (!collision.get(0).isEmpty()) {
            System.out.println("Projectile" + collision);
        }
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
                    tCollision.get(0).addAll((ArrayList<Collidable>) c);
                } else if (c instanceof PlayerView) {
                    tCollision.get(1).addAll((ArrayList<Collidable>) c);
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
        /*
        if (tCollision.size() > 0) {
            System.out.println(tCollision);
        }
        */
        return tCollision;
    }

    void resolve () {
        int i = 0;
        //System.out.println(collisionEVSE.get(0).get(0));
        for (Collidable c : collision.get(0)) {
            System.out.println("for_1");
            if (c instanceof ProjectileView) {
                System.out.println("if_1");
                if (collision.get(1).get(i) instanceof CharacterView) {
                    System.out.println("if_2");
                    pcs.firePropertyChange("characterHit", ((ProjectileView) c).getProjectile(), collision.get(1).get(i));
                } else if (collision.get(1).get(i) instanceof Environment) {
                    pcs.firePropertyChange("environmentHit", ((ProjectileView) c).getProjectile(), collision.get(1).get(i));
                }

            }
            i++;
        }
    }
}
