package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
    private PropertyChangeSupport pcs;
    private LevelManager levelManager;

    public GameStateManager(BPModel model, LevelManager levelManager) {
        this.model = model;
        this.levelManager = levelManager;
        mainMenu = new MainMenu(this, model);
        inGameMenu = new InGameMenu(this, model);
        highScoreState = new HighScoreState(this, model);
        gameOverState = new GameOverState(this, model);
        pcs = new PropertyChangeSupport(this);
        setState(MAIN_MENU, false);
    }

    /**
     * Sets the active state.
     *
     * @param state - the state to be activated
     * @param inGame - if a game already has been started or not
     */
    public void setState(int state, boolean inGame) {
        if (gameState != null) {
            gameState.dispose();
        }
        switch(state) {
            case MAIN_MENU:
                gameState = mainMenu;
                Gdx.input.setCursorImage(null, 0, 0);
                break;
            case IN_GAME_MENU:
                gameState = inGameMenu;
                Gdx.input.setCursorImage(null, 0, 0);
                model.pause();
                break;
            case PLAY:
                if(!inGame) {
                    mainMenu.dispose();
                    playState = new PlayState(this, model, levelManager.getLevel());
                    gameState = playState;
                } else {
                    gameState = playState;
                    playState.getGUI().setCrosshairCursor();
                }
                model.unPause();
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
                Gdx.input.setCursorImage(null, 0, 0);
                break;
            case GAMEOVER:
                model.pause();
                gameState = gameOverState;
                Gdx.input.setCursorImage(null, 0, 0);
                break;
            default:
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

    public GameOverState getGameOverState() {
        return gameOverState;
    }

    public InGameMenu getInGameMenu() {
        return inGameMenu;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void addListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }

    public void setModel(BPModel model) {
        this.model = model;
    }

    public void setLevelManager(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

}
