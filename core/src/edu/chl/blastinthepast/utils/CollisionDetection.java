package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.view.Collidable;

/**
 * Created by qwerty458 on 4/23/15.
 */
public final class CollisionDetection {

    public boolean collisionDetector(Collidable c1, Collidable c2) {
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
}