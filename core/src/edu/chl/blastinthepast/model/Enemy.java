package edu.chl.blastinthepast.model;

import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Mattias on 15-04-21.
 */
public class Enemy implements Character{

    private int health;
    private int movementSpeed;
    private Position position;
    private Weapon weapon;

    /**
     * Default constructor for Enemy with default movement speed and health.
     */
    public Enemy() {
        this(150, 100);
    }

    public Enemy(int movementSpeed, int health) {
        this.movementSpeed = movementSpeed;
        this.health = health;
    }

    public void move(String direction, float dt) {}

    public void setMovementSpeed(int newSpeed) {
        movementSpeed = newSpeed;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int getHealth() {
        return health;
    }

    public Position getPosition(){
        return position;
    }

    public Weapon getWeapon() {
        return weapon;
    };

    public int getMovementSpeed() {
        return movementSpeed;
    }

}
