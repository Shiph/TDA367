package edu.chl.blastinthepast.model.entities;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 21/04/15.
 */
public abstract class Projectile implements  ProjectileInterface{

    private Vector2 aimDirection;
    private Vector2 movementVector;
    private float speed;
    private int damage;
    private Position position;

    public Projectile(Position pos, Vector2 aimDirection, Vector2 movementVector, int speed, int damage, int bonusDamage) {
        this.aimDirection = new Vector2(aimDirection);
        this.movementVector = new Vector2(movementVector);
        position = pos;
        this.speed = speed;
        this.damage = damage + bonusDamage;
    }

    @Override
    public void move(float dt) {
        aimDirection.setLength(speed * dt);
        Vector2 oldMovementVector = new Vector2(movementVector);
        Vector2 totalDirection = movementVector.add(aimDirection);
        movementVector = oldMovementVector;
        /*
        System.out.println("Movement vector length: " + movementVector.len());
        System.out.println("Aim vector length: " + aimDirection.len());
        System.out.println("Total vector length: " + totalDirection.len());
        System.out.println("Delta time: " + dt);
        System.out.println("Speed: " + speed);
        System.out.println("Speed * dt: " + speed * dt);
        System.out.println();
        System.out.println("Delta x: " + (float) Math.cos(totalDirection.angleRad()));
        System.out.println("Delta y: " + (float) Math.sin(totalDirection.angleRad()));
        System.out.println();
        */
        position.setY(position.getY() + totalDirection.y);
        position.setX(position.getX() + totalDirection.x);
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
    public Vector2 getAimDirection() {
        return aimDirection;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public Position getPosition(){
        return position;
    }

}
