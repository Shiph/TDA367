package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

import java.util.ArrayList;

/**
 * Created by Shif on 21/04/15.
 */
public class Player implements Character {

    private int health;
    private int movementSpeed;
    private ArrayList<Weapon> weaponArray;
    private Weapon weapon;
    private boolean north, south, west, east = false;
    private Position position;
    private Vector2 aimDirection = new Vector2(1,0);
    private String movementDirection;

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
        position = new Position(0,0);
        this.movementSpeed = movementSpeed;
        this.health = health;
        weapon = new Weapon(position, aimDirection);
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

    public int getHealth() {
        return health;
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

    public void setPosition(int x, int y) {
        position.setPos(x, y);
    }

    public void move(float dt) {
        float x = position.getX();
        float y = position.getY();
        switch (movementDirection) {
            case "west":
                position.setX(x - movementSpeed * dt);
                break;
            case "east":
                position.setX(x + movementSpeed * dt);
                break;
            case "north":
                position.setY(y + movementSpeed * dt);
                break;
            case "south":
                position.setY(y - movementSpeed * dt);
                break;
        }
    }

    public void update() {
        weapon.setPosition(position);
    }

    public void act(String action, float dt){
        switch (action) {
            case "shoot":
                //weapon.fire();
                break;
            case "reload":
                weapon.reload();
                break;
            case "use":
                break;
        }
    }

    public void addWeapon(Weapon weapon){
        weaponArray.add(weapon);
    }

    public Vector2 getAimDirection(){
        return aimDirection;
    }

    public void setAimDirection(float aimDirection){
        this.aimDirection.setAngle(aimDirection);
    }

    public void calculateDirection(Position mousePoint){
        Vector2 direction=new Vector2(mousePoint.getX()-position.getX(), mousePoint.getY()-position.getY());
        float length=direction.len();
        direction.scl(1/length);
        setAimDirection(direction.angle());
        System.out.println(direction.angle());
    }
    public void setMovementDirection(String movementDirection) {
        this.movementDirection = movementDirection;
    }
}
