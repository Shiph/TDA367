package edu.chl.blastinthepast.model.player;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponFactory;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.*;
import edu.chl.blastinthepast.utils.Rectangle;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.*;
import java.util.ArrayList;
import java.util.Observable;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;


/**
 * Created by Shif on 21/04/15.
 */
public class Player extends Observable implements Character {

    private Vector2 aimDirection = new Vector2(1,0);
    private Rectangle rectangle=new RectangleAdapter();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final int width = 64;
    private final int height = 64;

    /**
     * Default constructor for Player with default movement speed and health.
     */
    public Player() {
        this(200, 10, new Position(0,0));
    }

    /**
     * Creates a new player character with texture, rectangle and sprite.
     */
    private Player(int movementSpeed, int health, PositionInterface pos) {
        position = new Position(pos);
        this.movementSpeed = movementSpeed;
        this.health = health;
        weaponFactory = new WeaponFactory();
        weaponArray = new ArrayList<>();
        weapon = weaponFactory.getWeapon(position, aimVector, movementVector, WeaponInterface.WeaponType.AK47);
        weaponArray.add(weapon);
        projectiles = new ArrayList<>();
        position=pos;
        rectangle.setPosition(position.getX(), position.getY());
        rectangle.setSize(width, height);
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
        newWeapon = weaponFactory.getWeapon(position, aimVector, movementVector, weapon.getWeaponType());
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
        position = new Position(newPosition);
        rectangle.setPosition(position);
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
    public void setPrevPos (Position prevPos) {
        this.prevPos = prevPos;
    }

    public void setPosition(int x, int y) {
        position.setPosition(x, y);
        rectangle.setPosition(position);
    }

    public void setPosition (Position position) {
        this.position = position;
        rectangle.setPosition(position);
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
        rectangle.setPosition(position);
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
            pcs.firePropertyChange("New Projectile", null, p);
        }
    }

    public Vector2 getDirection(){
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
        weapon.getAimVector().set(aimDirection);
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

    public void resetBonuses(){
        bonusMovementSpeed=0;
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
        if (weapon.getbulletsLeftInMagazine()<weapon.getMagazineCapacity() && weapon.getTotalBullets()>0){
            weapon.reload();
        }
    }

    @Override
    public String toString() {
        return "Player";
    }

    @Override
    public boolean isColliding(Collidable c) {
        return rectangle.overlaps(c.getRectangle());
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    public void removeListener (PropertyChangeListener pcl){
        pcs.removePropertyChangeListener(pcl);
    }

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
            movementVector.setLength(getTotalMovementSpeed() * dt);
        } else if (south) {
            if (east) {
                movementVector.setAngle(360-45);
            } else if (west) {
                movementVector.setAngle(180 + 45);
            } else {
                movementVector.setAngle(270);
            }
            movementVector.setLength(getTotalMovementSpeed() * dt);
        } else if (west) {
            movementVector.setAngle(180);
            movementVector.setLength(getTotalMovementSpeed() * dt);
        } else if (east) {
            movementVector.setAngle(0);
            movementVector.setLength(getTotalMovementSpeed() * dt);
        } else {
            movementVector.setLength(0);
        }
    }

    public void setAimVector(float aimVector){
        this.aimVector.setAngle(aimVector);
    }

    public ArrayList<ProjectileInterface> getProjectiles(){
        return projectiles;
    }

    @Override
    public Vector2 getMovementVector() {
        return movementVector;
    }

    @Override
    public Vector2 getAimVector() {
        return aimVector;
    }

    @Override
    public CharacterType getCharacterType() {
        return CharacterType.PLAYER;
    }
}