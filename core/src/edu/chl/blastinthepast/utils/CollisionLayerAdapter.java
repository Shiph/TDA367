package edu.chl.blastinthepast.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by Shif on 2015-05-30.
 */
public class CollisionLayerAdapter implements CollisionLayer {

    private TiledMapTileLayer layer;
    private TiledMap map;
    private float tileWidth;
    private float tileHeight;

    public CollisionLayerAdapter(String mapName) {
        map = new TmxMapLoader().load(mapName);
        layer = (TiledMapTileLayer)map.getLayers().get(1);
        tileWidth = layer.getTileWidth();
        tileHeight = layer.getTileHeight();
    }

    @Override
    public float getTileWidth() {
        return tileWidth;
    }

    @Override
    public float getTileHeight() {
        return tileHeight;
    }

    @Override
    public TiledMapTileLayer.Cell getCell(int x, int y) {
        return layer.getCell(x, y);
    }

}
