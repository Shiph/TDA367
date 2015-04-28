package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Player;

/**
 * Created by jonas on 2015-04-23.
 */
public class PlayerView implements CharacterView {
    private Texture texture;
    private Sprite sprite;
    private Rectangle rectangle;
    private Player player;
    private Vector2 direction;

    public PlayerView(Player newPlayer){
        texture = new Texture(Gdx.files.local("sanic.png"));
        sprite = new Sprite(texture);
        rectangle = new Rectangle();
        direction = new Vector2();
        rectangle.x = 800/2 - 64/2;
        rectangle.y = 480/2 - 64/2;
        rectangle.height = 64;
        rectangle.width = 64;
        sprite.setX(rectangle.x);
        sprite.setY(rectangle.y);
        player = newPlayer;
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

    public void setRectangle(Rectangle rectangle) {}

    /**
     * @return the sprite of the player character.
     */
    public Sprite getSprite() {
        return sprite;
    }

    public void updatePosition(){
        sprite.setPosition(player.getPosition().getX() - 32, player.getPosition().getY() - 32);
        rectangle.setPosition(player.getPosition().getX(), player.getPosition().getY());
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        rotate();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void rotate(){
        direction = player.getAimDirection();
        sprite.setOrigin(32, 32);
        sprite.setRotation(direction.angle());
    }

    /*private float getAimDirection() {
        return (float)(-Math.atan2(Gdx.input.getY() - (480-64-rectangle.y + rectangle.height/2),
                Gdx.input.getX() - (rectangle.x + rectangle.width/2)) * (180/Math.PI)-90);
    }*/
}
