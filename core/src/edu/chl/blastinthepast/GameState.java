package edu.chl.blastinthepast;

import edu.chl.blastinthepast.view.GameStateManager;

/**
 * Created by Shif on 23/04/15.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}
