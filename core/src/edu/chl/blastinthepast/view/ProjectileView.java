package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Projectile;

import java.util.ArrayList;

/**
 * Created by jonas on 2015-04-23.
 */
public class ProjectileView implements Collidable {
    private Texture texture;
    private Sprite sprite;
    private Array<Rectangle> rectangle;
    private Projectile projectile;

    public ProjectileView(Projectile newProjectile){
        projectile=newProjectile;
        texture = new Texture(Gdx.files.local("triforce.png"));
        sprite = new Sprite(texture);
        rectangle = new Array(true,1,Rectangle.class);
        rectangle.size = 1;
        rectangle.set(0, new Rectangle());
        rectangle.get(0).x = 800/2 - 64/2;
        rectangle.get(0).y = 480/2 - 64/2;
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
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

    private void updatePosition(){
        sprite.setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
        rectangle.get(0).setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
    }

    /**
     *
     * @return the rectangle of the projectile.
     */
    public ArrayList<Rectangle> getRectangles() {
        return rectangle;
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        sprite.setRotation(projectile.getDirection().angle());
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void setRectangles(ArrayList<Rectangle> rectangles) {

    }
}
