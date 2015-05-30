package edu.chl.blastinthepast.model.position;

/**
 * Created by jonas on 2015-04-23.
 */
public class Position implements PositionInterface {
    private float x;
    private float y;

    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Position(PositionInterface pos){
        x = pos.getX();
        y = pos.getY();
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public Position getPosition () {
        return new Position(x,y);
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y=y;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setPosition(PositionInterface pos){
        x = pos.getX();
        y = pos.getY();
    }

    public boolean overlaps(PositionInterface pos) {
        int margin = 50;
        return ((Math.abs(this.getPosition().getX() - pos.getX()) < margin) &&
                (Math.abs(this.getPosition().getY() - pos.getY()) < margin));
    }

    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof Position){
                Position pos = (Position) obj;
                return (pos.getX() == this.getX() && pos.getY() == this.getY());
            }
        }
        return false;
    }

    public int hashCode() {
        return (int)(x * 509 + y * 643);
    }

    public String toString(){
        return "X: " + x + " Y: " + y;
    }
}
