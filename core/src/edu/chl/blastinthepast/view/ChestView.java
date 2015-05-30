package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.chest.Chest;
import edu.chl.blastinthepast.view.assets.GraphicalAssets;

/**
 * Created by qwerty458 on 5/4/15.
 */
public class ChestView implements WorldObject {
    private Sprite sprite;
    private Chest chest;

    public ChestView (Chest chest) {
        this.chest = chest;
        sprite = new Sprite(GraphicalAssets.CHESTCLOSED);
        sprite.setX(chest.getPosition().getX());
        sprite.setY(chest.getPosition().getY());
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

    public void openChest() {
        sprite.setTexture(GraphicalAssets.CHESTOPEN);
    }

    public void update() {
        if (chest.isOpened()) {
            openChest();
        }
    }
}
