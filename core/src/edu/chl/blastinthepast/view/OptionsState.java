package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.controller.GameStateManager;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.GameState;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class OptionsState extends GameState {

    public OptionsState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
        this.gsm = gsm;
        init(model);
    }

    public void init(BPModel model) {

    }

    public void draw() {

    }

    public void dispose() {

    }

    public void update(float dt) {

    }

    public void handleInput() {

    }
}
