package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.view.BPView;
import edu.chl.blastinthepast.view.GameStateManager;
import edu.chl.blastinthepast.view.MenuState;
import edu.chl.blastinthepast.view.PlayState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
                if(currentGameState instanceof PlayState) {
                    model.getPlayer().move("west", Gdx.graphics.getDeltaTime());
                }
                break;
            case "east":
                if(currentGameState instanceof PlayState) {
                    model.getPlayer().move("east", Gdx.graphics.getDeltaTime());
                }
                break;
            case "north":
                if(currentGameState instanceof PlayState) {
                    model.getPlayer().move("north", Gdx.graphics.getDeltaTime());
                }
                break;
            case "south":
                if(currentGameState instanceof PlayState) {
                    model.getPlayer().move("south", Gdx.graphics.getDeltaTime());
                }
                break;
            case "shoot":
                if(currentGameState instanceof PlayState) {
                    try {
                        //model.getPlayer().act("shoot", Gdx.graphics.getDeltaTime());
                        model.spawnProjectile();
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
            default:
                break;
        }
    }

}
