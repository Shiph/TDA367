package edu.chl.blastinthepast.utils.collisiondetection;


import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.view.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by qwerty458 on 4/23/15.
 */
public class CollisionDetection {

    public enum Type {
        ENVIRONMENT, PROJECTILE
    }

    ArrayList<ArrayList<Collidable>> collision;

    public CollisionDetection () {
        clearAndInitializeCollision();
    }

    public CollisionDetection (ArrayList<EnemyView> enemies, PlayerView player, ArrayList<ProjectileView> projectiles, ChestView chest, CollisionView collisions) {
        new EnemyCD(enemies, player, chest, collisions);
        new PlayerCD(player, enemies, chest, collisions);
        new ProjectileCD(player, enemies, projectiles);
    }


    public ArrayList<ArrayList<Collidable>> collisionDetector(Collidable c1, Collidable c2) {
        ArrayList<ArrayList<Collidable>> collisionCDr = new ArrayList<>(2);
        collisionCDr.add(new ArrayList<Collidable>());
        collisionCDr.add(new ArrayList<Collidable>());

        ArrayList<Rectangle> r1 = new ArrayList<Rectangle>();
        ArrayList<Rectangle> r2 = new ArrayList<Rectangle>();

        r1.addAll(c1.getRectangles());
        r2.addAll(c2.getRectangles());

        //Checking for overlap between r1 and r2
        for (Rectangle r : r1) {
            for (Rectangle rr : r2) {
                if (r.overlaps(rr)) {
                    collisionCDr.get(0).add(c1);
                    collisionCDr.get(1).add(c2);
                }
            }
        }
        return collisionCDr;
    }

    public ArrayList<ArrayList<Collidable>> clean (ArrayList<ArrayList<Collidable>> c) {
        ArrayList<ArrayList<Collidable>> tCollision = new ArrayList<ArrayList<Collidable>>();
        for (ArrayList<Collidable> e : c) {
            e.removeAll(Collections.singleton(null));
            if (e.size() > 0) {
                tCollision.add(e);
            }
        }
        return tCollision;
    }

    public void clearAndInitializeCollision () {
        collision.clear();
        ArrayList<ArrayList<Collidable>> collision = new ArrayList<>(2);
        collision.add(new ArrayList<Collidable>());
        collision.add(new ArrayList<Collidable>());
    }

        /*
    class Resolve {

        public Resolve(ArrayList<ArrayList<Collidable>> collisionR, Type t) {
            if (t.equals(Type.ENVIRONMENT)) {
                //System.out.println("ENVIRONMENT");
                resolve_1_Enemies(collisionR);
                resolve_2_Player(collisionR);
            } else if (t.equals(Type.PROJECTILE)) {
                //System.out.println("PROJECTILE");
                resolve_3_Projectiles(collisionR);
            }
        }

        private void resolve_1_Enemies(ArrayList<ArrayList<Collidable>> collision) {
            //System.out.println("resolve_1");
            for (Collidable c : collision.get(0)) { //this is where shit goes wrong
                if (c instanceof EnemyView) {
                    ((EnemyView) c).setCollision();
                    ((EnemyView) c).update();
                }
            }
        }

        private void resolve_2_Player(ArrayList<ArrayList<Collidable>> collision) {
            for (Collidable c : collision.get(0)) {
                if (c instanceof PlayerView) {
                    ((PlayerView) c).setCollision();
                    ((PlayerView) c).updatePosition();
                }
            }
        }

        private void resolve_3_Projectiles(ArrayList<ArrayList<Collidable>> collision) {
            int i = 0;
            //System.out.println(collisionEVSE.get(0).get(0));
            for (Collidable c : collision.get(0)) {
                System.out.println("for_1");
                if (c instanceof ProjectileView) {
                    System.out.println("if_1");
                    if (collision.get(1).get(i) instanceof CharacterView) {
                        System.out.println("if_2");
                        ((CharacterView) collision.get(1).get(i)).hit((ProjectileView) c);
                    }
                    //removeProjectile(((ProjectileView) c).getProjectile());
                }
                i++;
            }
        }
    }
    */
}