package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 4/23/15.
 */
public class CollisionDetection {

    public enum Type {
        ENVIRONMENT, PROJECTILE
    }

    public CollisionDetection (ArrayList<EnemyView> enemies, PlayerView player, ArrayList<ProjectileView> projectiles, ChestView chest, CollisionView collisions) {
        ArrayList<ArrayList<Collidable>> collision = new ArrayList<>(2);
        collision.add(new ArrayList<Collidable>());
        collision.add(new ArrayList<Collidable>());

        collision.addAll(new EnemiesVSEnvironment(enemies, player, chest, collisions).collision);
        collision.addAll(new PlayerVSEnvironment(player, enemies, chest, collisions).collision);

        new Resolve(collision, Type.ENVIRONMENT);

        collision.addAll(new ProjectilesVSEverything(player, enemies, projectiles).collision);

        new Resolve(collision, Type.PROJECTILE);
    }


    public ArrayList<ArrayList<Collidable>> collisionDetector(Collidable c1, Collidable c2) {
        ArrayList<ArrayList<Collidable>> collision = new ArrayList<>(2);
        collision.add(new ArrayList<Collidable>());
        collision.add(new ArrayList<Collidable>());

        ArrayList<Rectangle> r1 = new ArrayList<Rectangle>();
        ArrayList<Rectangle> r2 = new ArrayList<Rectangle>();

        r1.addAll(c1.getRectangles());
        r2.addAll(c2.getRectangles());

        //Checking for overlap between r1 and r2
        for (Rectangle r : r1) {
            for (Rectangle rr : r2) {
                if (r.overlaps(rr)) {
                    collision.get(0).add(c1);
                    collision.get(1).add(c2);
                }
            }
        }
        return collision;
    }


    public class EnemiesVSEnvironment {
        private ArrayList<ArrayList<Collidable>> collision;

        private EnemiesVSEnvironment(ArrayList<EnemyView> enemies, PlayerView player, ChestView chest, CollisionView collisions) {
            collision = new ArrayList<ArrayList<Collidable>>();
            collision.addAll(enemiesVSChest(enemies, chest));
            collision.addAll(enemiesVSCollisions(enemies, collisions));
            collision.addAll(enemiesVSPlayer(enemies, player));
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

        private ArrayList<ArrayList<Collidable>> enemiesVSPlayer(ArrayList<EnemyView> enemies, PlayerView player) {
            ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
            for (EnemyView e : enemies) {
                tCollision.addAll(collisionDetector(e, player));
            }
            return tCollision;
        }

    }

    public class PlayerVSEnvironment {
        ArrayList<ArrayList<Collidable>> collision;

        private PlayerVSEnvironment(PlayerView player, ArrayList<EnemyView> enemies, ChestView chest, CollisionView collisions) {
            collision = new ArrayList<ArrayList<Collidable>>();
            collision.addAll(playerVSChest(player, chest));
            collision.addAll(playerVSCollision(player, collisions));
            collision.addAll(playerVSEnemies(player, enemies));
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
            return tCollision;
        }
    }

    public class ProjectilesVSEverything {
        ArrayList<ArrayList<Collidable>> collision;

        private ProjectilesVSEverything(PlayerView player, ArrayList<EnemyView> enemies, ArrayList<ProjectileView> projectiles) {
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

    public class Resolve {

        private Resolve (ArrayList<ArrayList<Collidable>> collision, Type t) {
            if (t.equals(Type.ENVIRONMENT)) {
                resolve_1_Enemies(collision);
                resolve_2_Player(collision);
            } else if (t.equals(Type.PROJECTILE)) {
                resolve_3_Projectiles(collision);
            }
        }

        private void resolve_1_Enemies (ArrayList<ArrayList<Collidable>> collision) {
            for (Collidable c : collision.get(0)) {
                if (c instanceof EnemyView) {
                    ((EnemyView) c).setCollision();
                    ((EnemyView) c).update();
                }
            }
        }

        private void resolve_2_Player (ArrayList<ArrayList<Collidable>> collision) {
            for (Collidable c : collision.get(0)) {
                if (c instanceof PlayerView) {
                    ((PlayerView) c).setCollision();
                    ((PlayerView) c).updatePosition();
                }
            }
        }

        private void resolve_3_Projectiles (ArrayList<ArrayList<Collidable>> collision) {
            int i = 0;
            for (Collidable c : collision.get(0)) {
                if (c instanceof ProjectileView) {
                    if(collision.get(1).get(i) instanceof CharacterView) {
                        ((CharacterView) collision.get(1).get(i)).hit((ProjectileView) c);
                    }
                    ((ProjectileView) c).dispose();
                }
                i++;
            }
        }
    }
}