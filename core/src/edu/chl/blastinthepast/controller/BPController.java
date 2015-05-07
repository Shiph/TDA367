package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import edu.chl.blastinthepast.model.*;
import edu.chl.blastinthepast.model.Character;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Shif on 20/04/15.
 */
public class BPController implements PropertyChangeListener {
    private BPModel model;
    private BPView view;

    private BPController(BPModel model, BPView view) {
        this.model = model;
        this.view = view;
        init();
    }

    private void init() {
        view.addListener(this);
    }

    public static BPController create(BPModel model, BPView view) {
        return new BPController(model, view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GameState currentGameState = view.getGameStateController().getGameState();
        GameStateManager gameStateManager = view.getGameStateController();
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
                    try {
                        //model.getPlayer().act("shoot", Gdx.graphics.getDeltaTime());
                        Projectile p = model.getPlayer().getWeapon().pullTrigger();
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
                        gameStateManager.setState(GameStateManager.INGAMEMENU, true);
                        gameStateManager.getGameState().draw();
                    } else if (currentGameState instanceof InGameMenu) {
                        gameStateManager.setState(GameStateManager.PLAY, true);
                        gameStateManager.getGameState().draw();
                    } else if (currentGameState instanceof HighScoreState) {
                        gameStateManager.setState(GameStateManager.MAINMENU, false);
                    }
                break;
            case "enter":
                if (currentGameState instanceof InGameMenu) {
                    ((InGameMenu) currentGameState).select();
                } else if (currentGameState instanceof MainMenu) {
                    ((MainMenu) currentGameState).select();
                } else if (currentGameState instanceof GameOverState) {
                    ((GameOverState) currentGameState).select();
                    gameStateManager.setState(GameStateManager.MAINMENU, false);
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
                if (view.getGameStateController().getGameState() instanceof PlayState) {
                    PlayState playState = (PlayState)view.getGameStateController().getGameState();
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
            default:
                break;
        }
    }



}
