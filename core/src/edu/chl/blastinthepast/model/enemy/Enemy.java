package edu.chl.blastinthepast.model.enemy;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponFactory;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;


/**
 * Created by Mattias on 15-04-21.
 */
public abstract class Enemy extends Observable implements Character {

    private final Character player;
    private int health;
    private int movementSpeed;
    private PositionInterface position;
    private PositionInterface prevPos;
    private WeaponInterface weapon;
    private WeaponFactory weaponFactory;
    private MyActionListener actionListener;
    private Timer timer;
    private int movementDirection;
    private Vector2 movementDirectionVector;
    private Vector2 playerDirectionVector;
    private int range = 500;
    private ArrayList<ProjectileInterface> projectiles;
    private ArrayList<Object> loot;
    private int bonusMovementSpeed=0;
    private Rectangle rectangle = new RectangleAdapter();
    private HashMap<String, ArrayList<Object>> lootz = new HashMap<String, ArrayList<Object>>();
    protected ArrayList<PowerUpI> powerUpDrops = new ArrayList<PowerUpI>();
    protected ArrayList<AmmunitionInterface> ammunitionDrops = new ArrayList<AmmunitionInterface>();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final int width;
    private final int height;

    public Enemy(int movementSpeed, int health, Character player, int width, int height) {
        this.width=width;
        this.height=height;
        rectangle.setSize(width, height);
        loot= new ArrayList<Object>();
        this.movementSpeed = movementSpeed;
        this.health = health;
        this.player = player;
        position = new Position(0,0);
        actionListener = new MyActionListener();
        Random r = new Random();
        movementDirection = r.nextInt(4);
        movementDirectionVector = new Vector2();
        playerDirectionVector = new Vector2();
        weaponFactory = new WeaponFactory();
        weapon = weaponFactory.getWeapon(this, "AK47");
        timer = new Timer(1000, actionListener);
        timer.setRepeats(true);
        timer.start();
        projectiles=new ArrayList<ProjectileInterface>();
    }

    public void move(float dt) {
        switch (movementDirection) {
            case 0: // move west
                if (!(position.getX() > 0)) {
                    movementDirection = 1;
                } else {
                    position.setX(position.getX() - getTotalMovementSpeed() * dt);
                    movementDirectionVector.set(position.getX() - range, position.getY());
                }
                break;
            case 1: // move east
                if (!(position.getX() < Constants.MAP_WIDTH)) {
                    movementDirection = 0;
                } else {
                    position.setX(position.getX() + getTotalMovementSpeed() * dt);
                    movementDirectionVector.set(position.getX() + range, position.getY());
                }
                break;
            case 2: // move north
                if (!(position.getY() < Constants.MAP_HEIGHT)) {
                    movementDirection = 3;
                } else {
                    position.setY(position.getY() + getTotalMovementSpeed() * dt);
                    movementDirectionVector.set(position.getX(), position.getY() + range);
                }
                break;
            case 3: // move south
                if (!(position.getY() > 2)) {
                    movementDirection = 1;
                } else {
                    position.setY(position.getY() - getTotalMovementSpeed() * dt);
                    movementDirectionVector.set(position.getX(), position.getY() - range);
                }
                break;
            default:
                break;
        }
        rectangle.setPosition(position);
    }

    public void setMovementSpeed(int newSpeed) {
        movementSpeed = newSpeed;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
    }

    public WeaponInterface getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    public PositionInterface getPosition(){
        return position;
    }

    public int getMovementDirection() {
        return movementDirection;
    }

    public void setPosition (PositionInterface position) {
        this.position = position;
        rectangle.setPosition(position);
    }

    @Override
    public void addBonusMovementSpeed(int bonusSpeed) {

    }

    @Override
    public int getBonusMovementSpeed() {
        return bonusMovementSpeed;
    }

    @Override
    public int getTotalMovementSpeed() {
        return movementSpeed+bonusMovementSpeed;
    }

    @Override
    public ArrayList<WeaponInterface> getAllWeapons() {
        ArrayList<WeaponInterface> weaponArray=new ArrayList<WeaponInterface>();
        weaponArray.add(weapon);
        return weaponArray;
    }

    @Override
    public void resetBonuses() {

    }

    public ArrayList<Object> getLoot() {
        return loot;
    }

    public PositionInterface getPrevPos(){
        return prevPos;
    }

    public void setPrevPos (Position prevPos) {
        this.prevPos = prevPos;
    }

    public WeaponInterface getCurrentWeapon() {
        return weapon;
    };

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void update(float dt) {
        prevPos = new Position(position);
        playerDirectionVector.set(player.getPosition().getX() - position.getX(), player.getPosition().getY() - position.getY());
        weapon.setPosition(position);
        if(isPlayerInRange()) {
            movementDirectionVector.set(playerDirectionVector);
            ProjectileInterface p=weapon.pullTrigger();
            if (p!=null){
                projectiles.add(p);
                pcs.firePropertyChange("New Projectile", null, p);
            }
        } else {
            updateMovementDirectionVector(movementDirection);
        }
        move(dt);

    }

    private void updateMovementDirectionVector(int movementDirection) {
        switch (movementDirection) {
            case 0: // moving west
                    movementDirectionVector.set(position.getX() - range, position.getY());
                break;
            case 1: // moving east
                    movementDirectionVector.set(position.getX() + range, position.getY());
                break;
            case 2: // moving north
                    movementDirectionVector.set(position.getX(), position.getY() + range);
                break;
            case 3: // moving south
                    movementDirectionVector.set(position.getX(), position.getY() - range);
                break;
            default:
                break;
        }
    }

    private boolean isPlayerInRange() {
        if (Math.abs(playerDirectionVector.angle(movementDirectionVector)) < 150 &&
               Math.abs(player.getPosition().getX() - position.getX()) < 300 &&
                Math.abs(player.getPosition().getY() - position.getY()) < 300) {
            return true;
        }
        return false;
    }

    public Vector2 getDirection() {
        return movementDirectionVector;
    }

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Random r = new Random();
            movementDirection = r.nextInt(4);
        }

    }

    public ArrayList<ProjectileInterface> getProjectiles(){
        return projectiles;
    }

    public abstract void generateLoot();


    public ArrayList<Object> die()
    {
        generateLoot();
        pcs.firePropertyChange("PowerUp drops", null, powerUpDrops);
        pcs.firePropertyChange("Ammunition drops", null, ammunitionDrops);
        return loot;
    }

    public boolean isColliding(Collidable c){
        return rectangle.overlaps(c.getRectangle());
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    public void removeListener (PropertyChangeListener pcl){
        pcs.removePropertyChangeListener(pcl);
    }

}
