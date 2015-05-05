package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.utils.Position;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by jonas on 2015-04-23.
 */
public class EnemyView implements CharacterView {
    private Texture texture;
    private Sprite sprite;
    private static final int DELAY = 2500;
    private ArrayList<Rectangle> rectangle;
    private Enemy enemy;
    private Timer timer;
    private int movementSpeed;
    private int health;
    private Vector2 vector;

    EnemyView(Enemy newEnemy){
        texture = new Texture(Gdx.files.local("kim.png"));
        sprite = new Sprite(texture);
        rectangle = new ArrayList<Rectangle>();
        rectangle.add(new Rectangle());
        rectangle.get(0).x = 800/2 - 64/2;
        rectangle.get(0).y = 480/2 - 64/2;
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
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
    public ArrayList<Rectangle> getRectangles() {
        return rectangle;
    }

    @Override
    public void setRectangles(ArrayList<Rectangle> rectangles) {

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

    public void update() {
        sprite.setPosition(enemy.getPosition().getX() - 32, enemy.getPosition().getY() - 32);
        rectangle.get(0).setPosition(enemy.getPosition().getX(), enemy.getPosition().getY());
    }

    public void dispose() {
        texture.dispose();
    }

}
