package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Shif on 21/04/15.
 */
public class Player extends Observable implements Character {

    private int health;
    private int movementSpeed;
    private ArrayList<Weapon> weaponArray;
    private Weapon weapon;
    private boolean north, south, west, east, shooting;
    private PositionInterface position;
    private Position prevPos;
    private Vector2 aimDirection = new Vector2(1,0);
    private ArrayList<ProjectileInterface> projectiles;
    /**
     * Default constructor for Player with default movement speed and health.
     */
    public Player() {
        this(200, 100, new Position(0,0));
    }

    /**
     * Creates a new player character with texture, rectangle and sprite.
     */
    public Player(int movementSpeed, int health, PositionInterface pos) {
        position = new Position(pos);
        this.movementSpeed = movementSpeed;
        this.health = health;
        weaponArray = new ArrayList<Weapon>();
        weapon = new AK47(position, aimDirection);
        weaponArray.add(weapon);
        projectiles = new ArrayList<ProjectileInterface>();
        position=pos;
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

    public void addWeapon(Weapon weapon) {
        weaponArray.add(weapon);
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public PositionInterface getPosition(){
        return position;
    }

    public Position getPrevPos() {
        return prevPos;
    }

    public void setPrevPos (Position prevPos) {
        this.prevPos = prevPos;
    }

    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }

    public void setPosition (Position position) {
        this.position = position;
    }

    public void move(float dt) {
        float x = position.getX();
        float y = position.getY();
        if (west) {
            position.setX(x - movementSpeed * dt);
        }
        if (east) {
            position.setX(x + movementSpeed * dt);
        }
        if (north) {
            position.setY(y + movementSpeed * dt);
        }
        if (south) {
            position.setY(y - movementSpeed * dt);
        }
    }

    public void update(float dt) {
        weapon.setPosition(position);
        move(dt);
        if (shooting) {
            shoot();
        }
    }

    public void isShooting(boolean shoot){
        shooting=shoot;
    }

    public void shoot(){
        ProjectileInterface p=weapon.pullTrigger();
        if (p!=null){
            projectiles.add(p);
            setChanged();
            notifyObservers(p);
        }
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
        direction.scl(1 / length);
        setAimDirection(direction.angle());
    }

    public void setMovementDirection(String movementDirection){
        switch (movementDirection) {
            case "north":
                north = !north;
                break;
            case "south":
                south = !south;
                break;
            case "west":
                west = !west;
                break;
            case "east":
                east = !east;
                break;
        }
    }

    public boolean isMovingNorth(){
        return north;
    }

    public boolean isMovingSouth(){
        return south;
    }

    public boolean isMovingWest(){
        return west;
    }

    public boolean isMovingEast(){
        return east;
    }

    public ArrayList<ProjectileInterface> getProjectiles(){
        return projectiles;
    }

}
