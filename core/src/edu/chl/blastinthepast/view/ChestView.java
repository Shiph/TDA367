package edu.chl.blastinthepast.view;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Chest;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 5/4/15.
 */
public class ChestView implements Environment {
    private ArrayList<Rectangle> rectangle;
    private Chest chest;

    public ChestView () {
        rectangle = new ArrayList<Rectangle>();

        //No ChestObjectLayer exists in the map yet.
        //rectangle.addAll(mapToRectangles(new TideMapLoader().load("GrassTestMap1.tmx")));
    }

    @Override
    public ArrayList<Rectangle> getRectangles() {
        return rectangle;
    }

    @Override
    public void setRectangles(ArrayList<Rectangle> rectangles) {

    }

    private ArrayList<Rectangle> mapToRectangles(TiledMap map) {
        MapLayer objectLayer = map.getLayers().get("ChestObjectLayer");
        MapObjects objects = objectLayer.getObjects();
        Array<RectangleMapObject> rectangleObjects = objects.getByType(RectangleMapObject.class);
        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
        for(int i = 0; i < rectangleObjects.size; i++) {
            rectangles.add(rectangleObjects.get(i).getRectangle());
        }
        return rectangles;
    }
}
