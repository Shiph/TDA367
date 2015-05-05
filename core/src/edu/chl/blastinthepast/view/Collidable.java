package edu.chl.blastinthepast.view;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by qwerty458 on 4/23/15.
 */
public interface Collidable{
    public ArrayList<Rectangle> getRectangles();
    public void setRectangles(ArrayList<Rectangle> rectangles);
}
