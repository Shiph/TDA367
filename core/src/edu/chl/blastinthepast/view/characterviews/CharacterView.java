package edu.chl.blastinthepast.view.characterviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.view.projectileviews.ProjectileView;

import java.util.ArrayList;

/**
 * Created by MattiasJ on 2015-04-27.
 */
public interface CharacterView {

    public ArrayList<Rectangle> getRectangles();

    public Texture getTexture();

    public Sprite getSprite();

    public Character getCharacter();

    public void hit(ProjectileView projectile);

    public void draw(SpriteBatch batch);


}
