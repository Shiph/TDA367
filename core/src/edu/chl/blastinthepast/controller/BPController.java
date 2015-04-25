package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
        switch(evt.getPropertyName()) {
            case "west":
                ((PlayState)view.getGameStateController().getGameState()).getPlayer().move("west", Gdx.graphics.getDeltaTime());
                break;
            case "east":
                ((PlayState)view.getGameStateController().getGameState()).getPlayer().move("east", Gdx.graphics.getDeltaTime());
                break;
            case "north":
                try {
                    ((PlayState) view.getGameStateController().getGameState()).getPlayer().move("north", Gdx.graphics.getDeltaTime());
                } catch(ClassCastException c) {} // Clicking in menu screen.
                break;
            case "south":
                try {
                    ((PlayState) view.getGameStateController().getGameState()).getPlayer().move("south", Gdx.graphics.getDeltaTime());
                } catch (ClassCastException c) {} // Clicking in menu screen.
                break;
            case "shoot":
                try {
                    ((PlayState)view.getGameStateController().getGameState()).spawnProjectile();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage()); // player doesn't have a weapon or is out of bullets
                } catch (ClassCastException c) {} // Clicking in menu screen.
                break;
            default:
                break;
        }
    }

}
