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
                view.updatePlayerPos(0);
                break;
            case "keyright":
                view.updatePlayerPos(1);
                break;
            case "keyup":
                view.updatePlayerPos(2);
                break;
            case "keydown":
                view.updatePlayerPos(3);
                break;
            case "shoot":
                try {
                    view.spawnProjectile(view.getPlayer().getWeapon().fire());
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage()); // player don't have a weapon or out of bullets
                }
                break;
            default:
                break;
        }
    }

}
