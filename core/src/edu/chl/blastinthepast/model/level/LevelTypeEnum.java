package edu.chl.blastinthepast.model.level;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public enum LevelTypeEnum implements LevelType {
    ONE("One");

    private String type;

    LevelTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String getID() {
        return type;
    }
}
