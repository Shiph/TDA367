package edu.chl.blastinthepast.utils;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import edu.chl.blastinthepast.model.player.Player;

/**
 * Created by Shif on 2015-05-30.
 */
public interface CollisionLayer {

    float getTileWidth();
    float getTileHeight();
    TiledMapTileLayer.Cell getCell(int x, int y);

}
