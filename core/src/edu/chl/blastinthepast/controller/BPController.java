package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelManager;
import edu.chl.blastinthepast.model.level.LevelOne;
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
    private GameStateManager gsm;
    private LevelManager levelManager;

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
        inputHandler = new InputHandler();
        inputHandler.addListener(this);
        Gdx.input.setInputProcessor(inputHandler);
        //levelManager = new LevelManager();
        gsm = new GameStateManager(model, levelManager);
        gsm.addListener(this);
    }

    public static BPController createController() {
        return new BPController();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        GameState currentGameState = gsm.getGameState();
        if (currentGameState instanceof PlayState){
            PlayState playState = (PlayState) currentGameState;
            playState.addListener(this);
        }

        switch(evt.getPropertyName()) {
            case "west":
            case "east":
            case "north":
            case "south":
                if(currentGameState instanceof PlayState) {
                    model.getPlayer().setMovementDirection(evt.getPropertyName());
                    //model.getPlayer().move(Gdx.graphics.getDeltaTime());
                }
                break;
            case "shoot":
                if(currentGameState instanceof PlayState) {
                    if (evt.getNewValue() instanceof Boolean) {
                        boolean b = (boolean) evt.getNewValue();
                        model.getPlayer().isShooting(b);
                    }
                }
                break;
            case "escape":
                if (currentGameState instanceof PlayState) {
                    gsm.setState(GameStateManager.IN_GAME_MENU, true);
                    model.getPlayer().resetMovementDirection();
                    //model.pause();
                } else if (currentGameState instanceof InGameMenu) {
                    gsm.setState(GameStateManager.PLAY, true);
                    //model.unPause();
                } else if (currentGameState instanceof HighScoreState) {
                    gsm.setState(GameStateManager.MAIN_MENU, false);
                }
                break;
            case "enter":
                if (currentGameState instanceof InGameMenu) {
                    ((InGameMenu) currentGameState).select();
                } else if (currentGameState instanceof MainMenu) {
                    ((MainMenu) currentGameState).select();
                } else if (currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).select();
                    gsm.setState(GameStateManager.MAIN_MENU, false);
                }
                break;
            case "up":
                if (currentGameState instanceof InGameMenu) {
                    ((InGameMenu) currentGameState).moveUp();
                } else if (currentGameState instanceof MainMenu) {
                    ((MainMenu) currentGameState).moveUp();
                } else if (currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).moveUp();
                }
                break;
            case "down":
                if (currentGameState instanceof InGameMenu) {
                    ((InGameMenu) currentGameState).moveDown();
                } else if (currentGameState instanceof MainMenu) {
                    ((MainMenu) currentGameState).moveDown();
                } else if (currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).moveDown();
                }
                break;
            case "left":
                if(currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).moveLeft();
                }
                break;
            case "right":
                if(currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).moveRight();
                }
                break;
            case "mouseMoved":
                if (currentGameState instanceof PlayState) {
                    PlayState playState = (PlayState)gsm.getGameState();
                    if (evt.getNewValue() instanceof Position) {
                        Position mouseScreenPos = (Position) evt.getNewValue();
                        Position mouseWorldPos = playState.screenToWorldCoordinates(mouseScreenPos);
                        model.getPlayer().calculateDirection(mouseWorldPos);
                    }
                }
                break;
            case "Collision":
                //model.collision(evt.getOldValue(), evt.getNewValue());
                break;
            case "blocked":
                model.getPlayer().block();
                break;
            case "unblocked":
                model.getPlayer().unBlock();
                break;
            case "use":
                if(currentGameState instanceof PlayState) {
                    if (!model.getChest().isOpened() && model.getPlayer().getPosition().overlaps(model.getChest().getPosition())) {
                        model.getPlayer().addWeapon(model.getChest().open(model.getPlayer()));
                        ((PlayState)gsm.getGameState()).getPlayer().changeWeaponView();
                        ((PlayState)gsm.getGameState()).updateGUIWeapon();
                    }
                }
                break;
            case "space":
                if (gsm.getGameState() instanceof InGameMenu) {
                    ((InGameMenu)gsm.getGameState()).toggleSoundSprite();
                    gsm.getPlayState().toggleSound();
                } else if (gsm.getGameState() instanceof PlayState) {
                    gsm.getPlayState().toggleSound();
                    gsm.getInGameMenu().toggleSoundSprite();
                }
                break;
            case "num1":
                if(currentGameState instanceof PlayState) {
                    try {
                        model.getPlayer().setWeapon(model.getPlayer().getAllWeapons().get(0));
                        ((PlayState) gsm.getGameState()).getPlayer().changeWeaponView();
                        ((PlayState) gsm.getGameState()).updateGUIWeapon();
                    } catch (IndexOutOfBoundsException e) {}
                }
                break;
            case "num2":
                if(currentGameState instanceof PlayState) {
                    try {
                        model.getPlayer().setWeapon(model.getPlayer().getAllWeapons().get(1));
                        ((PlayState) gsm.getGameState()).getPlayer().changeWeaponView();
                        ((PlayState) gsm.getGameState()).updateGUIWeapon();
                    } catch (IndexOutOfBoundsException e) {}
                }
                break;
            case "new game":
                model = new BPModel();
                model.addObserver(this);
                gsm.setModel(model);
                if (levelManager == null) {
                    levelManager = new LevelManager(new LevelOne(model));
                    gsm.setLevelManager(levelManager);
                } else {
                    levelManager.setLevel(new LevelOne(model));
                }
                break;
            case "reload":
                if (currentGameState instanceof PlayState){
                    model.getPlayer().reloadCurrentWeapon();
                }
            default:
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("player is kill")) {
            gsm.setState(GameStateManager.GAMEOVER, true);
            ((GameOverState)gsm.getGameState()).setScore(model.getPlayer().getScore());
        }
    }

}
