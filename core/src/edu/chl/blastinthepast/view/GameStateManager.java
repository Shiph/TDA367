package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.view.MenuState;
import edu.chl.blastinthepast.controller.GameState;

/**
 * Created by Shif on 23/04/15.
 */
public class GameStateManager {

    private GameState gameState;
    private BPModel model;
    private MenuState menuState;
    private PlayState playState;
    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager(BPModel model) {
        this.model = model;
        setState(MENU, true);
    }


    public GameStateManager() {
        setState(MENU, true);
    }

    public void setState(int state, boolean newGame) {
        if (gameState != null) {
            gameState.dispose();
        }

        if (state == MENU && newGame) {
            menuState = new MenuState(this, model);
            gameState = menuState;
        } else if (state == MENU && !newGame) {
            gameState = menuState;
        } else if (state == PLAY && newGame) {
            playState = new PlayState(this, model);
            gameState = playState;
        } else if (state == PLAY && !newGame) {
            gameState = playState;
        }
    }

    public void update(float dt) {
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }

    public GameState getGameState() {
        return gameState;
    }
}
