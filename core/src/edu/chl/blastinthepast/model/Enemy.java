package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;


/**
 * Created by Mattias on 15-04-21.
 */
public class Enemy extends Observable implements Character {

    protected final Player player;
    protected int health;
    protected int movementSpeed;
    protected Position position;
    protected Weapon weapon;
    protected MyActionListener actionListener;
    protected Timer timer;
    protected int movementDirection;
    protected Vector2 movementDirectionVector;
    protected Vector2 playerDirectionVector;
    protected int range = 500;

    /**
     * Default constructor for Enemy with default movement speed and health.
     */
    public Enemy(Player player) {
        this(150, 100, player);
    }

    public Enemy(int movementSpeed, int health, Player player) {
        this.movementSpeed = movementSpeed;
        this.health = health;
        this.player = player;
        position = new Position(0,0);
        actionListener = new MyActionListener();
        Random r = new Random();
        movementDirection = r.nextInt(4);
        movementDirectionVector = new Vector2();
        playerDirectionVector = new Vector2();
        weapon = new AK47(position, movementDirectionVector);
        timer = new Timer(1000, actionListener);
        timer.setRepeats(true);
        timer.start();
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
                if (!(position.getX() < 800)) {
                    movementDirection = 0;
                } else {
                    position.setX(position.getX() + movementSpeed * dt);
                    movementDirectionVector.set(position.getX() + range, position.getY());
                }
                break;
            case 2: // move north
                if (!(position.getY() < 480)) {
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

    public int getHealth() {
        return health;
    }

    public Position getPosition(){
        return position;
    }

    public Weapon getWeapon() {
        return weapon;
    };

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void update() {
        playerDirectionVector.set(player.getPosition().getX() - position.getX(), player.getPosition().getY() - position.getY());
        weapon.setPosition(position);
        if(isPlayerInRange()) {
            movementDirectionVector.set(playerDirectionVector);
            setChanged();
            notifyObservers(weapon.pullTrigger());
        } else {
            updateMovementDirectionVector(movementDirection);
        }
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

}
