package edu.chl.blastinthepast.view.weaponviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.view.WorldObject;

/**
 * Created by jonas on 2015-04-23.
 */
public abstract class WeaponView implements WorldObject {

    private Texture texture;
    private Sprite sprite;
    private WeaponInterface weapon;

    public WeaponView(WeaponInterface weapon, Texture weaponTexture) {
        this.weapon = weapon;
        texture = weaponTexture;
        sprite = new Sprite(texture);
        updateDirection();
        updatePosition();
    }

    @Override
    public Object getObject() {
        return weapon;
    }

    public void draw(SpriteBatch batch) {
        updateDirection();
        updatePosition();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void updateDirection() {
        sprite.setOrigin(0, 0);
        sprite.setRotation(weapon.getDirection().angle());
    }

    public void updatePosition(){
        sprite.setPosition(weapon.getPosition().getX(), weapon.getPosition().getY());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void dispose() {
        texture.dispose();
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

    public Texture getTexture() {
        return texture;
    }



}