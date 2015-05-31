package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.level.LevelManager;
import edu.chl.blastinthepast.model.level.LevelOne;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.view.gamestates.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Shif on 20/04/15.
 */
public class BPController extends ApplicationAdapter implements PropertyChangeListener{

    private BPModel model;
    private InputHandler inputHandler;
    private LevelManager levelManager;
    private GameStateManager gsm;
    private MainMenuController mainMenuController;
    private PlayController playController;
    private InGameMenuController inGameMenuController;
    private HighScoreController highScoreController;
    private GameOverController gameOverController;
    private ActiveController activeController = ActiveControllerEnum.MAIN_MENU;

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
        gsm = new GameStateManager(model, levelManager);
        inGameMenuController = new InGameMenuController(this, gsm);
        highScoreController = new HighScoreController(this, gsm);
        gameOverController = new GameOverController(this, gsm);
        mainMenuController = new MainMenuController(this, gsm);
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
            case "Player died":
                gameOverController.setScore(model.getPlayer().getScore());
                setActiveController(ActiveControllerEnum.GAME_OVER);
                gsm.setState(GameStateManager.GAMEOVER, true);
        }
    }

    public void newGame() {
        LevelOne levelOne = new LevelOne();
        if (levelManager == null) {
            levelManager = new LevelManager(levelOne);
            gsm.setLevelManager(levelManager);
        } else {
            levelManager.setLevel(levelOne);
        }
        model = new BPModel(levelOne);
        model.addListener(this);
        gsm.setModel(model);
        playController = new PlayController(this, gsm, model);
        setActiveController(ActiveControllerEnum.PLAY);
        gsm.setState(GameStateManager.PLAY, false);
    }

    public void keyDown(int keyCode) {
        switch (activeController.getID()) {
            case "Main menu":
                mainMenuController.keyDown(keyCode);
                break;
            case "Ingame menu":
                inGameMenuController.keyDown(keyCode);
                break;
            case "Play":
                playController.keyDown(keyCode);
                break;
            case "High score":
                highScoreController.keyDown(keyCode);
                break;
            case "Game over":
                gameOverController.keyDown(keyCode);
                break;
        }
    }

    public void keyUp(int keyCode) {
        if (activeController.getID().equals("Play")) {
            playController.keyUp(keyCode);
        }
    }

    public void touchDragged(Position p) {
        if (activeController.getID().equals("Play")) {
            playController.touchDragged(p);
        }
    }

    public void touchDown() {
        if(activeController.getID().equals("Play")) {
            playController.touchDown();
        }
    }

    public void touchUp() {
        if(activeController.getID().equals("Play")) {
            playController.touchUp();
        }
    }

    public void setActiveController(ActiveController ac) {
        activeController = ac;
        if (ac.toString().equals("Main menu")) {
            gsm.getMainMenu().playMusic();
        }
    }

    public void mouseMoved(int screenX, int screenY) {
        if (activeController.getID().equals("Play")) {
            playController.mouseMoved(screenX, screenY);
        }
    }

}