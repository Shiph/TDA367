package edu.chl.blastinthepast.model;

import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Mattias on 15-04-21.
 */
public class Enemy extends Actor {

    private int health;
    private int movementSpeed;
    private Position position;

    /**
     * Default constructor for Enemy with default movement speed and health.
     */
    public Enemy() {
        this(150, 100);
    }

    /**
     * Creates a new enemy character with texture, rectangle and sprite.
     */
    public Enemy(int movementSpeed, int health) {

    }


    /**
     * Sets the players new movement speed.
     * @param newSpeed
     */
    public void setMovementSpeed(int newSpeed) {
        movementSpeed = newSpeed;
    }

    /**
     * Sets the players new health.
     * @param newHealth
     */
    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public Position getPosition(){
        return position;
    }

}
