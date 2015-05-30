package edu.chl.blastinthepast.utils;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * Created by jonas on 2015-05-21.
 *
 * Adaptor for the LibGDX Rectangle class.
 */
public class RectangleAdapter implements Rectangle {

    private com.badlogic.gdx.math.Rectangle rectangle = new com.badlogic.gdx.math.Rectangle();

    public RectangleAdapter(){}

    public RectangleAdapter(float width, float height){
        rectangle.setSize(width, height);
    }

    public RectangleAdapter(float x, float y, float width, float height){
        rectangle.set(x, y, width, height);
    }


    @Override
    public float area() {
        return rectangle.area();
    }

    @Override
    public boolean contains(float x, float y) {
        return rectangle.contains(x, y);
    }

    @Override
    public boolean contains(Rectangle rectangle) {
        com.badlogic.gdx.math.Rectangle rect= new com.badlogic.gdx.math.Rectangle();
        rect.set(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        return this.rectangle.contains(rect);
    }

    @Override
    public float getAspectRatio() {
        return rectangle.getAspectRatio();
    }

    @Override
    public Point getCenter() {
        Vector2 v2 = new Vector2();
        v2 = rectangle.getCenter(v2);
        Point pos = new Point(0, 0);
        pos.setLocation(v2.x, v2.y);
        return pos;
    }

    @Override
    public float getHeight() {
        return rectangle.getHeight();
    }
    @Override
    public float getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public float getX() {
        return rectangle.getX();
    }

    @Override
    public float getY() {
        return rectangle.getY();
    }


    @Override
    public boolean overlaps(Rectangle r) {
        com.badlogic.gdx.math.Rectangle rect = new com.badlogic.gdx.math.Rectangle();
        rect.set(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        return rectangle.overlaps(rect);
    }

    @Override
    public float perimeter() {
        return rectangle.perimeter();
    }

    @Override
    public void set(float x, float y, float width, float height) {
        rectangle.set(x, y, width, height);
    }


    @Override
    public void set(Rectangle rect) {
        rectangle.set(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        rectangle.setCenter((float)rect.getCenter().getX(), (float)rect.getCenter().getY());
    }

    @Override
    public void setCenter(float x, float y) {
        rectangle.setCenter(x, y);
    }


    @Override
    public void setHeight(float height) {
        rectangle.setHeight(height);
    }

    @Override
    public void setPosition(float x, float y) {
        rectangle.setPosition(x, y);
    }

    @Override
    public void setSize(float sizeXY) {
        rectangle.setSize(sizeXY);
    }

    @Override
    public void setSize(float width, float height) {
        rectangle.setSize(width, height);
    }

    @Override
    public void setWidth(float width) {
        rectangle.setWidth(width);
    }

    @Override
    public void setX(float x) {
        rectangle.setX(x);
    }

    @Override
    public void setY(float y) {
        rectangle.setY(y);
    }
}
