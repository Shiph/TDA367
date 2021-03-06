package edu.chl.blastinthepast.model.level;

import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.enemy.EnemyFactory;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.utils.CollisionLayerAdapter;
import java.util.ArrayList;

/**
 * Created by Shif on 2015-05-30.
 */
public abstract class Level implements LevelInterface {

    protected CharacterI player;
    protected Boss boss;
    protected ArrayList<CharacterI> enemies;
    protected EnemyFactory enemyFactory = new EnemyFactory();
    protected int mapWidth;
    protected int mapHeight;
    protected String mapName;
    protected CollisionLayerAdapter layer;

    public Level(int mapWidth, int mapHeight, String mapName) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.mapName = mapName;
        layer = new CollisionLayerAdapter(mapName);
    }

    @Override
    public CharacterI getPlayer() {
        return player;
    }

    @Override
    public CharacterI getBoss() {
        return boss;
    }

    @Override
    public ArrayList<CharacterI> getEnemies() {
        return enemies;
    }

    @Override
    public float getMapWidth() {
        return mapWidth;
    }

    @Override
    public float getMapHeight() {
        return mapHeight;
    }

    @Override
    public String getMapName() {
        return mapName;
    }

    @Override
    public boolean playerIsColliding() {
        int playerMapX, playerMapY;
        if (((Player)player).isMovingEast()) {
            playerMapX = Math.round((((Player)player).getPosition().getX() + ((Player)player).getRectangle().getWidth()) / layer.getTileWidth()) - 1;
        } else {
            playerMapX = Math.round(((Player)player).getPosition().getX() / layer.getTileWidth()) - 1;
        }
        if (((Player)player).isMovingNorth()) {
            playerMapY = Math.round((player.getPosition().getY() + player.getRectangle().getHeight())/ layer.getTileHeight()) - 1;
        } else {
            playerMapY = Math.round(player.getPosition().getY()/ layer.getTileHeight()) - 1;
        }

        if (layer.getCell(playerMapX, playerMapY) != null) {
            if (layer.getCell(playerMapX, playerMapY).getTile().getProperties().containsKey("blocked")) {
                return true;
            }
        }
        return false;
    }

}
