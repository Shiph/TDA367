package edu.chl.blastinthepast.model;

/**
 * Created by Mattias on 15-04-23.
 */
public interface Character {

    public void move(float dt);

    public void setMovementSpeed(int newSpeed);

    public int getMovementSpeed();

    public Weapon getWeapon();

    public int getHealth();

    public void setHealth(int newHealth);

}