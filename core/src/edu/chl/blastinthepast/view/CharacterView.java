package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by MattiasJ on 2015-04-27.
 */
public interface CharacterView extends Collidable {

    public Array<Rectangle> getRectangles();

    public Texture getTexture();

    public Sprite getSprite();

}
