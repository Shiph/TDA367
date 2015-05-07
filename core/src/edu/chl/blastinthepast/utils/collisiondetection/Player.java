package edu.chl.blastinthepast.utils.collisiondetection;

import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 5/7/15.
 */
public class Player extends CollisionDetection {

    public Player(PlayerView player, ArrayList<EnemyView> enemies, ChestView chest, CollisionView collisions) {
        collision = new ArrayList<ArrayList<Collidable>>(2);
        collision.addAll(playerVSChest(player, chest));
        collision.addAll(playerVSCollision(player, collisions));
        collision.addAll(playerVSEnemies(player, enemies));
        collision = clean(collision);
    }

    public ArrayList<ArrayList<Collidable>> getCollision () {
        return collision;
    }

    private ArrayList<ArrayList<Collidable>> playerVSChest (PlayerView player, ChestView chest) {
        return collisionDetector(player, chest);
    }

    private ArrayList<ArrayList<Collidable>> playerVSCollision (PlayerView player, CollisionView collisions) {
        return collisionDetector(player, collisions);
    }

    private ArrayList<ArrayList<Collidable>> playerVSEnemies(PlayerView player, ArrayList<EnemyView> enemies) {
        ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
        for (EnemyView e : enemies) {
            tCollision.addAll(collisionDetector(player, e));
        }
        tCollision = clean(tCollision);
        return tCollision;
    }
}
