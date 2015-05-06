package edu.chl.blastinthepast.utils;

/**
 * Created by Shif on 06/05/15.
 */
public interface PositionInterface {

    public float getX();
    public float getY();
    public void setX(float x);
    public void setY(float y);
    public void setPos(float x, float y);
    public void setPos(Position pos);
    public String toString();

}
