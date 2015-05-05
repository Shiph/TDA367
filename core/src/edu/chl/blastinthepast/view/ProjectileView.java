package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Projectile;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

/**
 * Created by jonas on 2015-04-23.
 */
public class ProjectileView implements Collidable {
    private Texture texture;
    private Sprite sprite;
    private ArrayList<Rectangle> rectangle;
    private Projectile projectile;
    //private Sound fireSound = Gdx.audio.newSound(Gdx.files.internal("wow.mp3"));

    public ProjectileView(Projectile newProjectile){
        projectile=newProjectile;
        texture = new Texture(Gdx.files.local("triforce.png"));
        sprite = new Sprite(texture);
<<<<<<< HEAD
        rectangle = new ArrayList<Rectangle>();
        rectangle.add(new Rectangle());
=======
<<<<<<< HEAD
        rectangle = new ArrayList<Rectangle>();
        rectangle.add(new Rectangle());
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
        //fireSound.play();
=======
        rectangle = new ArrayList<Rectangle>;
        rectangle.add(0, new Rectangle());
>>>>>>> hopefully fixed some shit
        rectangle.get(0).x = 800/2 - 64/2;
        rectangle.get(0).y = 480/2 - 64/2;
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
<<<<<<< HEAD
=======
>>>>>>> collision detection wip
>>>>>>> hopefully fixed some shit
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
<<<<<<< HEAD
    public ArrayList<Rectangle> getRectangles() {
=======
    public Array<Rectangle> getRectangles() {
>>>>>>> hopefully fixed some shit
        return rectangle;
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        sprite.setRotation(projectile.getDirection().angle());
        //System.out.println("projectile direction: " + sprite.getRotation());
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

<<<<<<< HEAD
    public void dispose(){
        texture.dispose();
    }

<<<<<<< HEAD
    @Override
    public void setRectangles(ArrayList<Rectangle> rectangles) {

    }
=======
=======
    @Override
    public void setRectangles(Array<Rectangle> rectangles) {

    }
>>>>>>> collision detection wip
>>>>>>> hopefully fixed some shit
}
