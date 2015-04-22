package edu.chl.blastinthepast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Shif on 21/04/15.
 */
public class Projectile {

    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;
    private float direction = 0;
    private float speed = 500;
    private float x = 0;
    private float y = 0;

    public Projectile() {
        this(0, 0, 0);
    }

    public Projectile(float x, float y, float direction) {
        this.direction = direction;
        this.y = x;
        this.x = y;
        texture = new Texture(Gdx.files.local("bucket.png"));
        sprite = new Sprite(texture);
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        rectangle.height = 64;
        rectangle.width = 64;
        sprite.setX(rectangle.x);
        sprite.setY(rectangle.y);
    }

    /**
     *
     * @return the damage of the projectile.
     */
    public int getDamage() {
        return 0;
    }

    /**
     *
     * @return the texture of the projectile.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     *
     * @return the sprite of the projectile.
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     *
     * @return the rectangle of the projectile.
     */
    public Rectangle getRectangle() {
        return rectangle;
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

    public void setX(float x) {
        this.x = x;
        rectangle.setX(x);
        sprite.setX(x);
    }

    public void setY(float y) {
        this.y = y;
        rectangle.setY(y);
        sprite.setY(y);

    }
}
