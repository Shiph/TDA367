package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 21/04/15.
 */
public abstract class Projectile implements  ProjectileInterface{

    private Vector2 direction;
    private float speed;
    private int damage;
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
        position.setY(position.getY() + (float) Math.sin(direction.angleRad()) * speed * dt);
        position.setX(position.getX() + (float)Math.cos(direction.angleRad()) * speed * dt);
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

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
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
