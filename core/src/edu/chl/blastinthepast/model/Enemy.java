package edu.chl.blastinthepast.model;

import com.badlogic.gdx.scenes.scene2d.Actor;
import edu.chl.blastinthepast.utils.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * Created by Mattias on 15-04-21.
 */
public class Enemy implements Character {

    private int health;
    private int movementSpeed;
    private Position position;
    private Weapon weapon;
    private MyActionListener actionListener;
    private Timer t;
    private int movementDirection;

    /**
     * Default constructor for Enemy with default movement speed and health.
     */
    public Enemy() {
        this(150, 100);
    }

    public Enemy(int movementSpeed, int health) {
        this.movementSpeed = movementSpeed;
        this.health = health;
        position = new Position(0,0);
        actionListener = new MyActionListener();
        Random r = new Random();
        movementDirection = r.nextInt(4);
        t = new Timer(1000, actionListener);
        t.setRepeats(true);
        t.start();
    }

    public void move(float dt) {
        switch (movementDirection) {
            case 0: // move west
                if (!(position.getX() > 0)) {
                    movementDirection = 1;
                } else {
                    position.setX(position.getX() - movementSpeed * dt);
                }
                break;
            case 1: // move east
                if (!(position.getX() < 800)) {
                    movementDirection = 0;
                } else {
                    position.setX(position.getX() + movementSpeed * dt);
                }
                break;
            case 2: // move north
                if (!(position.getY() < 480)) {
                    movementDirection = 3;
                } else {
                    position.setY(position.getY() + movementSpeed * dt);
                }
                break;
            case 3: // move south
                if (!(position.getY() > 2)) {
                    movementDirection = 1;
                } else {
                    position.setY(position.getY() - movementSpeed * dt);
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

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Random r = new Random();
            movementDirection = r.nextInt(4);
        }
    }

}
