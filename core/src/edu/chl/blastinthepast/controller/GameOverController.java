package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.model.menu.GameOverModel;
import edu.chl.blastinthepast.utils.HighScoreHandler;
import edu.chl.blastinthepast.view.gamestates.GameOverState;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 22/05/15.
 */
public class GameOverController extends GameStateController {

    private GameOverModel gameOverModel;

    public GameOverController(BPController bpController, GameStateManager gsm, GameOverModel gameOverModel) {
        super(bpController, gsm);
        this.gameOverModel = gameOverModel;
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ENTER:
                if(gameOverModel.isNewHighScore()) {
                    gameOverModel.saveScore();
                }
                gsm.setState(GameStateManager.MAIN_MENU, false);
                bpController.setActiveController(BPController.ActiveController.MAIN_MENU);
                break;
            case Input.Keys.UP:
                gameOverModel.moveUp();
                break;
            case Input.Keys.DOWN:
                gameOverModel.moveDown();
                break;
            case Input.Keys.LEFT:
                gameOverModel.moveLeft();
                break;
            case Input.Keys.RIGHT:
                gameOverModel.moveRight();
        }
    }
}