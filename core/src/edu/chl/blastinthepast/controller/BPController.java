package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.ProjectileInterface;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Shif on 20/04/15.
 */
public class BPController extends ApplicationAdapter implements PropertyChangeListener {

    private BPModel model;
    private BPView view;
    private InputHandler inputHandler;
    private GameStateManager gsm;

    private BPController(BPModel model, BPView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void render () {
        inputHandler.checkForInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
    }

    @Override
    public void create() {
        view.addListener(this);
        inputHandler = new InputHandler();
        inputHandler.addListener(this);
        Gdx.input.setInputProcessor(inputHandler);
        gsm = new GameStateManager(model);
    }

    public static BPController create(BPModel model, BPView view) {
        return new BPController(model, view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GameState currentGameState = gsm.getGameState();

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
                    try {
                        //model.getPlayer().act("shoot", Gdx.graphics.getDeltaTime());
                        ProjectileInterface p = model.getPlayer().getWeapon().pullTrigger();
                        if (p != null) {
                            model.addProjectile(p);
                        } else {
                            //??
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage()); // player doesn't have a weapon or is out of bullets
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
                if (gsm.getGameState() instanceof PlayState) {
                    PlayState playState = (PlayState)gsm.getGameState();
                    if (evt.getNewValue() instanceof Position) {
                        Position mouseScreenPos = (Position) evt.getNewValue();
                        Position mouseWorldPos = playState.screenToWorldCoordinates(mouseScreenPos);
                        model.getPlayer().calculateDirection(mouseWorldPos);
                    }
                }
                break;
            default:
                break;
        }
    }

}
