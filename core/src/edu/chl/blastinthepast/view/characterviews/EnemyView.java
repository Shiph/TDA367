package edu.chl.blastinthepast.view.characterviews;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.utils.PositionInterface;
import edu.chl.blastinthepast.view.weaponviews.AK47View;
import edu.chl.blastinthepast.view.ProjectileView;
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
    private Enemy enemy;
    private WeaponViewFactory weaponViewFactory;
    private WeaponView weaponView;

    public EnemyView(Enemy enemy, Texture texture) {
        this.enemy = enemy;
        this.texture = texture;
        sprite = new Sprite(texture);
        weaponViewFactory = new WeaponViewFactory();
        weaponView = weaponViewFactory.getWeaponView(enemy.getCurrentWeapon());
    }

    public Texture getTexture() {
        return texture;
    }

    public void setRectangles(ArrayList<Rectangle> rectangles) {

    }

    public void setRectangle(Rectangle rectangle) {}

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Character getCharacter() {
        return enemy;
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
        sprite.setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY() - sprite.getHeight()/2);
        updateDirection();
    }

    public abstract void updateDirection();

    public void dispose() {
        texture.dispose();
    }

    public void updatePosition(){
        sprite.setPosition(enemy.getPosition().getX()-sprite.getWidth()/2, enemy.getPosition().getY() - sprite.getHeight()/2);
    }
    
}
