package edu.chl.blastinthepast.view.characterviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.utils.GraphicalAssets;
import edu.chl.blastinthepast.view.*;
import edu.chl.blastinthepast.view.weaponviews.AK47View;
import edu.chl.blastinthepast.view.weaponviews.MagnumView;
import edu.chl.blastinthepast.view.weaponviews.WeaponView;
import edu.chl.blastinthepast.view.weaponviews.WeaponViewFactory;

import java.util.ArrayList;

/**
 * Created by jonas on 2015-04-23.
 */
public class PlayerView implements CharacterView, WorldObject {
    private Texture texture;
    private Sprite sprite;
    private Player player;
    private Vector2 direction;
    private WeaponViewFactory weaponViewFactory;
    private WeaponView weaponView;
    private String currentWeapon;
    private boolean collision;

    public PlayerView(Player newPlayer){
        texture = GraphicalAssets.CHARACTERDOWN;
        sprite = new Sprite(texture);
        direction = new Vector2();
        player = newPlayer;
        weaponViewFactory = new WeaponViewFactory();
        weaponView = weaponViewFactory.getWeaponView(player.getCurrentWeapon());
        currentWeapon = player.getCurrentWeapon().toString();
        collision = false;
        updatePosition();
    }

    /**
     * @return the texture of the player character.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * @return the sprite of the player character.
     */
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Character getCharacter() {
        return player;
    }

    public void updatePosition(){
        sprite.setPosition(player.getPosition().getX() - sprite.getWidth() / 2, player.getPosition().getY() - sprite.getWidth() / 2);

    }

    public void updateDirection() {
        if (player.isMovingWest()) {
            sprite.setTexture(GraphicalAssets.CHARACTERLEFT);
        } else if (player.isMovingEast()) {
            sprite.setTexture(GraphicalAssets.CHARACTERRIGHT);
        } else if (player.isMovingNorth()){
            sprite.setTexture(GraphicalAssets.CHARACTERUP);
        } else if (player.isMovingSouth()) {
            sprite.setTexture(GraphicalAssets.CHARACTERDOWN);
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
        //texture.dispose();
    }

    public WeaponView getWeaponView() {
        return weaponView;
    }
}
