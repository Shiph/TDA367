package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.Projectile;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.BPView;
import edu.chl.blastinthepast.view.GameStateManager;
import edu.chl.blastinthepast.view.MenuState;
import edu.chl.blastinthepast.view.PlayState;

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

        switch(evt.getPropertyName()) {
            case "west":
            case "east":
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
                            System.out.println("projectile is null");
                        }
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage()); // player doesn't have a weapon or is out of bullets
                    }
                }
                break;
            case "escape":
                    if (currentGameState instanceof PlayState) {
                        gameStateManager.setState(GameStateManager.MENU, false);
                        gameStateManager.getGameState().draw();
                    } else if(currentGameState instanceof MenuState && gameStateManager.getMenuState().isInGame()) {
                        gameStateManager.setState(GameStateManager.PLAY, false);
                        gameStateManager.getGameState().draw();
                    }
                break;
            case "enter":
                if (currentGameState instanceof MenuState) {
                    ((MenuState) gameStateManager.getGameState()).select();
                }
                break;
            case "up":
                if (currentGameState instanceof MenuState) {
                    ((MenuState) gameStateManager.getGameState()).moveUp();
                }
                break;
            case "down":
                if (currentGameState instanceof MenuState) {
                    ((MenuState) currentGameState).moveDown();
                }
                break;
            case "mouseMoved":
                if (view.getGameStateController().getGameState() instanceof PlayState) {
                    PlayState playState=(PlayState)view.getGameStateController().getGameState();
                    if (evt.getNewValue() instanceof Position) {
                        Position mouseScreenPos=(Position) evt.getNewValue();
                        Position mouseWorldPos=playState.screenToWorldCoordinates(mouseScreenPos);
                        model.getPlayer().calculateDirection(mouseWorldPos);
                    }
                }
            default:
                break;
        }
    }

}
