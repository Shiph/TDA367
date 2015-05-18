package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.entities.PowerUpDecorator;

/**
 * Created by jonas on 2015-05-15.
 */
public class PowerUpView implements WorldObject{

    private Texture texture;
    private PowerUpDecorator powerUp;
    private Rectangle rectangle;
    private Sprite sprite;

    public PowerUpView(PowerUpDecorator powerUp, Texture texture){
        this.powerUp=powerUp;
        this.texture=texture;
        sprite= new Sprite(texture);
        rectangle= new Rectangle();
        rectangle.setWidth(sprite.getWidth());
        rectangle.setHeight(sprite.getHeight());
        sprite.setPosition(powerUp.getPosition().getX(), powerUp.getPosition().getY());
        rectangle.setPosition(powerUp.getPosition().getX(), powerUp.getPosition().getY());
    }

    @Override
    public Object getObject() {
        return powerUp;
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
