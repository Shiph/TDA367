package edu.chl.blastinthepast.view.gamestates;

import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.level.LevelInterface;

/**
 * Created by Shif on 23/04/15.
 */
public abstract class GameState{

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm, BPModel model, LevelInterface level) {
        this.gsm = gsm;
    }

    protected GameState(GameStateManager gsm, BPModel model) {
        this.gsm = gsm;
        init(model);
    }

    public abstract void init(BPModel model);
    public abstract void init(BPModel model, LevelInterface level);
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void dispose();

}
