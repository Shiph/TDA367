package edu.chl.blastinthepast.utils;


import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.Enemy;
import edu.chl.blastinthepast.Player;

/**
 * Created by qwerty458 on 4/23/15.
 */
public final class CollisionDetection {
    private final class PlayerCollisionDetection {

        /**
         * Checks whether the attempted movement results in collision between the player and the rectangles int the CollisionObjectLayer in the map.
         * @param map The map which the player is moving in.
         * @param player The player whose Rectangle is to be compared to the rectangles in the map.
         * @return True if the player collides with a rectangle in the map, false if not.
         */
        public boolean collisionDetector(TiledMap map, Player player) {
            Array<Rectangle> rectangles = mapToRectangles(map);
            for(int i = rectangles.size; i != 0; i--) {
                if (player.getRectangle().overlaps(rectangles.get(i))) {
                    return true;
                }
            }
            return false;
        }
    }

    private final class EnemyCollisionDetection {

        /**
         * Checks whether the attempted movement results in collision between the enemy and the rectangles int the CollisionObjectLayer in the map.
         * @param map The map which the enemy is moving in.
         * @param enemy The enemy whose Rectangle is to be compared to the rectangles in the map.
         * @return True if the enemy collides with a rectangle in the map, false if not.
         */
        public boolean collisionDetector(TiledMap map, Enemy enemy) {
            Array<Rectangle> rectangles = mapToRectangles(map);
            for(int i = rectangles.size; i != 0; i--) {
                if (enemy.getRectangle().overlaps(rectangles.get(i))) {
                    return true;
                }
            }
            return false;
        }
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
