package edu.chl.blastinthepast.model.player;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.*;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.lang.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Shif on 21/04/15.
 */
public class Player extends Observable implements Character {

    private int health;
    private int movementSpeed;
    private ArrayList<WeaponInterface> weaponArray;
    private WeaponInterface weapon;
    private WeaponFactory weaponFactory;
    private boolean north, south, west, east, shooting;
    private PositionInterface position;
    private int score = 0;
    private boolean playerIsDead = false;
    private PositionInterface prevPos;
    private Vector2 aimVector = new Vector2(1,0);
    private Vector2 movementVector = new Vector2(1, 0);
    private ArrayList<ProjectileInterface> projectiles;
    private int bonusMovementSpeed;
    private boolean blockedWest, blockedEast, blockedNorth, blockedSouth = false;

    /**
     * Default constructor for Player with default movement speed and health.
     */
    public Player() {
        this(200, 10, new Position(0,0));
    }

    /**
     * Creates a new player character with given movement speed and health.
     */
    private Player(int movementSpeed, int health, PositionInterface pos) {
        position = new Position(pos);
        this.movementSpeed = movementSpeed;
        this.health = health;
        weaponFactory = new WeaponFactory();
        weaponArray = new ArrayList<WeaponInterface>();
        weapon = weaponFactory.getWeapon(this, WeaponInterface.WeaponType.AK47);
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
        newWeapon = weaponFactory.getWeapon(this, weapon.getWeaponType());
        weaponArray.add(newWeapon);
        setWeapon(newWeapon);

    }

    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
    }

    public WeaponInterface getCurrentWeapon() {
        return weapon;
    }

    public ArrayList<WeaponInterface> getAllWeapons(){
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

    @Override
    public void addBonusMovementSpeed(int bonusSpeed) {
        bonusMovementSpeed+=bonusSpeed;
    }

    @Override
    public int getBonusMovementSpeed() {
        return bonusMovementSpeed;
    }

    @Override
    public int getTotalMovementSpeed() {
        return movementSpeed+bonusMovementSpeed;
    }

    public void setPosition(int x, int y) {
        position.setPosition(x, y);
    }

    public void setPosition (Position position) {
        this.position = position;
    }

    public void move(float dt) {
        prevPos = new Position(position);
        float x = position.getX();
        float y = position.getY();
        updateMovementVector(dt);
        if (movementVector.len() != 0) {
            position.setX(x + movementVector.x);
        }
        position.setY(y + movementVector.y);
    }

    private void updateMovementVector(float dt) {
        movementVector.set(1, 0);
        if (north) {
            if (east) {
                movementVector.setAngle(45);
            } else if (west) {
                movementVector.setAngle(90+45);
            } else {
                movementVector.setAngle(90);
            }
            movementVector.setLength(movementSpeed * dt);
        } else if (south) {
            if (east) {
                movementVector.setAngle(360-45);
            } else if (west) {
                movementVector.setAngle(180 + 45);
            } else {
                movementVector.setAngle(270);
            }
            movementVector.setLength(movementSpeed * dt);
        } else if (west) {
            movementVector.setAngle(180);
            movementVector.setLength(movementSpeed * dt);
        } else if (east) {
            movementVector.setAngle(0);
            movementVector.setLength(movementSpeed * dt);
        } else {
            movementVector.setLength(0);
        }
    }

    public void update(float dt) {
        if (health <= 0) {
            die();
        }
        weapon.setPosition(position);
        if (!(blockedEast || blockedNorth || blockedSouth || blockedWest)) {
            move(dt);
        }

        if (shooting) {
            shoot();
        }
        for (WeaponInterface w : weaponArray){
            w.resetBonuses();
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

    public void setAimVector(float aimVector){
        this.aimVector.setAngle(aimVector);
    }

    public void calculateDirection(Position mousePoint){
        Vector2 direction=new Vector2(mousePoint.getX()-position.getX(), mousePoint.getY()-position.getY());
        float length=direction.len();
        direction.scl(1 / length);
        setAimVector(direction.angle());
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

    public void resetMovementDirection() {
        north = false;
        south = false;
        west = false;
        east = false;
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

    public void resetBonuses(){
        bonusMovementSpeed=0;
    }

    @Override
    public Vector2 getMovementVector() {
        return movementVector;
    }

    @Override
    public Vector2 getAimVector() {
        return aimVector;
    }

    public void block() {
        if (north) {
            blockedNorth = true;
        }
        if (south) {
            blockedSouth = true;
        }
        if (east) {
            blockedEast = true;
        }
        if (west) {
            blockedWest = true;
        }
    }

    public void unBlock() {
        blockedNorth = false;
        blockedSouth = false;
        blockedEast = false;
        blockedWest = false;
    }

    public void reloadCurrentWeapon(){
        if (weapon.getbulletsLeftInMagazine() < weapon.getMagazineCapacity() && weapon.getTotalBullets() > 0){
            weapon.reload();
        }
    }

    @Override
    public CharacterType getCharacterType() {
        return CharacterType.PLAYER;
    }
}
