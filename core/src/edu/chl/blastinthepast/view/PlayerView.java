package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.Player;
import edu.chl.blastinthepast.utils.Constants;

/**
 * Created by jonas on 2015-04-23.
 */
public class PlayerView {
    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;
    private Player player;

    public PlayerView(Player newPlayer){
        texture = new Texture(Gdx.files.local("sanic.png"));
        sprite = new Sprite(texture);
        rectangle = new Rectangle();
        rectangle.x = 800/2 - 64/2;
        rectangle.y = 480/2 - 64/2;
        rectangle.height = 64;
        rectangle.width = 64;
        sprite.setX(rectangle.x);
        sprite.setY(rectangle.y);
        player=newPlayer;
    }

    /**
     * @return the texture of the player character.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * @return the rectangle of the player character.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * @return the sprite of the player character.
     */
    public Sprite getSprite() {
        return sprite;
    }
}
