package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import edu.chl.blastinthepast.model.entities.Character;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.blastinthepast.model.entities.Projectile;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelInterface;
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
        gsm = new GameStateManager(model);
        gsm.addListener(this);
        //levelManager = new LevelManager(model);
    }

    public static BPController createController() {
        return new BPController();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //GameState currentGameState = view.getGameStateController().getGameState();
        //GameStateManager gameStateManager = view.getGameStateController();

        GameState currentGameState = gsm.getGameState();
        if (currentGameState instanceof PlayState){
            PlayState playState = (PlayState) currentGameState;
            playState.addListener(this);
        }

        switch(evt.getPropertyName()) {
            case "west":
                if(currentGameState instanceof GameOverState) {
                    //((GameOverState) currentGameState).moveLeft();
                }
            case "east":
                if(currentGameState instanceof GameOverState) {
                    //((GameOverState) currentGameState).moveRight();
                }
            case "north":
            case "south":
                if(currentGameState instanceof PlayState) {
                    model.getPlayer().setMovementDirection(evt.getPropertyName());
                    model.getPlayer().move(Gdx.graphics.getDeltaTime());
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
                    model.collision(evt.getOldValue(), evt.getNewValue());
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
                        model.getPlayer().setWeapon(model.getPlayer().getWeaponArray().get(0));
                        ((PlayState) gsm.getGameState()).getPlayer().changeWeaponView();
                        ((PlayState) gsm.getGameState()).updateGUIWeapon();
                    } catch (IndexOutOfBoundsException e) {}
                }
                break;
            case "num2":
                if(currentGameState instanceof PlayState) {
                    try {
                        model.getPlayer().setWeapon(model.getPlayer().getWeaponArray().get(1));
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
                } else {
                    levelManager.setLevel(new LevelOne(model));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("player is kill")) {
            gsm.setState(GameStateManager.GAMEOVER, true);
        }
    }

}
