package edu.chl.blastinthepast.model.entities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Shif on 21/04/15.
 */
public class Player extends Observable implements Character {

    private int health;
    private int movementSpeed;
    private ArrayList<WeaponInterface> weaponArray;
    private WeaponInterface weapon;
    private boolean north, south, west, east, shooting;
    private PositionInterface position;
    private int score = 0;
    private boolean playerIsDead = false;
    private PositionInterface prevPos;
    private Vector2 aimDirection = new Vector2(1,0);
    private ArrayList<ProjectileInterface> projectiles;
    private PowerUpDecorator nextPowerUp;
    private int bonusMovementSpeed = 0;


    /**
     * Default constructor for Player with default movement speed and health.
     */
    public Player() {
        this(200, 1000000, new Position(0,0));
    }

    /**
     * Creates a new player character with texture, rectangle and sprite.
     */
    public Player(int movementSpeed, int health, PositionInterface pos) {
        position = new Position(pos);
        this.movementSpeed = movementSpeed;
        this.health = health;
        weaponArray = new ArrayList<WeaponInterface>();
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

    public void addWeapon(WeaponInterface weapon) {
        WeaponInterface newWeapon;
        switch(weapon.toString()) {
            case "AK47":
                newWeapon = new AK47(position,aimDirection);
                weaponArray.add(newWeapon);
                setWeapon(newWeapon);
                break;
            case "Magnum":
                newWeapon = new Magnum(position,aimDirection);
                weaponArray.add(newWeapon);
                setWeapon(newWeapon);
                break;
            default:
                break;
        }
    }

    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
    }

    public WeaponInterface getCurrentWeapon() {
        return weapon;
    }

    public ArrayList<WeaponInterface> getWeapons(){
        return weaponArray;
    }

    public ArrayList<WeaponInterface> getWeaponArray(){
        return weaponArray;
    }

    public PositionInterface getPosition(){
        return position;
    }

    @Override
    public PositionInterface getPrevPos() {
        return prevPos;
    }

    @Override
    public void setPosition(PositionInterface newPosition) {
        position=new Position(newPosition);
    }

    public void setPrevPos (Position prevPos) {
        this.prevPos = prevPos;
    }

    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }

    public void setPosition (Position position) {
        this.position.setPosition(position);
    }

    public void move(float dt) {
        prevPos = new Position(position);
        float x = position.getX();
        float y = position.getY();
        if (west) {
            position.setX(x - getActualMovementSpeed() * dt);
        }
        if (east) {
            position.setX(x + getActualMovementSpeed() * dt);
        }
        if (north) {
            position.setY(y + getActualMovementSpeed() * dt);
        }
        if (south) {
            position.setY(y - getActualMovementSpeed() * dt);
        }
    }

    public void update(float dt) {
        if (health <= 0) {
            die();
        }
        for (WeaponInterface w : weaponArray){
            weapon.setPosition(position);
        }
        move(dt);
        if (shooting) {
            shoot();
        }
        resetBonuses();
    }


    public void die() {
        setChanged();
        if(!playerIsDead) {
            playerIsDead = true;
            notifyObservers("player is kill");
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
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

    public void setNextPowerUp(PowerUpDecorator p){
        nextPowerUp = p;
    }

    public PowerUpDecorator getNextPowerUp(){
        return nextPowerUp;
    }

    public void addBonusMovementSpeed(int bonusMovementSpeed){
        this.bonusMovementSpeed+=bonusMovementSpeed;
    }

    public void resetBonuses(){
        bonusMovementSpeed=0;
        for (WeaponInterface w : weaponArray){
            w.resetBonuses();
        }
    }

    public int getActualMovementSpeed(){
        return movementSpeed+bonusMovementSpeed;
    }

}
