package edu.chl.blastinthepast.controller;

import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 22/05/15.
 */
public abstract class GameStateController implements ControllerInterface {

    protected BPController bpController;
    protected GameStateManager gsm;

    public GameStateController(BPController bpController, GameStateManager gsm) {
        this.bpController = bpController;
        this.gsm = gsm;
    }

}
