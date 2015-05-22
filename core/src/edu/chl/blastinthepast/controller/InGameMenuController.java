package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.model.menu.InGameMenuModel;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 22/05/15.
 */
public class InGameMenuController extends GameStateController {

    private InGameMenuModel inGameMenuModel;

    public InGameMenuController(BPController bpController, GameStateManager gsm, InGameMenuModel inGameMenuModel) {
        super(bpController, gsm);
        this.inGameMenuModel = inGameMenuModel;
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ESCAPE:
                gsm.setState(GameStateManager.PLAY, true);
                gsm.setState(GameStateManager.PLAY, true);
                inGameMenuModel.resetCurrentItem();
                bpController.setActiveController(BPController.ActiveController.PLAY);
                break;
            case Input.Keys.ENTER:
                if (inGameMenuModel.getCurrentItem() == 0) { // continue
                    gsm.setState(GameStateManager.PLAY, true);
                    bpController.setActiveController(BPController.ActiveController.PLAY);
                } else if (inGameMenuModel.getCurrentItem() == 3) { // exit
                    inGameMenuModel.resetCurrentItem();
                    gsm.setState(GameStateManager.MAIN_MENU, false);
                    bpController.setActiveController(BPController.ActiveController.MAIN_MENU);
                }
                break;
            case Input.Keys.UP:
                inGameMenuModel.moveUp();
                break;
            case Input.Keys.DOWN:
                inGameMenuModel.moveDown();
                break;
            case Input.Keys.SPACE:
                gsm.getInGameMenu().toggleSoundSprite();
                gsm.getPlayState().toggleSound();
                break;
        }
    }

}
