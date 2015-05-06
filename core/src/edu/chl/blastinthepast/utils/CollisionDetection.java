package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.Player;
import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 4/23/15.
 */
public final class CollisionDetection {
        
    public void update(ArrayList<EnemyView> enemies, PlayerView player, ArrayList<ProjectileView> projectiles, ChestView chest, CollisionView collisions) {
        ArrayList<ArrayList<Collidable>> collision = new ArrayList<ArrayList<Collidable>>();

        collision.addAll(new EnemiesVSEnvironment(enemies, player, chest, collisions).collision);
        collision.addAll(new PlayerVSEnvironment(player, enemies, chest, collisions).collision);
        collision.addAll(new CharacterVSCharacter(player, enemies).collision);
        collision.addAll(new ProjectilesVSCharacters(player, enemies, projectiles).collision);

        new Resolve(collision);
    }

    public static ArrayList<ArrayList<Collidable>> collisionDetector(Collidable c1, Collidable c2) {
        ArrayList<ArrayList<Collidable>> collision = new ArrayList<ArrayList<Collidable>>();

        ArrayList<Rectangle> r1 = new ArrayList<Rectangle>();
        ArrayList<Rectangle> r2 = new ArrayList<Rectangle>();

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

    private class PlayerVSEnvironment {
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

    private class CharacterVSCharacter {
        ArrayList<ArrayList<Collidable>> collision;

        private CharacterVSCharacter(PlayerView player, ArrayList<EnemyView> enemies) {
            collision = new ArrayList<ArrayList<Collidable>>();

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

        private Resolve (ArrayList<ArrayList<Collidable>> collision) {
            resolve_1_Enemies(collision);
            resolve_2_Player(collision);
            resolve_3_Projectiles(collision);
        }

        private void resolve_1_Enemies (ArrayList<ArrayList<Collidable>> collision) {
            for (Collidable c : collision.get(0)) {
                if (c instanceof EnemyView) {
                    ((EnemyView) c).setCollision();
                }
            }
        }

        private void resolve_2_Player (ArrayList<ArrayList<Collidable>> collision) {
            for (Collidable c : collision.get(0)) {
                if (c instanceof PlayerView) {
                    ((PlayerView) c).setCollision();
                }
            }
        }

        private void resolve_3_Projectiles (ArrayList<ArrayList<Collidable>> collision) {
            int i = 0;
            for (Collidable c : collision.get(0)) {
                if (c instanceof ProjectileView) {
                    ((CharacterView) collision.get(1).get(i)).hit((ProjectileView) c);
                }
                i++;
            }
        }
    }
}