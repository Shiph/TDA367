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

    public static boolean collisionDetector(Collidable c1, Collidable c2) {

        Array<Rectangle> r1 = new Array<Rectangle>();
        Array<Rectangle> r2 = new Array<Rectangle>();

        r1.addAll(c1.getRectangles());
        r2.addAll(c2.getRectangles());

        //Checking for overlap between r1 and r2
        for (int i = r1.size; i != 0; i--) {
            for (int k = r2.size; i != 0; i--) {
                if (r1.get(i).overlaps(r2.get(k))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final class EnemiesVSEnvironment {
        Collidable[][] collision;

        private EnemiesVSEnvironment (ArrayList<EnemyView> enemies, ChestView chest, CollisionView collisions) {

        }

        private boolean enemiesVSChest(ArrayList<EnemyView> enemies, ChestView chest) {
            for (EnemyView e : enemies) {
                if (collisionDetector(e, chest)) {
                    return true;
                }
            }
            return false;
        }

        private boolean enemiesVSCollisions(ArrayList<EnemyView> enemies, CollisionView collisions) {
            for (EnemyView e : enemies) {
                if (collisionDetector(e, collisions)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static final class PlayerVSEnvironment {
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

    private static final class PlayerVSEnemies {
        Collidable[][] collision;

        private PlayerVSEnemies (PlayerView player, ArrayList<EnemyView>) {

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

    private static final class ProjectilesVSCharacters {
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
}