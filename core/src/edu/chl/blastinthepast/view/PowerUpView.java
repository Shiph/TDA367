package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;

/**
 * Created by jonas on 2015-05-15.
 */
public class PowerUpView implements WorldObject{

    private Texture texture;
    private PowerUpI powerUp;
    private Sprite sprite;

    public PowerUpView(PowerUpI powerUp, Texture texture){
        this.powerUp=powerUp;
        this.texture=texture;
        sprite= new Sprite(texture);
        sprite.setPosition(powerUp.getPosition().getX(), powerUp.getPosition().getY());
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
}
