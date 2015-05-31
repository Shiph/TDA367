package edu.chl.blastinthepast.model.player;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponFactory;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.weapon.WeaponTypeEnum;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.*;
import java.util.ArrayList;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.position.PositionInterface;


/**
 * Created by Shif on 21/04/15.
 */
public class Player extends Character {

    private Vector2 aimDirection = new Vector2(1,0);
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final int width = 32;
    private final int height = 32;
    private ArrayList<WeaponInterface> weaponArray;
    private WeaponFactory weaponFactory;
    private boolean north, south, west, east, shooting;
    private int score = 0;
    private boolean blockedWest, blockedEast, blockedNorth, blockedSouth = false;

    /**
     * Default constructor for Player with default movement speed and health.
     */
    public Player(PositionInterface position) {
        this(200, 10, new Position(position));
    }

    /**
     * Creates a new player character with texture, rectangle and sprite.
     */
    private Player(int movementSpeed, int health, PositionInterface pos) {
        setPosition(new Position(pos));
        setMovementSpeed(movementSpeed);
        setHealth(health);
        weaponFactory = new WeaponFactory();
        weaponArray = new ArrayList<>();
        setWeapon(weaponFactory.getWeapon(getPosition(), getAimVector(), getMovementVector(), WeaponTypeEnum.AK47));
        weaponArray.add(getWeapon());
        setPosition(pos);
        setRectangle(pos);
        getRectangle().setSize(width, height);
    }

    @Override
    /**
     * Updates the movement vector based on delta time and moves the position and rectangle to where the vector points.
     *
     * @param dt - delta time (the time span between the current frame and the last frame in seconds)
     */
    public void move(float dt) {
        setPrevpos(new Position(getPosition()));
        float x = getPosition().getX();
        float y = getPosition().getY();
        updateMovementVector(dt);
        if (getMovementVector().len() != 0) {
            getPosition().setX(x + getMovementVector().x);
        }
        getPosition().setY(y + getMovementVector().y);
        getRectangle().setPosition(getPosition().getX(), getPosition().getY());
    }

    @Override
    public void update(float dt) {
        if (getHealth() <= 0) {
            die();
        }
        if (getWeapon()!=null) {
            getWeapon().setPosition(getPosition());
        }
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

    public void addWeapon(WeaponInterface weapon) {
        weaponArray.add(weapon);
        setWeapon(weapon);
    }

    @Override
    public ArrayList<WeaponInterface> getAllWeapons(){
        return weaponArray;
    }

    /**
     * Executed when the player runs out of health. Notifies observers that the player is dead.
     */
    public void die() {
        pcs.firePropertyChange("Player died", null, "Player died");
    }

    public void isShooting(boolean shoot){
        shooting = shoot;
    }

    /**
     * Fires the weapon and notifes observers if a Projectile was created.
     */
    public void shoot(){
        if (getWeapon()!=null) {
            ProjectileInterface p = getWeapon().pullTrigger();
            if (p != null) {
                getProjectiles().add(p);
                pcs.firePropertyChange("New Projectile", null, p);
            }
        }
    }

    public void setAimDirection(float aimDirection){
        this.aimDirection.setAngle(aimDirection);
    }

    /**
     * Calculates the vector between the player and the mouse pointer. Sets the weapon aim vector to that vector.
     *
     * @param mousePoint - position of the mouse pointer
     */
    public void calculateDirection(PositionInterface mousePoint){
        Vector2 direction=new Vector2(mousePoint.getX() - getPosition().getX(), mousePoint.getY() - getPosition().getY());
        float length=direction.len();
        direction.scl(1 / length);
        setAimDirection(direction.angle());
        if (getWeapon()!=null) {
            getWeapon().getAimVector().set(aimDirection);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }


    /**
     * Sets which movement direction the player should have, based on a string.
     *
     * @param movementDirection - should be "north", "south", "west" or "east"
     */
    public void setMovementDirection(String movementDirection){
        switch (movementDirection) {
            case "north":
                north = true;
                break;
            case "south":
                south = true;
                break;
            case "west":
                west = true;
                break;
            case "east":
                east = true;
                break;
        }
    }

    /**
     * Resets the movement direction of the player in the given direction.
     *
     * @param movementDirection - should be "north", "south", "west" or "east"
     */
    public void resetMovementDirection(String movementDirection){
        switch (movementDirection) {
            case "north":
                north = false;
                break;
            case "south":
                south = false;
                break;
            case "west":
                west = false;
                break;
            case "east":
                east = false;
                break;
        }
    }

    /**
     * Resets all movement directions.
     */
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

    /**
     * Blocks the player if it tries to move in any direction.
     */
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

    /**
     * Unblocks the movement of the player.
     */
    public void unBlock() {
        blockedNorth = false;
        blockedSouth = false;
        blockedEast = false;
        blockedWest = false;
    }

    public void reloadCurrentWeapon(){
        if (getWeapon()!=null) {
            if (getWeapon().getbulletsLeftInMagazine() < getWeapon().getMagazineCapacity() && getWeapon().getTotalBullets()>0) {
                getWeapon().reload();
            }
        }
    }

    @Override
    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    /**
     * Updates the movement vector so that it aligns with the direction the player is facing.
     *
     * @param dt - delta time (the time span between the current frame and the last frame in seconds)
     */
    private void updateMovementVector(float dt) {
        getMovementVector().set(1, 0);
        if (north) {
            if (east) {
                getMovementVector().setAngle(45);
            } else if (west) {
                getMovementVector().setAngle(90 + 45);
            } else {
                getMovementVector().setAngle(90);
            }
            getMovementVector().setLength(getTotalMovementSpeed() * dt);
        } else if (south) {
            if (east) {
                getMovementVector().setAngle(360 - 45);
            } else if (west) {
                getMovementVector().setAngle(180 + 45);
            } else {
                getMovementVector().setAngle(270);
            }
            getMovementVector().setLength(getTotalMovementSpeed() * dt);
        } else if (west) {
            getMovementVector().setAngle(180);
            getMovementVector().setLength(getTotalMovementSpeed() * dt);
        } else if (east) {
            getMovementVector().setAngle(0);
            getMovementVector().setLength(getTotalMovementSpeed() * dt);
        } else {
            getMovementVector().setLength(0);
        }
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.PLAYER;
    }

}