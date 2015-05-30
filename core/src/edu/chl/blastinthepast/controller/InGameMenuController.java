package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 22/05/15.
 */
public class InGameMenuController extends GameStateController {

    private int currentItem;
    private String[] menuItems;

    public InGameMenuController(BPController bpController, GameStateManager gsm) {
        super(bpController, gsm);
        currentItem = 0;
        menuItems = new String[]{"Continue", "Save game", "Options", "Exit to main menu"};
        gsm.getInGameMenu().setMenuItems(menuItems);
        gsm.getInGameMenu().setCurrentItem(currentItem);
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ESCAPE:
                gsm.setState(GameStateManager.PLAY, true);
                gsm.setState(GameStateManager.PLAY, true);
                currentItem = 0;
                gsm.getInGameMenu().setCurrentItem(currentItem);
                bpController.setActiveController(ActiveControllerEnum.PLAY);
                break;
            case Input.Keys.ENTER:
                if (currentItem == 0) { // continue
                    gsm.setState(GameStateManager.PLAY, true);
                    bpController.setActiveController(ActiveControllerEnum.PLAY);
                } else if (currentItem == 3) { // exit
                    currentItem = 0;
                    gsm.getInGameMenu().setCurrentItem(currentItem);
                    gsm.setState(GameStateManager.MAIN_MENU, false);
                    bpController.setActiveController(ActiveControllerEnum.MAIN_MENU);
                }
                break;
            case Input.Keys.UP:
                moveUp();
                break;
            case Input.Keys.DOWN:
                moveDown();
                break;
            case Input.Keys.SPACE:
                gsm.getInGameMenu().toggleSoundSprite();
                gsm.getPlayState().toggleSound();
                break;
        }
    }

    public void moveUp() {
        if (currentItem > 0) {
            currentItem--;
        } else {
            currentItem = menuItems.length-1;
        }
        gsm.getInGameMenu().setCurrentItem(currentItem);
    }

    public void moveDown() {
        if(currentItem < menuItems.length-1) {
            currentItem++;
        } else {
            currentItem = 0;
        }
        gsm.getInGameMenu().setCurrentItem(currentItem);
    }

}
