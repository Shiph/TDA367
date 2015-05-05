package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 4/23/15.
 */
public final class CollisionDetection {

    public void update (ArrayList<EnemyView> enemies, PlayerView player, ArrayList<ProjectileView> projectiles, ChestView chest, CollisionView collisions) {

    }

    public static Collidable[][] collisionDetector(Collidable c1, Collidable c2) {

        Collidable[][] collision = new Collidable[2][];

        ArrayList<Rectangle> r1 = new ArrayList<Rectangle>();
        ArrayList<Rectangle> r2 = new ArrayList<Rectangle>();

        r1.addAll(c1.getRectangles());
        r2.addAll(c2.getRectangles());

        int r1c,r2c;
        r1c = r2c = 0;

        //Checking for overlap between r1 and r2
        for (Rectangle r : r1) {
            for (Rectangle rr : r2) {
                if (r.overlaps(rr)) {
                    collision[0][r1c] = r;
                    collision[1][r2c] = rr;
                }
                r2c++;
            }
            r1c++;
        }
        return collision;
    }


    private class EnemiesVSEnvironment {
        Collidable[][] collision;

        private EnemiesVSEnvironment (ArrayList<EnemyView> enemies, ChestView chest, CollisionView collisions) {
            collision = new Collidable[2][];

        }

        private EnemyView enemiesVSChest(ArrayList<EnemyView> enemies, ChestView chest) {
            for (EnemyView e : enemies) {
                if (collisionDetector(e, chest)) {
                    return e;
                }
            }
            return null;
        }

        private Object[][] enemiesVSCollisions(ArrayList<EnemyView> enemies, CollisionView collisions) {
            Object[][] tempCollision = new Object[2][];
            for (int i = enemies.size(); i >= 0; i--) {
                if (collisionDetector(enemies, collisions)) {

                }
            }
            return null;
        }
    }

    private class PlayerVSEnvironment {
        Collidable[][] collision;

        private PlayerVSEnvironment (PlayerView player, ChestView chest, CollisionView collisions) {

        }

        private boolean playerVSChest (PlayerView player, ChestView chest) {
            return collisionDetector(player, chest);
        }

        private boolean playerVSCollision (PlayerView player, CollisionView collisions) {
            return collisionDetector(player, collisions);
        }
    }

    private class PlayerVSEnemies {
        Collidable[][] collision;

        private PlayerVSEnemies (PlayerView player, ArrayList<EnemyView> enemies) {

        }

        private boolean playerVSEnemies(ArrayList<EnemyView> enemies, PlayerView player) {
            for (EnemyView e : enemies) {
                if (collisionDetector(e, player)) {
                    return true;
                }
            }
            return false;
        }
    }

    private class ProjectilesVSCharacters {
        Collidable[][] collision;

        private ProjectilesVSCharacters (PlayerView player, ArrayList<EnemyView> enemies, ArrayList<ProjectileView> projectiles) {

        }

        private boolean projectilesVSPlayer (ArrayList<ProjectileView> projectiles, PlayerView player) {
            for (ProjectileView p : projectiles) {
                if (collisionDetector(p, player)) {
                    return true;
                }
            }
            return false;
        }

        private boolean projectilesVSPlayer (ArrayList<ProjectileView> projectiles, ArrayList<EnemyView> enemies ) {
            for (ProjectileView p : projectiles) {
                for (EnemyView e : enemies) {
                    if (collisionDetector(p, e)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private static final class Resolve {

    }
}