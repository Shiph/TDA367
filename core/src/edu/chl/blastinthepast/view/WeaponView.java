package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.Weapon;

/**
 * Created by jonas on 2015-04-23.
 */
public class WeaponView {
    private Texture texture;
    private Sprite sprite;
    private Weapon weapon;

    public WeaponView(Weapon newWeapon)
    {
        weapon=newWeapon;
        texture= new Texture(Gdx.files.local("arrowWeapon.png"));
        sprite=new Sprite(texture);
        updateDirection();
        updatePosition();

    }

    public void updateDirection(){
        sprite.setOrigin(-25, 0);
        sprite.setRotation(weapon.getDirection().angle());
    }

    public void updatePosition(){
        sprite.setPosition(weapon.getPosition().getX()+50, weapon.getPosition().getY()+25);
    }

    public void draw(SpriteBatch batch){
        updateDirection();
        updatePosition();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

}
