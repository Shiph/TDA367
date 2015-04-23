package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.Player;

/**
 * Created by qwerty458 on 4/23/15.
 */
public class CollisionDetection {
    private final class PlayerCollisionDetection {

        boolean directionDetector(int direction, TiledMap map, Player player) throws Exception {
            Array<Rectangle> rectangles = mapToRectangles(map);
            for(int i = rectangles.size; i != 0; i--) {
                if (player.getRectangle().overlaps(rectangles.get(i)))
                    return true;
            }
            return false;
        }
    }

    private final class EnemyCollisionDetection {

    }

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
