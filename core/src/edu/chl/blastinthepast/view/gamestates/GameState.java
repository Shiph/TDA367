package edu.chl.blastinthepast.view.gamestates;

import edu.chl.blastinthepast.model.BPModel;



/**
 * Created by Shif on 23/04/15.
 */
public abstract class GameState{

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
