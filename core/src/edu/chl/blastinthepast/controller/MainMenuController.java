package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.model.menu.MainMenuModel;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 21/05/15.
 */
public class MainMenuController extends GameStateController {

    private MainMenuModel mainMenuModel;

    public MainMenuController(BPController bpController, GameStateManager gsm, MainMenuModel mainMenuModel) {
        super(bpController, gsm);
        this.mainMenuModel = mainMenuModel;
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ENTER:
                int currentItem = mainMenuModel.getCurrentItem();
                if(currentItem == 0) {
                    bpController.newGame();
                } else if (currentItem == 1) {
                    //gsm.setState(GameStateManager.SAVES);
                } else if (currentItem == 2) {
                    gsm.setState(GameStateManager.HIGHSCORES, false);
                    bpController.setActiveController(BPController.ActiveController.HIGHSCORE);
                    mainMenuModel.resetCurrentItem();
                } else if (currentItem == 3) {
                    //gsm.setState(GameStateManager.OPTIONS, false);
                } else if (currentItem == 4) {
                    Gdx.app.exit();
                }
                break;
            case Input.Keys.UP:
                mainMenuModel.moveUp();
                break;
            case Input.Keys.DOWN:
                mainMenuModel.moveDown();
                break;
        }
    }

}
