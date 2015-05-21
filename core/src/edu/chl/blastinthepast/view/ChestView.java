package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.chest.Chest;
import edu.chl.blastinthepast.utils.GraphicalAssets;

import java.util.ArrayList;

/**
 * Created by qwerty458 on 5/4/15.
 */
public class ChestView implements WorldObject {
    private ArrayList<Rectangle> rectangles;
    private Sprite sprite;
    private Chest chest;

    public ChestView (Chest chest) {
        this.chest = chest;
        rectangles = new ArrayList<Rectangle>();
        rectangles.add(new Rectangle());
        sprite = new Sprite(GraphicalAssets.CHESTCLOSED);
        sprite.setX(chest.getPosition().getX());
        sprite.setY(chest.getPosition().getY());
        rectangles.get(0).height = sprite.getHeight();
        rectangles.get(0).width = sprite.getWidth();
    }

    @Override
    public Object getObject() {
        return chest;
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public void dispose() {

    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

    public void openChest() {
        sprite.setTexture(GraphicalAssets.CHESTOPEN);
    }

    public void update() {
        if (chest.isOpened()) {
            openChest();
        }
    }

}
