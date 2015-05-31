package edu.chl.blastinthepast.view.characterviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.view.assets.GraphicalAssets;
import edu.chl.blastinthepast.view.weaponviews.WeaponView;
import edu.chl.blastinthepast.view.weaponviews.WeaponViewFactory;

/**
 * Created by jonas on 2015-04-23.
 */
public class PlayerView implements CharacterView {
    private Texture texture;
    private Sprite sprite;
    private Player player;
    private WeaponViewFactory weaponViewFactory;
    private WeaponView weaponView;
    private String currentWeapon;

    public PlayerView(Player player){
        //texture = GraphicalAssets.CHARACTERDOWN;
        texture = GraphicalAssets.PLAYER;
        sprite = new Sprite(texture);
        this.player = player;
        weaponViewFactory = new WeaponViewFactory();
        weaponView = weaponViewFactory.getWeaponView(player.getCurrentWeapon());
        currentWeapon = player.getCurrentWeapon().toString();
        updatePosition();
    }

    /**
     * @return the texture of the player character.
     */
    @Override
    public Texture getTexture() {
        return texture;
    }

    /**
     * @return the sprite of the player character.
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public CharacterI getCharacter() {
        return player;
    }

    public void updatePosition(){
        sprite.setPosition(player.getPosition().getX() - sprite.getWidth() / 2, player.getPosition().getY() - sprite.getWidth() / 2);

    }

    public void updateDirection() {
        sprite.setOrigin(16, 16);
        if (!(player.getCurrentWeapon().getAimVector() == null)) {
            sprite.setRotation(player.getCurrentWeapon().getAimVector().angle());
        }
    }



    @Override
    public Object getObject() {
        return  player;
    }

    @Override
    public void draw(SpriteBatch batch) {
        updatePosition();
        updateDirection();
        batch.begin();
        sprite.draw(batch);
        batch.end();
        weaponView.draw(batch);
    }

    public void changeWeaponView() {
        if(!currentWeapon.equals(player.getCurrentWeapon().toString())) {
            weaponView = weaponViewFactory.getWeaponView(player.getCurrentWeapon());
            currentWeapon = player.getCurrentWeapon().toString();
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public WeaponView getWeaponView() {
        return weaponView;
    }
}
