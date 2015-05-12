package edu.chl.blastinthepast.collisiondetection;

import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 5/7/15.
 */
public class EnemyCD extends CollisionDetection {

    public EnemyCD(ArrayList<EnemyView> enemies, PlayerView player, ChestView chest, CollisionView collisions) {
        super();
        collision.addAll(enemiesVSChest(enemies, chest));
        collision.addAll(enemiesVSCollisions(enemies, collisions));
        collision.addAll(enemiesVSPlayer(enemies, player));
        //collision = clean(collision);
        if (!collision.get(0).isEmpty()) {
            System.out.println("Enemy" + collision);
        }
    }

    public ArrayList<ArrayList<Collidable>> getCollision () {
        return collision;
    }

    private ArrayList<ArrayList<Collidable>> enemiesVSChest(ArrayList<EnemyView> enemies, ChestView chest) {
        ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
        for (EnemyView e : enemies) {
            tCollision.addAll(collisionDetector(e, chest));
        }
        tCollision = clean(tCollision);
        return tCollision;
    }

    private ArrayList<ArrayList<Collidable>> enemiesVSCollisions(ArrayList<EnemyView> enemies, CollisionView collisions) {
        ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
        for (EnemyView e : enemies) {
            tCollision.addAll(collisionDetector(e, collisions));
        }
        tCollision = clean(tCollision);
        return tCollision;
    }

    private ArrayList<ArrayList<Collidable>> enemiesVSPlayer(ArrayList<EnemyView> enemies, PlayerView player) {
        ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
        for (EnemyView e : enemies) {
            tCollision.addAll(collisionDetector(e, player));
        }
        tCollision = clean(tCollision);
        return tCollision;
    }

}