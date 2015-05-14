package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by jonas on 2015-05-13.
 */
public interface WorldObject {
    public Object getObject();
    public void draw(SpriteBatch batch);
    public Texture getTexture();
    public void dispose();
    public Rectangle getRectangle();

}
