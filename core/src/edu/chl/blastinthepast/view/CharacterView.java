package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by MattiasJ on 2015-04-27.
 */
public interface CharacterView extends Collidable {

    public Rectangle getRectangle();

    public Texture getTexture();

    public Sprite getSprite();

}
