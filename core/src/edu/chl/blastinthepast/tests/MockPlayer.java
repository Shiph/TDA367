package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.*;
import edu.chl.blastinthepast.model.Character;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 11/05/15.
 */
public class MockPlayer implements Character {

    public PositionInterface position = new Position(0,0);
    public int movementSpeed = 100;
    public MockWeapon weapon;
    private int health = 100;

    @Override
    public void move(float dt) {
        position.setX(position.getX() + movementSpeed * dt);
    }

    @Override
    public void setMovementSpeed(int newSpeed) {
        this.movementSpeed = newSpeed;
    }

    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public WeaponInterface getWeapon() {
        return weapon;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

}
