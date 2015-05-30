package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.utils.HighScoreHandler;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;

/**
 * Created by Shif on 22/05/15.
 */
public class GameOverController extends GameStateController {

    private char[] newName = new char[] {'A', 'A', 'A'};
    private int currentChar = 0;
    private boolean newHighScore;
    private int score;

    public GameOverController(BPController bpController, GameStateManager gsm) {
        super(bpController, gsm);
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.ENTER:
                if(newHighScore) {
                    saveScore();
                }
                gsm.setState(GameStateManager.MAIN_MENU, false);
                bpController.setActiveController(ActiveControllerEnum.MAIN_MENU);
                break;
            case Input.Keys.UP:
                moveUp();
                break;
            case Input.Keys.DOWN:
                moveDown();
                break;
            case Input.Keys.LEFT:
                moveLeft();
                break;
            case Input.Keys.RIGHT:
                moveRight();
        }
    }

    public void moveUp() {
        if(newName[currentChar] == ' ') {
            newName[currentChar] = 'A';
        } else {
            newName[currentChar] ++;
            if(newName[currentChar] > 'Z') {
                newName[currentChar] = ' ';
            }
        }
        gsm.getGameOverState().setNewName(newName);
    }

    public void moveDown() {
        if(newName[currentChar] == ' ') {
            newName[currentChar] = 'Z';
        } else {
            newName[currentChar] --;
            if(newName[currentChar] < 'A') {
                newName[currentChar] = ' ';
            }
        }
        gsm.getGameOverState().setNewName(newName);
    }

    public void moveLeft() {
        if(currentChar > 0) {
            currentChar--;
            gsm.getGameOverState().setCurrentChar(currentChar);
        }
    }

    public void moveRight() {
        if(currentChar < newName.length - 1) {
            currentChar++;
            gsm.getGameOverState().setCurrentChar(currentChar);
        }
    }

    public void saveScore() {
        HighScoreHandler.gameData.addHighScore(score, new String(newName));
        HighScoreHandler.save();
    }

    public void setScore(int score) {
        this.score = score;
        newHighScore = HighScoreHandler.gameData.isHighScore(score);
        gsm.getGameOverState().setScore(score);
        gsm.getGameOverState().setIsNewHighScore(newHighScore);
        gsm.getGameOverState().setNewName(newName);
    }
}