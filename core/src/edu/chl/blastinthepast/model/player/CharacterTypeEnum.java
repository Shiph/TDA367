package edu.chl.blastinthepast.model.player;

/**
 * Created by Mattias on 15-05-28.
 */
public enum CharacterTypeEnum implements CharacterType {
    PLAYER("Player"), PLEB("Pleb"), BOSS("Boss");

    private String type;

    CharacterTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String getID() {
        return type;
    }
}
