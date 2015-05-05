package edu.chl.blastinthepast.controller;

import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.Player;
import edu.chl.blastinthepast.view.GameStateManager;

import java.util.Objects;

/**
 * Created by Shif on 23/04/15.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm, BPModel model) {
        this.gsm = gsm;
        init(model);
    }

    public abstract void init(BPModel model);
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}