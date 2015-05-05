package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Chest;

/**
 * Created by qwerty458 on 5/4/15.
 */
public class ChestView implements Collidable {
    private Array<Rectangle> rectangle;
    private Chest chest;

    public ChestView () {
        TiledMap tiledMap = new TideMapLoader().load("GrassTestMap1.tmx");
        rectangle = new Array<Rectangle>();
        rectangle.addAll(mapToRectangles(tiledMap));
    }

    @Override
    public Array<Rectangle> getRectangles() {
        return rectangle;
    }

    @Override
    public void setRectangles(Array<Rectangle> rectangles) {

    }

    private Array<Rectangle> mapToRectangles(TiledMap map) {
        MapLayer objectLayer = map.getLayers().get("ChestObjectLayer");
        MapObjects objects = objectLayer.getObjects();
        Array<RectangleMapObject> rectangleObjects = objects.getByType(RectangleMapObject.class);
        Array<Rectangle> rectangles = new Array<Rectangle>();
        for(int i = 0; i < rectangleObjects.size; i++) {
            rectangles.add(rectangleObjects.get(i).getRectangle());
        }
        return rectangles;
    }
}
