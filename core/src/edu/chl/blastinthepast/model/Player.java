package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 21/04/15.
 */
public class Player {

    private int health;
    private int movementSpeed;
    private Weapon weapon;
    private boolean north, south, west, east = false;
    private Position position;

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

        weapon = new Weapon();
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

    /*
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



    }*/

    /*
    private float getAimDirection() {
        return (float)(-Math.atan2(Gdx.input.getY() - (480-64-rectangle.y + rectangle.height/2),
                Gdx.input.getX() - (rectangle.x + rectangle.width/2)) * (180/Math.PI)-90);
    } */

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
        float x=position.getX();
        float y=position.getY();
        switch (direction) {
            case "west":
                position.setX(x-movementSpeed*dt);
                /* if (x<0){
                    position.setX(0);
                }*/
                break;
            case "east":
                position.setX(x+movementSpeed*dt);
                /*if (sprite.getX()> Constants.MAP_WIDTH-sprite.getWidth()){
                    rectangle.setX(Constants.MAP_WIDTH - sprite.getWidth());
                    sprite.setX(Constants.MAP_WIDTH - sprite.getWidth());
                } */
                break;
            case "north":
                position.setY(y+movementSpeed*dt);
               /* if (sprite.getY()> Constants.MAP_HEIGHT-sprite.getHeight()){
                    rectangle.setY(Constants.MAP_HEIGHT - sprite.getHeight());
                    sprite.setY(Constants.MAP_HEIGHT - sprite.getHeight());
                }*/
                break;
            case "south":
                position.setY(y-movementSpeed*dt);
              /*  if (sprite.getY()<0){
                    rectangle.setY(0);
                    sprite.setY(0);
                }*/
                break;
            default: break;
        }
    }

}
