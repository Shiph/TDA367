package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.level.LevelInterface;
import edu.chl.blastinthepast.model.player.Player;
import java.util.ArrayList;

/**
 * Created by Shif on 2015-05-30.
 */
public class MockLevel implements LevelInterface {

    public ArrayList<MockEnemy> enemies;
    public MockPlayer player;

    public MockLevel() {
        player = new MockPlayer();
        enemies = new ArrayList<MockEnemy>();
        for (int i=0; i<10; i++) {
            MockEnemy e = new MockEnemy();
        }
    }

    @Override
    public float getMapWidth() {
        return 1000;
    }

    @Override
    public float getMapHeight() {
        return 1000;
    }

    @Override
    public String getMapName() {
        return "map name";
    }

    @Override
    public Level getLevel() {
        return null;
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public Boss getBoss() {
        return null;
    }

    @Override
    public void spawnNewEnemies() {

    }

    @Override
    public boolean playerIsColliding() {
        return false;
    }
}
