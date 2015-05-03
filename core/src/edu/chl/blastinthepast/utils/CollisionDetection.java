package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.view.Collidable;

/**
 * Created by qwerty458 on 4/23/15.
 */
public final class CollisionDetection {

    public boolean collisionDetector (Collidable c1, Collidable c2) {
        Array<Rectangle> r1 = new Array<Rectangle>();
        Array<Rectangle> r2 = new Array<Rectangle>();

        //Assigning c1 to r1
        if (arrayChecker(c1)) {
            r1.add(c1.getRectangle());
        } else if (!arrayChecker(c1)) {
            r1.addAll(c1.getRectangle());
        }

        //Assigning c2 to r2
        if (arrayChecker(c2)) {
            r2.add(c2.getRectangle());
        } else if (!arrayChecker(c2)) {
            r2.addAll(c2.getRectangle());
        }

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

    private boolean arrayChecker(Collidable c) {
        if(c.getRectangle().getClass().equals(Rectangle.class)) {
            return true;
        } else if (c.getRectangle().getClass().equals(Array.class)) {
            return false;
        }
        return false;
    }



    /**
     * Checks whether the attempted movement results in collision between the character and the rectangles int the CollisionObjectLayer in the map.
     * @param map The map which the player is moving in.
     * @param characterView The character whose Rectangle is to be compared to the rectangles in the map.
     * @return True if the player collides with a rectangle in the map, false if not.
     */
    /*
    public boolean characterCollisionDetector(TiledMap map, CharacterView characterView) {
        Array<Rectangle> rectangles = mapToRectangles(map);
        for(int i = rectangles.size; i != 0; i--) {
            if (characterView.getRectangle().overlaps(rectangles.get(i))) {
                return true;
            }
        }
        return false;
    }
    */


    private Array<Rectangle> mapToRectangles(TiledMap map) {
        MapLayer objectLayer = map.getLayers().get("collisionObjectLayer");
        MapObjects objects = objectLayer.getObjects();
        Array<RectangleMapObject> rectangleObjects = objects.getByType(RectangleMapObject.class);
        Array<Rectangle> rectangles = new Array<Rectangle>();
        for(int i = 0; i < rectangleObjects.size; i++) {
            rectangles.add(rectangleObjects.get(i).getRectangle());
        }
        return rectangles;
    }
}
