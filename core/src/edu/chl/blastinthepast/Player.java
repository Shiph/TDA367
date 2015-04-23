package edu.chl.blastinthepast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Shif on 21/04/15.
 */
public class Player extends Actor {

    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;
    private int health;
    private int movementSpeed;
    private Weapon weapon;
    private boolean north, south, west, east = false;

    /**
     * Default constructor for Player with default movement speed and health.
     */
    public Player() {
        this(200, 100);
    }

    /**
     * Creates a new player character with texture, rectangle and sprite.
     */
    public Player(int movementSpeed, int health) {
        this.movementSpeed = movementSpeed;
        this.health = health;
        texture = new Texture(Gdx.files.local("sanic.png"));
        sprite = new Sprite(texture);
        rectangle = new Rectangle();
        rectangle.x = 800/2 - 64/2;
        rectangle.y = 480/2 - 64/2;
        rectangle.height = 64;
        rectangle.width = 64;
        sprite.setX(rectangle.x);
        sprite.setY(rectangle.y);
        weapon = new Weapon();
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

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void update(float dt) {
        if(north) {
            System.out.println("north true");
            rectangle.y += movementSpeed * dt;
            sprite.setY(sprite.getY() + movementSpeed * dt);
            if (sprite.getY()> Constants.MAP_HEIGHT-sprite.getHeight()){
                rectangle.setY(Constants.MAP_HEIGHT - sprite.getHeight());
                sprite.setY(Constants.MAP_HEIGHT - sprite.getHeight());
            }
        }
        if(south) {
            System.out.println("south true");
            rectangle.y -= movementSpeed * dt;
            sprite.setY(sprite.getY() - movementSpeed * dt);
            if (sprite.getY()<0){
                rectangle.setY(0);
                sprite.setY(0);
            }
        }
        if(west) {
            System.out.println("west true");
            rectangle.x -= movementSpeed * dt;
            sprite.setX(sprite.getX() - movementSpeed * dt);
            if (sprite.getX()<0){
                rectangle.setX(0);
                sprite.setX(0);
            }
        }
        if(east) {
            System.out.println("east true");
            rectangle.x += movementSpeed * dt;
            sprite.setX(sprite.getX() + movementSpeed * dt);
            if (sprite.getX()> Constants.MAP_WIDTH-sprite.getWidth()){
                rectangle.setX(Constants.MAP_WIDTH - sprite.getWidth());
                sprite.setX(Constants.MAP_WIDTH - sprite.getWidth());
            }
        }


    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.setRotation(getAimDirection());
        sprite.draw(batch);
        batch.end();
    }

    private float getAimDirection() {
        return (float)(-Math.atan2(Gdx.input.getY() - (480-64-rectangle.y + rectangle.height/2),
                Gdx.input.getX() - (rectangle.x + rectangle.width/2)) * (180/Math.PI)-90);
    }

    /**
     * Updates the position of the player character's rectangle and sprite.
     *
     * @param direction - the direction in which to move the sprite. 0 = left, 1 = right, 2 = up, 3 = down.
     */
    /*
    public void updatePlayerPos(int direction) {
        switch (direction) {
            case 0: // move left
                rectangle.x -= movementSpeed * Gdx.graphics.getDeltaTime();
                sprite.setX(sprite.getX() - movementSpeed * Gdx.graphics.getDeltaTime());
                if (sprite.getX()<0){
                    rectangle.setX(0);
                    sprite.setX(0);
                }
                break;
            case 1: // move right
                rectangle.x += movementSpeed * Gdx.graphics.getDeltaTime();
                sprite.setX(sprite.getX() + movementSpeed * Gdx.graphics.getDeltaTime());
                if (sprite.getX()> Constants.MAP_WIDTH-sprite.getWidth()){
                    rectangle.setX(Constants.MAP_WIDTH - sprite.getWidth());
                    sprite.setX(Constants.MAP_WIDTH - sprite.getWidth());
                }
                break;
            case 2: // move up
                rectangle.y += movementSpeed * Gdx.graphics.getDeltaTime();
                sprite.setY(sprite.getY() + movementSpeed * Gdx.graphics.getDeltaTime());
                if (sprite.getY()> Constants.MAP_HEIGHT-sprite.getHeight()){
                    rectangle.setY(Constants.MAP_HEIGHT - sprite.getHeight());
                    sprite.setY(Constants.MAP_HEIGHT - sprite.getHeight());
                }
                break;
            case 3: // move down
                rectangle.y -= movementSpeed * Gdx.graphics.getDeltaTime();
                sprite.setY(sprite.getY() - movementSpeed * Gdx.graphics.getDeltaTime());
                if (sprite.getY()<0){
                    rectangle.setY(0);
                    sprite.setY(0);
                }
                break;
        }
    }

    public void setNorth(boolean up) {
        this.north = up;
    }

    public void setSouth(boolean down) {
        this.south = down;
    }

    public void setWest(boolean left) {
        this.west = left;
    }

    public void setEast(boolean right) {
        this.east = right;
    }
*/
    public void move(String direction, float dt) {
        switch (direction) {
            case "west":
                rectangle.x -= movementSpeed * dt;
                sprite.setX(sprite.getX() - movementSpeed * dt);
                if (sprite.getX()<0){
                    rectangle.setX(0);
                    sprite.setX(0);
                }
                break;
            case "east":
                rectangle.x += movementSpeed * dt;
                sprite.setX(sprite.getX() + movementSpeed * dt);
                if (sprite.getX()> Constants.MAP_WIDTH-sprite.getWidth()){
                    rectangle.setX(Constants.MAP_WIDTH - sprite.getWidth());
                    sprite.setX(Constants.MAP_WIDTH - sprite.getWidth());
                }
                break;
            case "north":
                rectangle.y += movementSpeed * dt;
                sprite.setY(sprite.getY() + movementSpeed * dt);
                if (sprite.getY()> Constants.MAP_HEIGHT-sprite.getHeight()){
                    rectangle.setY(Constants.MAP_HEIGHT - sprite.getHeight());
                    sprite.setY(Constants.MAP_HEIGHT - sprite.getHeight());
                }
                break;
            case "south":
                rectangle.y -= movementSpeed * dt;
                sprite.setY(sprite.getY() - movementSpeed * dt);
                if (sprite.getY()<0){
                    rectangle.setY(0);
                    sprite.setY(0);
                }
                break;
            default: break;
        }
    }

}
