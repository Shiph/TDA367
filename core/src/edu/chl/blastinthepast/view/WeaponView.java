package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.Weapon;
import edu.chl.blastinthepast.model.WeaponInterface;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by jonas on 2015-04-23.
 */
public class WeaponView {

    private Texture texture;
    private Sprite sprite;
    private WeaponInterface weapon;

    public WeaponView(WeaponInterface newWeapon) {
        weapon = newWeapon;
        texture= GraphicalAssets.AK47;
        sprite = new Sprite(texture);
        updateDirection();
        updatePosition();
    }

    public void updateDirection(){
        sprite.setOrigin(0, 0);
        sprite.setRotation(weapon.getDirection().angle());
    }

    public void updatePosition(){
        sprite.setPosition(weapon.getPosition().getX(), weapon.getPosition().getY());
    }

    public void draw(SpriteBatch batch){
        updateDirection();
        updatePosition();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void dispose() {
        //texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }
}
