package edu.chl.blastinthepast.utils;

/**
 * Created by jonas on 2015-04-23.
 */
public class Position implements PositionInterface {
    private float x;
    private float y;

    public Position(float x, float y){
        this.x=x;
        this.y=y;
    }

    public Position(PositionInterface pos){
        x=pos.getX();
        y=pos.getY();
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x){
        this.x=x;
    }

    public void setY(float y){
        this.y=y;
    }

    public void setPos(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void setPos(PositionInterface pos){
        x=pos.getX();
        y=pos.getY();
    }

    public boolean equals(PositionInterface pos) {
        return (pos.getX() == this.getX() && pos.getY() == this.getY());
    }

    public int hashCode() {
        return (int)(x * 509 + y * 643);
    }

    public String toString(){
        return "X: "+x+" Y: "+y;
    }
}
