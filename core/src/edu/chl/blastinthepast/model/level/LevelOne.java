package edu.chl.blastinthepast.model.level;

import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.utils.Position;
import java.util.Observable;
import java.util.Random;

/**
 * Created by Shif on 2015-05-15.
 */
public class LevelOne implements LevelInterface {

    private BPModel model;
    private int mapWidth = 3200;
    private int mapHeight = 3200;

    public LevelOne(BPModel model) {
        this.model = model;
        model.setMapWidth(mapWidth);
        model.setMapHeight(mapHeight);
        model.addObserver(this);
        //model.spawnEnemies(5);
        model.spawnBoss(new Position(500, 500));
        placeEnemies();
        placePlayer();
    }

    private void placePlayer() {
        model.getPlayer().setPosition(mapWidth/2, mapHeight/2);
    }

    public void placeEnemies() {
        for (Enemy e : model.getEnemies()) {
            Random r = new Random();
            float x = r.nextFloat() * mapWidth;
            float y = r.nextFloat() * mapHeight;
            e.getPosition().setX(x);
            e.getPosition().setY(y);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            if(arg.equals("all enemies is kill")) {
                model.spawnEnemies(5);
                model.spawnBoss(new Position(500, 500));
                placeEnemies();
            }
        }
    }

    public Level getLevel() {
        return Level.ONE;
    }

}
