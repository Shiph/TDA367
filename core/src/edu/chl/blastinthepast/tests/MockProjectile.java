package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.utils.PositionInterface;
import edu.chl.blastinthepast.utils.Rectangle;

/**
 * Created by Shif on 06/05/15.
 */
public class MockProjectile implements ProjectileInterface {

    private float speed;
    private int damage;
    private MockPosition position;
    private Vector2 direction;

    @Override
    public void setSpeed(int newSpeed) {
        this.speed = speed;
    }

    @Override
    public void move(float dt) {

    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public boolean isColliding(Collidable c) {
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}
