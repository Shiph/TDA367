package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by jonas on 2015-05-13.
 */
public interface WorldObject {

    Object getObject();
    void draw(SpriteBatch batch);
    Texture getTexture();
    void dispose();
    Rectangle getRectangle();

}
