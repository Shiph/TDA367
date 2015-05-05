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
        for(EnemyView e : enemies) {
        }
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

    private static final class Enemies {

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

    private static final class Player {

        private boolean playerVSChest (PlayerView player, ChestView chest) {
            return collisionDetector(player, chest);
        }

        private boolean playerVSCollision (PlayerView player, CollisionView collisions) {
            return collisionDetector(player, collisions);
        }
    }

    private static final class Projectiles {

        private boolean projectilesVSPlayer (ArrayList<ProjectileView> projectiles, PlayerView player) {
            for (ProjectileView p : projectiles) {
                if (collisionDetector(p, player)) {
                    return true;
                }
            }
            return false;
        }

        private boolean projectilesVSPlayer (ArrayList<ProjectileView> projectiles, Array<EnemyView> enemies ) {
            return true;
        }
    }

    private boolean enemiesVSPlayer (ArrayList < EnemyView > enemies, PlayerView player) {
        for (EnemyView e : enemies) {
            if (collisionDetector(e, player)) {
                return true;
            }
        }
        return false;
    }
}