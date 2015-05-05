package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Player;

/**
 * Created by jonas on 2015-04-23.
 */
public class PlayerView implements CharacterView {
    private Texture texture;
    private Sprite sprite;
    private Array<Rectangle> rectangle;
    private Player player;
    private Vector2 direction;

    public PlayerView(Player newPlayer){
        texture = new Texture(Gdx.files.local("sanic.png"));
        sprite = new Sprite(texture);
        rectangle = new Array(true,1,Rectangle.class);
        rectangle.size = 1;
        rectangle.set(0,new Rectangle());
        direction = new Vector2();
        rectangle.get(0).x = 800/2 - 64/2;
        rectangle.get(0).y = 480/2 - 64/2;
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
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
    public Array<Rectangle> getRectangles() {
        return rectangle;
    }

    @Override
    public void setRectangles(Array<Rectangle> rectangles) {

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
        rectangle.get(0).setPosition(player.getPosition().getX(), player.getPosition().getY());
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
