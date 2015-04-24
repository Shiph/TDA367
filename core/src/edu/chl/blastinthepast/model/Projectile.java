package edu.chl.blastinthepast.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 21/04/15.
 */
public class Projectile {

    private float direction = 0;
    private float speed = 500;
    private int damage = 100;
    private Position position;

    public Projectile() {
        this(0, 0, 0);
    }

    public Projectile(float x, float y, float direction) {
        this.direction = direction;
        position=new Position(x, y);
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
    public float getDirection() {
        return direction;
    }

    /**
     *
     * @param direction the direction of the projectile.
     */
    public void setDirection(float direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public Position getPosition(){
        return position;
    }
}
