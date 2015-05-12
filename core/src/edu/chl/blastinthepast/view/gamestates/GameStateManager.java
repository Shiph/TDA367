package edu.chl.blastinthepast.view.gamestates;

import edu.chl.blastinthepast.model.BPModel;

/**
 * Created by Shif on 23/04/15.
 */
public class GameStateManager {

    private GameState gameState;
    private BPModel model;
    private MainMenu mainMenu;
    private HighScoreState highScoreState;
    private GameOverState gameOverState;
    private InGameMenu inGameMenu;
    private PlayState playState;
    public static final int MAIN_MENU = 0;
    public static final int PLAY = 1;
    public static final int IN_GAME_MENU = 2;
    public static final int SAVES = 3;
    public static final int OPTIONS = 4;
    public static final int HIGHSCORES = 5;
    public static final int GAMEOVER = 6;

    public GameStateManager(BPModel model) {
        this.model = model;
        mainMenu = new MainMenu(this, model);
        inGameMenu = new InGameMenu(this, model);
        highScoreState = new HighScoreState(this, model);
        gameOverState = new GameOverState(this, model);
        setState(MAIN_MENU, false);
    }

    public void setState(int state, boolean inGame) {
        if (gameState != null) {
            gameState.dispose();
        }
        switch(state) {
            case MAIN_MENU:
                gameState = mainMenu;
                break;
            case IN_GAME_MENU:
                gameState = inGameMenu;
                break;
            case PLAY:
                if(!inGame) {
                    mainMenu.dispose();
                    model.newGame();
                    playState = new PlayState(this, model);
                    gameState = playState;
                } else {
                    gameState = playState;
                }
                break;
            case SAVES:
                if(inGame) {
                    //Save
                } else {
                    //Load
                }
                break;
            case OPTIONS:
                //gameState = options;
                break;
            case HIGHSCORES:
                gameState = highScoreState;
                break;
            case GAMEOVER:
                gameState = gameOverState;
                break;
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

    public PlayState getPlayState() {
        return playState;
    }

    public InGameMenu getInGameMenu() {
        return inGameMenu;
    }

}
