package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.Weapon;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by jonas on 2015-04-23.
 */
public class WeaponView {
    private Texture texture;
    private Sprite sprite;
    private Weapon weapon;

    public WeaponView(Weapon newWeapon) {
        weapon=newWeapon;
        texture= GraphicalAssets.AK47;
        sprite=new Sprite(texture);
        updateDirection();
        updatePosition();
    }

    public void updateDirection(){
        sprite.setOrigin(-25, 0);
        sprite.setRotation(weapon.getDirection().angle());
    }

    public void updatePosition(){
        sprite.setPosition(weapon.getPosition().getX() + 50, weapon.getPosition().getY() + 25);
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

}
