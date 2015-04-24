package edu.chl.blastinthepast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Mattias on 15-04-21.
 */
public class Enemy extends Actor {
    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;
    private int movementSpeed;
    private int health;

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
