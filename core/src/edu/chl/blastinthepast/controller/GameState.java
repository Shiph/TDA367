package edu.chl.blastinthepast.controller;

import edu.chl.blastinthepast.controller.GameStateController;

/**
 * Created by Shif on 23/04/15.
 */
public abstract class GameState {

    protected GameStateController gsc;

    protected GameState(GameStateController gsc) {
        this.gsc = gsc;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}
