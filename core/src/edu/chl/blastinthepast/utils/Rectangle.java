package edu.chl.blastinthepast.utils;

import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by jonas on 2015-05-21.
 */
public interface Rectangle {
    float area();
    boolean contains(float x, float y);
    boolean	contains(Rectangle rectangle);
    boolean	equals(java.lang.Object obj);
    float getAspectRatio();
    PositionInterface getCenter();
    float getHeight();
    float getWidth();
    float getX();
    float getY();
    int	hashCode();
    boolean	overlaps(Rectangle r);
    float perimeter();
    void set(float x, float y, float width, float height);
    void set(Rectangle rect);
    void setCenter(float x, float y);
    void setHeight(float height);
    void setPosition(float x, float y);
    void setPosition(PositionInterface pos);
    void setSize(float sizeXY);
    void setSize(float width, float height);
    void setWidth(float width);
    void setX(float x);
    void setY(float y);
    String toString();
}
