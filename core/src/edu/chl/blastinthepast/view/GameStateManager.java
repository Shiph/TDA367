package edu.chl.blastinthepast.view;

/**
 * Created by Shif on 23/04/15.
 */
public class GameStateManager {

    private GameState gameState;
    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager() {
        setState(MENU);
    }

    public void setState(int state) {
        if(gameState!=null) {
            gameState.dispose();
        }
        if (state == MENU) {
            gameState = new MenuState(this);
        } else if (state == PLAY) {
            gameState = new PlayState(this);
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
