package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.view.BPView;
import edu.chl.blastinthepast.view.PlayState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Shif on 20/04/15.
 */
public class BPController implements PropertyChangeListener {

    private BPModel model;
    private BPView view;
    private InputHandler inputHandler;

    private BPController(BPModel model, BPView view) {
        this.model = model;
        this.view = view;
        init();
    }

    private void init() {
        view.addListener(this);
        inputHandler = new InputHandler();
        inputHandler.addListener(this);
        Gdx.input.setInputProcessor(inputHandler);
    }

    public static BPController create(BPModel model, BPView view) {
        return new BPController(model, view);
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
