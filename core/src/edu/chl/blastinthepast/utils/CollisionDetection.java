package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.math.Rectangle;
<<<<<<< HEAD
import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;
import java.util.Collections;
=======
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;
>>>>>>> hopefully fixed some shit

/**
 * Created by qwerty458 on 4/23/15.
 */
public final class CollisionDetection {

    public void update (ArrayList<EnemyView> enemies, PlayerView player, ArrayList<ProjectileView> projectiles, ChestView chest, CollisionView collisions) {
<<<<<<< HEAD
        
    }

    public static ArrayList<ArrayList<Collidable>> collisionDetector(Collidable c1, Collidable c2) {

        ArrayList<ArrayList<Collidable>> collision = new ArrayList<ArrayList<Collidable>>();

        ArrayList<Rectangle> r1 = new ArrayList<Rectangle>();
        ArrayList<Rectangle> r2 = new ArrayList<Rectangle>();
=======
        for(EnemyView e : enemies) {
        }
    }

    public static boolean collisionDetector(Collidable c1, Collidable c2) {

        Array<Rectangle> r1 = new Array<Rectangle>();
        Array<Rectangle> r2 = new Array<Rectangle>();
>>>>>>> hopefully fixed some shit

        r1.addAll(c1.getRectangles());
        r2.addAll(c2.getRectangles());

        //Checking for overlap between r1 and r2
        for (Rectangle r : r1) {
            for (Rectangle rr : r2) {
                if (r.overlaps(rr)) {
                    collision.get(0).add(c1);
                    collision.get(1).add(c1);
                }
            }
        }
        return collision;
    }


    private class EnemiesVSEnvironment {
        private ArrayList<ArrayList<Collidable>> collision;

        private EnemiesVSEnvironment (ArrayList<EnemyView> enemies, ChestView chest, CollisionView collisions) {
            collision = new ArrayList<ArrayList<Collidable>>();
            collision.addAll(enemiesVSChest(enemies, chest));
            collision.addAll(enemiesVSCollisions(enemies, collisions));
        }

        private ArrayList<ArrayList<Collidable>> enemiesVSChest(ArrayList<EnemyView> enemies, ChestView chest) {
            ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
            for (EnemyView e : enemies) {
                tCollision.addAll(collisionDetector(e, chest));
            }
            return tCollision;
        }

        private ArrayList<ArrayList<Collidable>> enemiesVSCollisions(ArrayList<EnemyView> enemies, CollisionView collisions) {
            ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
            for (EnemyView e : enemies) {
                tCollision.addAll(collisionDetector(e, collisions));
            }
            return tCollision;
        }
    }

<<<<<<< HEAD
    private class PlayerVSEnvironment {
        ArrayList<ArrayList<Collidable>> collision;

        private PlayerVSEnvironment (PlayerView player, ChestView chest, CollisionView collisions) {
            collision = new ArrayList<ArrayList<Collidable>>();
            collision.addAll(playerVSChest(player, chest));
            collision.addAll(playerVSCollision(player, collisions));
        }

        private ArrayList<ArrayList<Collidable>> playerVSChest (PlayerView player, ChestView chest) {
            return collisionDetector(player, chest);
        }

        private ArrayList<ArrayList<Collidable>> playerVSCollision (PlayerView player, CollisionView collisions) {
            return collisionDetector(player, collisions);
        }
    }

    private class PlayerVSEnemies {
        ArrayList<ArrayList<Collidable>> collision;

        private PlayerVSEnemies (PlayerView player, ArrayList<EnemyView> enemies) {
            collision = new ArrayList<ArrayList<Collidable>>();

        }

        private ArrayList<ArrayList<Collidable>> playerVSEnemies(ArrayList<EnemyView> enemies, PlayerView player) {
            ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
            for (EnemyView e : enemies) {
                tCollision.addAll(collisionDetector(e, player));
            }
            return tCollision;
        }
    }

    private class ProjectilesVSCharacters {
        ArrayList<ArrayList<Collidable>> collision;

        private ProjectilesVSCharacters (PlayerView player, ArrayList<EnemyView> enemies, ArrayList<ProjectileView> projectiles) {
            collision = new ArrayList<ArrayList<Collidable>>();
            collision.addAll(projectilesVSPlayer(projectiles, player));
            collision.addAll(projectilesVSEnemies(projectiles, enemies));
        }

        private ArrayList<ArrayList<Collidable>> projectilesVSPlayer (ArrayList<ProjectileView> projectiles, PlayerView player) {
            ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
            for (ProjectileView p : projectiles) {
                tCollision.addAll(collisionDetector(p, player));
            }
            return tCollision;
        }

        private ArrayList<ArrayList<Collidable>> projectilesVSEnemies (ArrayList<ProjectileView> projectiles, ArrayList<EnemyView> enemies ) {
            ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
            for (ProjectileView p : projectiles) {
                for (EnemyView e : enemies) {
                    tCollision.addAll(collisionDetector(p, e));
                }
            }
            return tCollision;
        }
    }

    private static final class Resolve {

    }

    /*

    public static ArrayList<ArrayList<Collidable>> clean(final ArrayList<ArrayList<Collidable>> cs) {
        for (ArrayList<ArrayList<Collidable>> aac : cs) {
            aac.removeAll(Collections.singleton(null));
        }
        return cs;
    }

    */
=======
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
>>>>>>> hopefully fixed some shit
}