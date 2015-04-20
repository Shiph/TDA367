package edu.chl.blastinthepast.controller;

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
            case "keyleft":
                view.updatePos(0);
                break;
            case "keyright":
                view.updatePos(1);
                break;
            case "keyup":
                view.updatePos(2);
                break;
            case "keydown":
                view.updatePos(3);
                break;
        }
    }

}
