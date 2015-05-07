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
    private WeaponView weaponView;
    private boolean collision;

    EnemyView(Enemy enemy){
        this.enemy=enemy;
        texture = new Texture(Gdx.files.local("kim.png"));
        sprite = new Sprite(texture);
        weaponView = new WeaponView(enemy.getWeapon());
        rectangle = new ArrayList<Rectangle>();
        rectangle.add(new Rectangle());
        rectangle.get(0).x = 800/2 - 64/2;
        rectangle.get(0).y = 480/2 - 64/2;
        rectangle.get(0).height = 64;
        rectangle.get(0).width = 64;
        sprite.setX(rectangle.get(0).x);
        sprite.setY(rectangle.get(0).y);
        collision = false;

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

    @Override
    public void hit(ProjectileView projectile) {
        System.out.println(this);
    }

    public Position getPosition(){
        return enemy.getPosition();
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
        weaponView.draw(batch);
    }

    public void update() {
        if (!collision) {
            enemy.setPrevPos(enemy.getPosition());
            sprite.setPosition(enemy.getPosition().getX() - 32, enemy.getPosition().getY() - 32);
            rectangle.get(0).setPosition(enemy.getPosition().getX(), enemy.getPosition().getY());
        } else if (collision) {
            enemy.setPosition(enemy.getPrevPos());
            sprite.setPosition(enemy.getPosition().getX() - 32, enemy.getPosition().getY() - 32);
            rectangle.get(0).setPosition(enemy.getPosition().getX(), enemy.getPosition().getY());
            setCollision();
        }
        sprite.setRotation(enemy.getDirection().angle());
    }

    public void setCollision () {
        collision ^= true;
    }

    public void dispose() {
        texture.dispose();
    }

}
