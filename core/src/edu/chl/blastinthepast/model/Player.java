package edu.chl.blastinthepast.model;

import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 21/04/15.
 */
public class Player {

    private int health;
    private int movementSpeed;
    private Array<Weapon> weaponArray;
    private Weapon weapon;
    private boolean north, south, west, east = false;
    private Position position;
    private float direction; //Placeholder until we have a Vector2

    /**
     * Default constructor for Player with default movement speed and health.
     */
    public Player() {
        this(200, 100);
    }

    /**
     * Creates a new player character with texture, rectangle and sprite.
     */
    public Player(int movementSpeed, int health) {
        position=new Position(0,0);
        this.movementSpeed = movementSpeed;
        this.health = health;

        weapon = new Weapon(position, direction);
    }



    public int getMovementSpeed() {
        return movementSpeed;
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

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Position getPosition(){
        return position;
    }


    public void move(String direction, float dt) {
        System.out.println(direction);
        float x=position.getX();
        float y=position.getY();
        switch (direction) {
            case "west":
                position.setX(x-movementSpeed*dt);
                break;
            case "east":
                position.setX(x+movementSpeed*dt);
                break;
            case "north":
                position.setY(y+movementSpeed*dt);
                break;
            case "south":
                position.setY(y-movementSpeed*dt);
                break;
            default: break;
        }
    }

    public void addWeapon(Weapon weapon){
        weaponArray.add(weapon);
    }

}
