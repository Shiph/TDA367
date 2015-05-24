package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.blastinthepast.model.menu.GameOverModel;
import edu.chl.blastinthepast.model.menu.InGameMenuModel;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelManager;
import edu.chl.blastinthepast.model.level.LevelOne;
import edu.chl.blastinthepast.model.menu.MainMenuModel;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.gamestates.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Shif on 20/04/15.
 */
public class BPController extends ApplicationAdapter implements PropertyChangeListener, Observer {

    private BPModel model;
    private InputHandler inputHandler;
    private LevelManager levelManager;
    private GameStateManager gsm;
    private MainMenuController mainMenuController;
    private PlayController playController;
    private InGameMenuController inGameMenuController;
    private HighScoreController highScoreController;
    private GameOverController gameOverController;
    private ActiveController activeController = ActiveController.MAIN_MENU;
    private MainMenuModel mainMenuModel;
    private InGameMenuModel inGameMenuModel;
    private GameOverModel gameOverModel;

    public enum ActiveController {
        MAIN_MENU, PLAY, INGAME_MENU, HIGHSCORE, GAME_OVER
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (model != null) {
            model.update(Gdx.graphics.getDeltaTime());
        }
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
    }

    @Override
    public void create() {
        inputHandler = new InputHandler(this);
        Gdx.input.setInputProcessor(inputHandler);
        mainMenuModel = new MainMenuModel();
        inGameMenuModel = new InGameMenuModel();
        gameOverModel = new GameOverModel();
        gsm = new GameStateManager(model, mainMenuModel, inGameMenuModel, gameOverModel, levelManager);
        mainMenuController = new MainMenuController(this, gsm, mainMenuModel);
        inGameMenuController = new InGameMenuController(this, gsm, inGameMenuModel);
        highScoreController = new HighScoreController(this, gsm);
        gameOverController = new GameOverController(this, gsm, gameOverModel);
        gsm.addListener(this);
    }

    public static BPController createController() {
        return new BPController();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "blocked":
                model.getPlayer().block();
                break;
            case "unblocked":
                model.getPlayer().unBlock();
                break;
        }
    }

    public void newGame() {
        model = new BPModel();
        model.addObserver(this);
        model.getPlayer().addObserver(this);
        gsm.setModel(model);
        if (levelManager == null) {
            levelManager = new LevelManager(new LevelOne(model));
            gsm.setLevelManager(levelManager);
        } else {
            levelManager.setLevel(new LevelOne(model));
        }
        playController = new PlayController(this, gsm, model);
        setActiveController(ActiveController.PLAY);
        gsm.setState(GameStateManager.PLAY, false);
        gsm.getPlayState().addListener(this);
    }

    public void keyDown(int keyCode) {
        switch (activeController) {
            case MAIN_MENU:
                mainMenuController.keyDown(keyCode);
                break;
            case INGAME_MENU:
                inGameMenuController.keyDown(keyCode);
                break;
            case PLAY:
                playController.keyDown(keyCode);
                break;
            case HIGHSCORE:
                highScoreController.keyDown(keyCode);
                break;
            case GAME_OVER:
                gameOverController.keyDown(keyCode);
                break;
        }
    }

    public void keyUp(int keyCode) {
        if (activeController == ActiveController.PLAY) {
            playController.keyUp(keyCode);
        }
    }

    public void touchDragged(Position p) {
        if (activeController == ActiveController.PLAY) {
            playController.touchDragged(p);
        }
    }

    public void touchDown() {
        if(activeController == ActiveController.PLAY) {
            playController.touchDown();
        }
    }

    public void touchUp() {
        if(activeController == ActiveController.PLAY) {
            playController.touchUp();
        }
    }

    public void setActiveController(ActiveController ac) {
        activeController = ac;
        if (ac == ActiveController.MAIN_MENU) {
            gsm.getMainMenu().playMusic();
        }
    }

    public void mouseMoved(int screenX, int screenY) {
        if (activeController == ActiveController.PLAY) {
            playController.mouseMoved(screenX, screenY);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("player is kill")) {
            gsm.setState(GameStateManager.GAMEOVER, true);
            gameOverModel.setScore(model.getPlayer().getScore());
            setActiveController(ActiveController.GAME_OVER);
        }
    }

}