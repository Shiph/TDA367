package edu.chl.blastinthepast.view;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.*;
import edu.chl.blastinthepast.utils.GraphicalAssets;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.model.Character;

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
    private WeaponView weaponView;
    private boolean collision;

    EnemyView(Enemy enemy){
        this.enemy = enemy;
        texture = GraphicalAssets.ENEMYDOWN;
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
    public Character getCharacter() {
        return enemy;
    }

    @Override
    public void hit(ProjectileView projectile) {
        System.out.println(this);
    }

    public Position getPosition(){
        return enemy.getPosition();
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        batch.begin();
        sprite.draw(batch);
        batch.end();
        weaponView.draw(batch);
    }

    public void update() {
        if (!collision) {
            sprite.setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY() - sprite.getHeight()/2);
            rectangle.get(0).setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY()-sprite.getHeight()/2);
        } else if (collision) {
            enemy.setPosition(enemy.getPrevPos());
            sprite.setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY() - sprite.getHeight()/2);
            rectangle.get(0).setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY()-sprite.getHeight()/2);
            collision = false;
        }
        updateDirection();
    }

    public void updateDirection() {
        try {
            switch (enemy.getMovementDirection()) {
                case 0:
                    sprite.setTexture(GraphicalAssets.ENEMYLEFT);
                    break;
                case 1:
                    sprite.setTexture(GraphicalAssets.ENEMYRIGHT);
                    break;
                case 2:
                    sprite.setTexture(GraphicalAssets.ENEMYUP);
                    break;
                case 3:
                    sprite.setTexture(GraphicalAssets.ENEMYDOWN);
                    break;
            }
        } catch (NullPointerException e) {}
    }

    public void setCollision () {
        collision = true;
    }

    public void dispose() {
        texture.dispose();
    }

    public void updatePosition(){
        sprite.setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY() - sprite.getHeight()/2);
        rectangle.get(0).setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY()-sprite.getHeight()/2);
    }
    
}
