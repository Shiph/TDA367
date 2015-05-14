package edu.chl.blastinthepast.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;


/**
 * Created by Mattias on 15-04-21.
 */
public class Enemy extends Observable implements Character {

    private final Character player;
    private int health;
    private int movementSpeed;
    private PositionInterface position;
    private Position prevPos;
    private WeaponInterface weapon;
    private MyActionListener actionListener;
    private Timer timer;
    private int movementDirection;
    private Vector2 movementDirectionVector;
    private Vector2 playerDirectionVector;
    private int range = 500;
    private ArrayList<ProjectileInterface> projectiles;
    private ArrayList<GameObject> loot;

    /**
     * Default constructor for Enemy with default movement speed and health.
     */
    public Enemy(Character player, PositionInterface position) {
        this(150, 100, position, player);
    }

    public Enemy(int movementSpeed, int health, PositionInterface position, Character player) {
        loot= new ArrayList<GameObject>();
        this.movementSpeed = movementSpeed;
        this.health = health;
        this.player = player;
        this.position = position;
        actionListener = new MyActionListener();
        Random r = new Random();
        movementDirection = r.nextInt(4);
        movementDirectionVector = new Vector2();
        playerDirectionVector = new Vector2();
        weapon = new AK47(position, movementDirectionVector);
        timer = new Timer(1000, actionListener);
        timer.setRepeats(true);
        timer.start();
        projectiles=new ArrayList<ProjectileInterface>();
        generateLoot();
    }

    public void move(float dt) {
        switch (movementDirection) {
            case 0: // move west
                if (!(position.getX() > 0)) {
                    movementDirection = 1;
                } else {
                    position.setX(position.getX() - movementSpeed * dt);
                    movementDirectionVector.set(position.getX() - range, position.getY());
                }
                break;
            case 1: // move east
                if (!(position.getX() < Constants.MAP_WIDTH)) {
                    movementDirection = 0;
                } else {
                    position.setX(position.getX() + movementSpeed * dt);
                    movementDirectionVector.set(position.getX() + range, position.getY());
                }
                break;
            case 2: // move north
                if (!(position.getY() < Constants.MAP_HEIGHT)) {
                    movementDirection = 3;
                } else {
                    position.setY(position.getY() + movementSpeed * dt);
                    movementDirectionVector.set(position.getX(), position.getY() + range);
                }
                break;
            case 3: // move south
                if (!(position.getY() > 2)) {
                    movementDirection = 1;
                } else {
                    position.setY(position.getY() - movementSpeed * dt);
                    movementDirectionVector.set(position.getX(), position.getY() - range);
                }
                break;
            default:
                break;
        }

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
    }

    public Position getPrevPos(){
        return prevPos;
    }

    public void setPrevPos (Position prevPos) {
        this.prevPos = prevPos;
    }

    public WeaponInterface getWeapon() {
        return weapon;
    };

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void update(float dt) {
        playerDirectionVector.set(player.getPosition().getX() - position.getX(), player.getPosition().getY() - position.getY());
        weapon.setPosition(position);
        if(isPlayerInRange()) {
            movementDirectionVector.set(playerDirectionVector);
            ProjectileInterface p=weapon.pullTrigger();
            if (p!=null){
                projectiles.add(p);
                setChanged();
                notifyObservers(p);
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
        if (Math.abs(playerDirectionVector.angle(movementDirectionVector)) < 100) {
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

    public void generateLoot(){
        Random random = new Random();
        boolean hasAmmo=random.nextBoolean();
        if (hasAmmo) {
            int amount = random.nextInt(4)*10+20;
            Ammunition ammo = new Ammunition(getPosition(), weapon.getProjectile(), amount);
            loot.add(ammo);
        }
    }

    public ArrayList<GameObject> die(){
        return loot;
    }

}
