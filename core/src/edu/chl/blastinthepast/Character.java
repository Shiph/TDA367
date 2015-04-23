package edu.chl.blastinthepast;

import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.Collidable;

/**
 * Created by Mattias on 15-04-23.
 */
public interface Character extends Collidable {

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

    @Override
    public Rectangle getRectangle();

    @Override
    public void setRectangle(Rectangle rectangle);
}