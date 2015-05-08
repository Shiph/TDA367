package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.*;
import edu.chl.blastinthepast.model.Character;
import edu.chl.blastinthepast.utils.GraphicalAssets;

import java.util.ArrayList;

/**
 * Created by jonas on 2015-04-23.
 */
public class PlayerView implements CharacterView {
    private Texture texture;
    private Sprite sprite;
    private ArrayList<Rectangle> rectangle;
    private Player player;
    private Vector2 direction;
    private WeaponView weaponView;
    private boolean collision;

    public PlayerView(Player newPlayer){
        texture = GraphicalAssets.CHARACTERDOWN;
        sprite = new Sprite(texture);
        rectangle = new ArrayList<Rectangle>();
        rectangle.add(new Rectangle());
        direction = new Vector2();
        //rectangle.get(0).x = 800/2 - 64/2;
        //rectangle.get(0).y = 480/2 - 64/2;
        rectangle.get(0).height = sprite.getHeight();
        rectangle.get(0).width = sprite.getWidth();
        //sprite.setX(rectangle.get(0).x);
        //sprite.setY(rectangle.get(0).y);
        player = newPlayer;
        weaponView = new WeaponView(player.getWeapon());
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
        return player;
    }

    @Override
    public void hit(ProjectileView projectile) {
        System.out.println(this);
    }

    public void updatePosition(){
        if (!collision) {
            sprite.setPosition(player.getPosition().getX()-sprite.getWidth()/2, player.getPosition().getY()-sprite.getWidth()/2);
            rectangle.get(0).setPosition(player.getPosition().getX()-sprite.getWidth()/2, player.getPosition().getY() -sprite.getHeight()/2);
        } else if (collision) {
            player.setPosition(player.getPrevPos());
            sprite.setPosition(player.getPosition().getX(), player.getPosition().getY());
            rectangle.get(0).setPosition(player.getPosition().getX(), player.getPosition().getY());
            setCollision();
        }
    }

    public void updateDirection() {
        try {
            switch (player.getMovementDirection()) {
                case "west":
                    sprite.setTexture(GraphicalAssets.CHARACTERLEFT);
                    break;
                case "east":
                    sprite.setTexture(GraphicalAssets.CHARACTERRIGHT);
                    break;
                case "north":
                    sprite.setTexture(GraphicalAssets.CHARACTERUP);
                    break;
                case "south":
                    sprite.setTexture(GraphicalAssets.CHARACTERDOWN);
                    break;
            }
        } catch (NullPointerException e) {}
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        updateDirection();
        //rotate();
        batch.begin();
        sprite.draw(batch);
        batch.end();
        weaponView.draw(batch);
    }

    public void rotate () {
        direction = player.getAimDirection();
        sprite.setOriginCenter();
        sprite.setRotation(direction.angle());
    }

    public void setCollision () {
        collision ^= true;
    }

    public void dispose() {
        //texture.dispose();
    }

}
