package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.controller.GameState;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.view.PlayState;

/**
 * Created by Shif on 23/04/15.
 */
public class GameStateManager {

    private GameState gameState;
    private BPModel model;
    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager(BPModel model) {
        this.model=model;
        setState(PLAY);
    }

    public void setState(int state) {
        if(gameState!=null) {
            gameState.dispose();
        }
        if (state == MENU) {
            // switch to menu state
        } else if (state == PLAY) {
            // switch to play state
            gameState = new PlayState(this, model);
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
