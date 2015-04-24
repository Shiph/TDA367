package edu.chl.blastinthepast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Mattias on 15-04-21.
 */
public class Enemy extends Actor {
    private Texture texture;
    private Sprite sprite;
    private static final int DELAY = 2500;
    private Rectangle rectangle;
    private Timer timer;
    private int movementSpeed;
    private int health;
    private Vector2 vector;

    /**
     * Default constructor for Enemy with default movement speed and health.
     */
    public Enemy() {
        this(150, 100);
    }

    /**
     * Creates a new enemy character with texture, rectangle and sprite.
     */
    public Enemy(int movementSpeed, int health) {
        texture = new Texture(Gdx.files.local("kim.png"));
        sprite = new Sprite(texture);
        rectangle = new Rectangle();
        rectangle.x = 800/2 - 64/2;
        rectangle.y = 480/2 - 64/2;
        rectangle.height = 64;
        rectangle.width = 64;
        sprite.setX(rectangle.x);
        sprite.setY(rectangle.y);
        //vector.add(rectangle.x, rectangle.y);

        /*ActionListener AI = new ActionListener() {
            Random rand = new Random();
            private boolean hasTarget;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(angle(player.getVector()) ... ) {
                    hasTarget = true;
                }
                if(!hasTarget()) {
                    rand.nextInt(3);
                    move(updateSetDirection(rand), Gdx.graphics.getDeltaTime(););
                } else {
                    //shoot at player.
                }
            }
        };

        //timer = new Timer(DELAY, AI);
        //timer.setRepeats(true); */
    }

    /**
     * @return the texture of the player character.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * @return the rectangle of the player character.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * @return the sprite of the player character.
     */
    public Sprite getSprite() {
        return sprite;
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

    public void setX(float x) {
        rectangle.setX(x);
        sprite.setX(x);
    }

    public void setY(float y) {
        rectangle.setY(y);
        sprite.setY(y);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

}
