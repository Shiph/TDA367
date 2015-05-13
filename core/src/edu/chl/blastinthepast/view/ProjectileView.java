package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;
import edu.chl.blastinthepast.model.entities.ProjectileInterface;

import java.util.ArrayList;

import edu.chl.blastinthepast.utils.Constants;

/**
 * Created by jonas on 2015-04-23.
 */
public abstract class ProjectileView implements Collidable {
    private Texture texture;
    private Sprite sprite;
    private ArrayList<Rectangle> rectangle;
    private ProjectileInterface projectile;

    public ProjectileView(ProjectileInterface projectile, Texture texture, Sound sound){
        this.projectile=projectile;
        this.texture = texture;
        sprite = new Sprite(texture);
        rectangle = new ArrayList<Rectangle>();
        sound.play();
        rectangle.add(new Rectangle());
        rectangle.get(0).x = Constants.CAMERA_WIDTH / 2 - sprite.getWidth() / 2;
        rectangle.get(0).y = Constants.CAMERA_HEIGHT / 2 - sprite.getHeight() / 2;
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
    }

    /**
     * @return the texture of the projectile.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * @return the sprite of the projectile.
     */
    public Sprite getSprite() {
        return sprite;
    }

    private void updatePosition() {
        sprite.setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
        rectangle.get(0).setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
    }

    private void setRotation(){
        sprite.setRotation(projectile.getDirection().angle());
    }

    /**
     * @return the rectangle of the projectile.
     */
    public ArrayList<Rectangle> getRectangles() {
        return rectangle;
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        setRotation();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void setRectangles(ArrayList<Rectangle> rectangles) {

    }

    public void dispose() {
        texture.dispose();
    }

    public ProjectileInterface getProjectile(){
        return projectile;
    }
}
