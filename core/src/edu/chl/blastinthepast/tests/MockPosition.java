package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public class MockPosition implements PositionInterface {

    private float x;
    private float y;

    public MockPosition(){
        this(0,0);
    }

    public MockPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setPosition(PositionInterface pos) {
        x = pos.getX();
        y = pos.getY();
    }

    @Override
    public boolean equals(PositionInterface pos) {
        return (pos.getX() == x && pos.getY() == y);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "X: "+x+" Y: "+y;
    }

}
