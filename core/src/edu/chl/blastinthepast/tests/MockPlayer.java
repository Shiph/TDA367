package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.entities.Character;
import edu.chl.blastinthepast.model.entities.ProjectileInterface;
import edu.chl.blastinthepast.model.entities.WeaponInterface;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.util.ArrayList;

/**
 * Created by Shif on 11/05/15.
 */
public class MockPlayer implements Character {

    public PositionInterface position = new Position(0,0);
    public int movementSpeed = 100;
    public WeaponInterface weapon;
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
    public WeaponInterface getCurrentWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
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
    public ArrayList<ProjectileInterface> getProjectiles() {
        return null;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public PositionInterface getPrevPos() {
        return null;
    }

    @Override
    public void setPosition(PositionInterface newPosition) {

    }


}
