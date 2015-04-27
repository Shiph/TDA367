package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.utils.Position;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;

/**
 * Created by jonas on 2015-04-23.
 */
public class EnemyView implements CharacterView {
    private Texture texture;
    private Sprite sprite;
    private static final int DELAY = 2500;
    private Rectangle rectangle;
    private Enemy enemy;
    private Timer timer;
    private int movementSpeed;
    private int health;
    private Vector2 vector;

    EnemyView(Enemy newEnemy){
        texture = new Texture(Gdx.files.local("kim.png"));
        sprite = new Sprite(texture);
        rectangle = new Rectangle();
        rectangle.x = 800/2 - 64/2;
        rectangle.y = 480/2 - 64/2;
        rectangle.height = 64;
        rectangle.width = 64;
        sprite.setX(rectangle.x);
        sprite.setY(rectangle.y);
        enemy=newEnemy;
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

    public Position getPosition(){
        return enemy.getPosition();
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

}
