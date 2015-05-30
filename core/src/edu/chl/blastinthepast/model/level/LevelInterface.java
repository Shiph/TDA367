package edu.chl.blastinthepast.model.level;

import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.player.CharacterI;
import java.util.ArrayList;

/**
 * Created by Shif on 2015-05-15.
 */
public interface LevelInterface {

    enum Level {
        ONE
    }

    float getMapWidth();
    float getMapHeight();
    String getMapName();
    Level getLevel();
    ArrayList<CharacterI> getEnemies();
    CharacterI getPlayer();
    Boss getBoss();
    void spawnNewEnemies();
    boolean playerIsColliding();

}
