package edu.chl.blastinthepast.view.characterviews;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.utils.PositionInterface;
import edu.chl.blastinthepast.view.projectileviews.ProjectileView;
import edu.chl.blastinthepast.view.weaponviews.WeaponView;
import edu.chl.blastinthepast.view.WorldObject;
import edu.chl.blastinthepast.view.weaponviews.WeaponViewFactory;

import java.util.ArrayList;

/**
 * Created by jonas on 2015-04-23.
 */
public abstract class EnemyView implements CharacterView, WorldObject {
    private Texture texture;
    private Sprite sprite;
    private static final int DELAY = 2500;
    private ArrayList<Rectangle> rectangle;
    private Enemy enemy;
    private WeaponViewFactory weaponViewFactory;
    private WeaponView weaponView;
    private boolean collision;

    public EnemyView(Enemy enemy, Texture texture) {
        this.enemy = enemy;
        this.texture = texture;
        sprite = new Sprite(texture);
        weaponViewFactory = new WeaponViewFactory();
        weaponView = weaponViewFactory.getWeaponView(enemy.getCurrentWeapon());
        rectangle = new ArrayList<Rectangle>();
        rectangle.add(new Rectangle());
        rectangle.get(0).height = getSprite().getHeight();
        rectangle.get(0).width = getSprite().getWidth();
        collision = false;
    }

    public Texture getTexture() {
        return texture;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangle;
    }

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

    public PositionInterface getPosition(){
        return enemy.getPosition();
    }

    @Override
    public Object getObject() {
        return enemy;
    }

    @Override
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

    public abstract void updateDirection();

    public void dispose() {
        texture.dispose();
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle.get(0);
    }

    public void updatePosition(){
        sprite.setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY() - sprite.getHeight()/2);
        rectangle.get(0).setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY()-sprite.getHeight()/2);
    }
    
}
