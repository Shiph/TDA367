package edu.chl.blastinthepast.view;

/**
 * Created by Shif on 23/04/15.
 */
public class GameStateManager {

    private GameState gameState;
    private MenuState menuState;
    private PlayState playState;
    public static final int MENU = 0;
    public static final int PLAY = 1;

    public GameStateManager() {
        setState(MENU, true);
    }

    public void setState(int state, boolean newGame) {
        if(gameState!=null) {
            gameState.dispose();
        }
        if (state == MENU && newGame) {
            menuState = new MenuState(this);
            gameState = menuState;
        } else if(state == MENU && !newGame){
            gameState = menuState;
        } else if (state == PLAY && newGame) {
            playState = new PlayState(this);
            gameState = playState;
        } else if(state == PLAY && !newGame) {
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
