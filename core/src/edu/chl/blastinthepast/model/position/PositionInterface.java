package edu.chl.blastinthepast.model.position;

/**
 * Created by Shif on 06/05/15.
 */
public interface PositionInterface {

    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
    void setPosition(float x, float y);
    void setPosition(PositionInterface pos);
    String toString();
    boolean overlaps(PositionInterface pos);

}
