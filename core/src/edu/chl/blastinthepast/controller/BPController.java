package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.view.BPView;
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
        switch(evt.getPropertyName()) {
            case "west":
                model.getPlayer().move("west", Gdx.graphics.getDeltaTime());
                break;
            case "east":
                model.getPlayer().move("east", Gdx.graphics.getDeltaTime());
                break;
            case "north":
                model.getPlayer().move("north", Gdx.graphics.getDeltaTime());
                break;
            case "south":
                model.getPlayer().move("south", Gdx.graphics.getDeltaTime());
                break;
            case "shoot":
                try {
                    //model.getPlayer().act("shoot", Gdx.graphics.getDeltaTime());
                    model.spawnProjectile();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage()); // player doesn't have a weapon or is out of bullets
                }
            case "enter":
                if (view.getGameStateController().getGameState() instanceof MenuState) {
                    ((MenuState) view.getGameStateController().getGameState()).select();
                }
                break;
            case "up":
                if (view.getGameStateController().getGameState() instanceof MenuState) {
                    ((MenuState) view.getGameStateController().getGameState()).moveUp();
                }
                break;
            case "down":
                if (view.getGameStateController().getGameState() instanceof MenuState) {
                    ((MenuState) view.getGameStateController().getGameState()).moveDown();
                }
                break;
            default:
                break;
        }
    }

}
