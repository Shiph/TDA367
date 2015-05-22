package edu.chl.blastinthepast.model.projectile;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

/**
 * Created by Shif on 21/04/15.
 */
public abstract class Projectile implements  ProjectileInterface {

    private Vector2 direction;
    private float speed;
    private int damage;
    private Position position;
    private Rectangle rectangle = new RectangleAdapter();
    private final int width;
    private final int height;

    public Projectile(Position pos, Vector2 direction, int speed, int damage, int bonusDamage, int width, int height) {
        this.direction = new Vector2(direction);
        position = pos;
        this.speed = speed;
        this.damage = damage+bonusDamage;
        this.width=width;
        this.height=height;
        rectangle.setSize(width, height);
    }

    public void move(float dt) {
        position.setY(position.getY() + (float) Math.sin(direction.angleRad()) * speed * dt);
        position.setX(position.getX() + (float) Math.cos(direction.angleRad()) * speed * dt);
        rectangle.setY(position.getY());
        rectangle.setX(position.getX());
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

    public boolean isColliding(Collidable c){
        return rectangle.contains(c.getRectangle());
    }

    public Rectangle getRectangle(){
        return rectangle;
    }


}
