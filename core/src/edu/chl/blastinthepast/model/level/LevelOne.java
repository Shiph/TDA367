package edu.chl.blastinthepast.model.level;

import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.Position;
import java.util.Observable;
import java.util.Random;

/**
 * Created by Shif on 2015-05-15.
 */
public class LevelOne implements LevelInterface {

    private BPModel model;

    public LevelOne(BPModel model) {
        this.model = model;
        model.addObserver(this);
        //model.spawnEnemies(5);
        model.spawnBoss(new Position(500, 500));
        placeEnemies();
        placePlayer();
    }

    private void placePlayer() {
        model.getPlayer().setPosition(Constants.MAP_WIDTH/2, Constants.MAP_HEIGHT/2);
    }

    public void placeEnemies() {
        for (Enemy e : model.getEnemies()) {
            Random r = new Random();
            float x = r.nextFloat() * Constants.MAP_WIDTH;
            float y = r.nextFloat() * Constants.MAP_HEIGHT;
            while (x <= model.getPlayer().getPosition().getX() + Constants.CAMERA_WIDTH/2 && //Makes enemies spawn outside the players view
                    x >= model.getPlayer().getPosition().getX() - Constants.CAMERA_WIDTH/2) {
                while (y <= model.getPlayer().getPosition().getY() + Constants.CAMERA_HEIGHT/2 &&
                        y >= model.getPlayer().getPosition().getY() - Constants.CAMERA_HEIGHT/2) {
                    y = r.nextFloat() * Constants.MAP_HEIGHT;
                }
                x = r.nextFloat() * Constants.MAP_WIDTH;
            }
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
