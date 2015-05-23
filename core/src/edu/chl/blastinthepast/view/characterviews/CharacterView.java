package edu.chl.blastinthepast.view.characterviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.view.ProjectileView;

import java.util.ArrayList;

/**
 * Created by MattiasJ on 2015-04-27.
 */
public interface CharacterView {

    Texture getTexture();
    Sprite getSprite();
    Character getCharacter();
    void draw(SpriteBatch batch);


}
