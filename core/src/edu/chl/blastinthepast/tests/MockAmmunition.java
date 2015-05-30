package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.utils.Rectangle;

/**
 * Created by Shif on 2015-05-30.
 */
public class MockAmmunition implements AmmunitionInterface {

    public PositionInterface pos;

    public MockAmmunition() {
        pos  = new MockPosition();
    }

    @Override
    public PositionInterface getPosition() {
        return null;
    }

    @Override
    public void setPosition(PositionInterface newPosition) {
        pos = newPosition;
    }

    @Override
    public ProjectileInterface getType() {
        return null;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public boolean isColliding(Collidable c) {
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

}
