package edu.chl.blastinthepast.controller;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public enum ActiveControllerEnum implements ActiveController {
    MAIN_MENU("Main menu"), PLAY("Play"), INGAME_MENU("Ingame menu"), HIGHSCORE("High score"), GAME_OVER("Game over");

    String type;

    ActiveControllerEnum(String type) {
       this.type = type;
    }

    @Override
    public String getID() {
        return type;
    }
}
