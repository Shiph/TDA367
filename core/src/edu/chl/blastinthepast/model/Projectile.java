package edu.chl.blastinthepast.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;

import java.util.Iterator;

/**
 * Created by Shif on 21/04/15.
 */
public class Projectile {

    private Vector2 direction;
    private float speed = 500;
    private int damage = 100;
    private Position position;

    public Projectile(float x, float y, Vector2 direction) {
        this.direction = new Vector2(direction);
        position = new Position(x, y);
    }

    public Projectile(Position pos, Vector2 direction){
        this.direction= new Vector2(direction);
        position = new Position(pos);
    }

    public void move(float dt) {
        position.setY(position.getY() + ((float)Math.cos(Math.toRadians(direction.angle()))) * speed * dt);
        position.setX(position.getX() - ((float)Math.cos(Math.toRadians(direction.angle()))) * speed * dt);
    }

    /**
     *
     * @return the damage of the projectile.
     */
    public int getDamage() {
        return damage;
    }

    public void setDamage(int newDamage) {
        damage = newDamage;
    }


    /**
     *
     * @return the direction of the projectile.
     */
    public Vector2 getDirection() {
        return direction;
    }

    /**
     *
     * @param direction the direction of the projectile.
     */
    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public Position getPosition(){
        return position;
    }

}
