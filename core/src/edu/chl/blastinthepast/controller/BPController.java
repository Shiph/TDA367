package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import edu.chl.blastinthepast.model.*;
import edu.chl.blastinthepast.model.Character;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.ProjectileInterface;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.gamestates.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Shif on 20/04/15.
 */
public class BPController extends ApplicationAdapter implements PropertyChangeListener {

    private BPModel model;
    private InputHandler inputHandler;
    private GameStateManager gsm;

    private BPController(BPModel model) {
        this.model = model;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        model.update(Gdx.graphics.getDeltaTime());
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
    }

    @Override
    public void create() {
        inputHandler = new InputHandler();
        inputHandler.addListener(this);
        Gdx.input.setInputProcessor(inputHandler);
        gsm = new GameStateManager(model);
    }

    public static BPController create(BPModel model) {
        return new BPController(model);
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
                    ((GameOverState) currentGameState).moveLeft();
                }
            case "east":
                if(currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).moveRight();
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
                        gsm.setState(GameStateManager.INGAMEMENU, true);
                        gsm.getGameState().draw();
                    } else if (currentGameState instanceof InGameMenu) {
                        gsm.setState(GameStateManager.PLAY, true);
                        gsm.getGameState().draw();
                    } else if (currentGameState instanceof HighScoreState) {
                        gsm.setState(GameStateManager.MAINMENU, false);
                    }
                break;
            case "enter":
                if (currentGameState instanceof InGameMenu) {
                    ((InGameMenu) currentGameState).select();
                } else if (currentGameState instanceof MainMenu) {
                    ((MainMenu) currentGameState).select();
                } else if (currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).select();
                    gsm.setState(GameStateManager.MAINMENU, false);
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
            case "characterHit": //Temporary collision detection
                if (evt.getOldValue() instanceof Projectile && evt.getNewValue() instanceof Character){
                    Character c=(Character)evt.getNewValue();
                    Projectile p = (Projectile) evt.getOldValue();
                    model.collision(c, p);
                }
                break;
            case "use":
                if(currentGameState instanceof PlayState) {
                    if(!model.getChest().isOpened()) {
                        model.getPlayer().addWeapon(model.getChest().open(model.getPlayer()));
                    }
                }
                break;
            default:
                break;
        }
    }



}
