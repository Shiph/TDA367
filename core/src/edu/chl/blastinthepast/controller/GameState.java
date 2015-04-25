package edu.chl.blastinthepast.controller;

import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.Player;
import edu.chl.blastinthepast.view.GameStateManager;

/**
 * Created by Shif on 23/04/15.
 */
public abstract class GameState {

    protected GameStateManager gsc;

    protected GameState(GameStateManager gsc, BPModel model) {
        this.gsc = gsc;
        init(model);
    }

    public abstract void init(BPModel model);
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}
