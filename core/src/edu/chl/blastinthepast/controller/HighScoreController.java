package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.view.gamestates.GameState;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 22/05/15.
 */
public class HighScoreController extends GameStateController {

    public HighScoreController(BPController bpController, GameStateManager gsm) {
        super(bpController, gsm);
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ESCAPE:
                gsm.setState(GameStateManager.MAIN_MENU, false);
                bpController.setActiveController(ActiveControllerEnum.MAIN_MENU);
                break;
        }
    }
}
