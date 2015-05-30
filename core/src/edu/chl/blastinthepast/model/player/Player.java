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
    private final int width = 64;
    private final int height = 64;
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

    public void update(float dt) {
        if (getHealth() <= 0) {
            die();
        }
        if (getWeapon()!=null) {
            getWeapon().setPosition(getPosition());
        }
        getWeapon().setPosition(getPosition());
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
        WeaponInterface newWeapon;
        newWeapon = weaponFactory.getWeapon(getPosition(), getAimVector(), getMovementVector(), weapon.getWeaponType());
        weaponArray.add(newWeapon);
        setWeapon(newWeapon);
    }

    public ArrayList<WeaponInterface> getAllWeapons(){
        return weaponArray;
    }

    public void die() {
        pcs.firePropertyChange("Player died", null, "Player died");
    }

    public void isShooting(boolean shoot){
        shooting = shoot;
    }

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

    public void setMovementDirection(String movementDirection){
        switch (movementDirection) {
            case "north":
                north = !north;
                south = false;
                break;
            case "south":
                south = !south;
                north = false;
                break;
            case "west":
                west = !west;
                east = false;
                break;
            case "east":
                east = !east;
                west = false;
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
        if (getWeapon()!=null) {
            if (getWeapon().getbulletsLeftInMagazine() < getWeapon().getMagazineCapacity() && getWeapon().getTotalBullets()>0) {
                getWeapon().reload();
            }
        }
    }

    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

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