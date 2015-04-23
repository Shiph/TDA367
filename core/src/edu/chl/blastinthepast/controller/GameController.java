package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import edu.chl.blastinthepast.model.GameModel;
import edu.chl.blastinthepast.view.BlastInThePast;
import edu.chl.blastinthepast.view.InputHandler;
import edu.chl.blastinthepast.view.PlayState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Shif on 20/04/15.
 */
public class GameController implements PropertyChangeListener {

    private GameModel model;
    private BlastInThePast view;
    private InputHandler inputHandler;

    private GameController(GameModel model, BlastInThePast view) {
        this.model = model;
        this.view = view;
        init();
    }

    private void init() {
        view.addListener(this);
        inputHandler = new InputHandler();
        Gdx.input.setInputProcessor(inputHandler);
        inputHandler.addListener(this);
    }

    public static GameController create(GameModel model, BlastInThePast view) {
        return new GameController(model, view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case "west":
                ((PlayState)view.getGameStateController().getGameState()).getPlayer().move("west", Gdx.graphics.getDeltaTime());
                break;
            case "east":
                view.updatePlayerPos(1);
                break;
            case "north":
                view.updatePlayerPos(2);
                break;
            case "south":
                view.updatePlayerPos(3);
                break;
            case "shoot":
                try {
                    view.spawnProjectile();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage()); // player doesn't have a weapon or is out of bullets
                }
                break;
            case "update":
                update();
                break;
            default:
                break;
        }
    }

    private void update() {
        inputHandler.checkForInput();
    }

}
