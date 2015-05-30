package edu.chl.blastinthepast.model.projectiles;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

/**
 * Created by Shif on 21/04/15.
 */
public abstract class Projectile implements ProjectileInterface {

    private Vector2 aimVector;
    private Vector2 movementVector;
    private float speed;
    private int damage;
    private Position position;
    private Rectangle rectangle = new RectangleAdapter();

    public Projectile(Position pos, Vector2 aimVector, Vector2 movementVector, int speed, int damage, int bonusDamage, int width, int height) {
        this.aimVector = new Vector2(aimVector);
        this.movementVector = new Vector2(movementVector);
        position = pos;
        this.speed = speed;
        this.damage = damage + bonusDamage;
        rectangle.setSize(width, height);
        rectangle.setPosition(position);
    }

    @Override
    public void move(float dt) {
        aimVector.setLength(speed * dt);
        Vector2 oldMovementVector = new Vector2(movementVector);
        Vector2 totalDirection = movementVector.add(aimVector);
        movementVector = oldMovementVector;
        position.setY(position.getY() + totalDirection.y);
        position.setX(position.getX() + totalDirection.x);
        rectangle.setPosition(position);
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int newDamage) {
        damage = newDamage;
    }

    @Override
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    @Override
    public Vector2 getAimVector() {
        return aimVector;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public Position getPosition(){
        return position;
    }

    public boolean isColliding(Collidable c){
        return rectangle.overlaps(c.getRectangle());
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}
