package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.Projectile;

/**
 * Created by jonas on 2015-04-23.
 */
public class ProjectileView {
    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;
    private Projectile projectile;

    public ProjectileView(){
        texture = new Texture(Gdx.files.local("triforce.png"));
        sprite = new Sprite(texture);
        rectangle = new Rectangle();
        rectangle.height = 64;
        rectangle.width = 64;
        sprite.setX(rectangle.x);
        sprite.setY(rectangle.y);
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

    public void setProjectile(Projectile newProjectile){
        projectile = newProjectile;
    }

    private void updatePosition(){
        sprite.setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
        rectangle.setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
    }

    private void setRotation(){
        sprite.setRotation(projectile.getDirection().angle());
    }

    /**
     *
     * @return the rectangle of the projectile.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        setRotation();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void dispose(){
        texture.dispose();
    }

}
