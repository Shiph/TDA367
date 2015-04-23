package edu.chl.blastinthepast;

/**
 * Created by Mattias on 15-04-23.
 */
public interface Character {

    /**
     *  This is called upon from the controller which tells which direction the character should move.
     */
    public void move();

    /**
     *
     * @param newSpeed
     */
    public void setMovementSpeed(int newSpeed);

    /**
     *
     * @return
     */
    public int getMovementSpeed();

    /**
     *
     * @return
     */
    public Weapon getWeapon();

}