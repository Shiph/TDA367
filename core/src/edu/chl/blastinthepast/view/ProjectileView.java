package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.Projectile;
import com.badlogic.gdx.audio.Sound;
import edu.chl.blastinthepast.model.ProjectileInterface;

import java.util.ArrayList;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by jonas on 2015-04-23.
 */
public class ProjectileView implements Collidable {
    private Texture texture;
    private Sprite sprite;
    private ArrayList<Rectangle> rectangle;
    private ProjectileInterface projectile;
    //private Sound fireSound = Gdx.audio.newSound(Gdx.files.internal("wow.mp3"));

    public ProjectileView(ProjectileInterface projectile){
        this.projectile=projectile;
        texture = GraphicalAssets.TRIFORCE_BULLET;
        sprite = new Sprite(texture);
        rectangle = new ArrayList<Rectangle>();
        rectangle.add(new Rectangle());
        rectangle.get(0).x = 800 / 2 - 64 / 2;
        rectangle.get(0).y = 480 / 2 - 64 / 2;
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
        //fireSound.play();
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
}
