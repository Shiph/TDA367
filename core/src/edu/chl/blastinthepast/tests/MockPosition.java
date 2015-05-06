package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public class MockPosition implements PositionInterface {

    private float x;
    private float y;

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
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
    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setPos(PositionInterface pos) {
        x = pos.getX();
        y = pos.getY();
    }

    @Override
    public boolean equals(PositionInterface pos) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
