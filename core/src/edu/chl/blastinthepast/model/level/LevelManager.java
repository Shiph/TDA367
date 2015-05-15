package edu.chl.blastinthepast.model.level;

/**
 * Created by Shif on 2015-05-15.
 */
public class LevelManager {

    private LevelInterface level;

    public LevelManager(LevelInterface level) {
        this.level = level;
    }

    public void setLevel(LevelInterface level) {
        this.level = level;
    }

    public LevelInterface getLevel() {
        return level;
    }

}
