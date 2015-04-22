package edu.chl.blastinthepast.controller;

import edu.chl.blastinthepast.InputHandler;
import edu.chl.blastinthepast.model.GameModel;
import edu.chl.blastinthepast.view.BlastInThePast;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Shif on 20/04/15.
 */
public class GameController implements PropertyChangeListener {

    private GameModel model;
    private BlastInThePast view;

    private GameController(GameModel model, BlastInThePast view) {

        this.model = model;
        this.view = view;
        view.addListener(this);

    }

    public static GameController create(GameModel model, BlastInThePast view) {
        return new GameController(model, view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case "west":
                view.updatePlayerPos(0);
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
            default:
                break;
        }
    }

}
