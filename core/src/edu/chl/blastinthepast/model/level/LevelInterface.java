package edu.chl.blastinthepast.model.level;

import java.util.Observer;

/**
 * Created by Shif on 2015-05-15.
 */
public interface LevelInterface extends Observer {

    enum Level {
        ONE
    }

    void placeEnemies();
    Level getLevel();

}
