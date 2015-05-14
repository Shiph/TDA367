package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.Ammunition;

/**
 * Created by jonas on 2015-05-13.
 */
public class AmmunitionView implements WorldObject {


    private Ammunition ammunition;
    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;

    public AmmunitionView(Ammunition ammunition, Texture texture){
        this.ammunition=ammunition;
        this.texture=texture;
        sprite = new Sprite(texture);
        rectangle= new Rectangle();
        rectangle.setWidth(sprite.getWidth());
        rectangle.setHeight(sprite.getHeight());
        sprite.setPosition(ammunition.getPosition().getX(), ammunition.getPosition().getY());
        rectangle.setPosition(ammunition.getPosition().getX(), ammunition.getPosition().getY());
    }

    @Override
    public Object getObject() {
        return ammunition;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
}