package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 21/05/15.
 */
public class MainMenuController extends GameStateController {

    private int currentItem;
    private String[] menuItems;

    public MainMenuController(BPController bpController, GameStateManager gsm) {
        super(bpController, gsm);
        currentItem = 0;
        menuItems = new String[]{"New game", "Load game", "Highscores", "Options", "Quit"};
        gsm.getMainMenu().setMenuItems(menuItems);
        gsm.getMainMenu().setCurrentItem(currentItem);
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ENTER:
                if(currentItem == 0) {
                    bpController.newGame();
                } else if (currentItem == 1) {
                    //gsm.setState(GameStateManager.SAVES);
                } else if (currentItem == 2) {
                    gsm.setState(GameStateManager.HIGHSCORES, false);
                    bpController.setActiveController(BPController.ActiveController.HIGHSCORE);
                    currentItem = 0;
                    gsm.getMainMenu().setCurrentItem(currentItem);
                } else if (currentItem == 3) {
                    //gsm.setState(GameStateManager.OPTIONS, false);
                } else if (currentItem == 4) {
                    Gdx.app.exit();
                }
                break;
            case Input.Keys.UP:
                moveUp();
                break;
            case Input.Keys.DOWN:
                moveDown();
                break;
        }
    }

    public void moveUp() {
        if (currentItem > 0) {
            currentItem--;
        } else {
            currentItem = menuItems.length-1;
        }
        gsm.getMainMenu().setCurrentItem(currentItem);
    }

    public void moveDown() {
        if(currentItem < menuItems.length-1) {
            currentItem++;
        } else {
            currentItem = 0;
        }
        gsm.getMainMenu().setCurrentItem(currentItem);
    }

}