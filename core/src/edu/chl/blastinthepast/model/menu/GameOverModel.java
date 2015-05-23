package edu.chl.blastinthepast.model.menu;

import edu.chl.blastinthepast.utils.HighScoreHandler;

/**
 * Created by Shif on 22/05/15.
 */
public class GameOverModel {

    private char[] newName = new char[] {'A', 'A', 'A'};
    private int currentChar = 0;
    private boolean isNewHighScore;
    private int score;

    public void setScore(int score) {
        this.score = score;
        isNewHighScore = HighScoreHandler.gameData.isHighScore(score);
    }

    public boolean isNewHighScore() {
        return isNewHighScore;
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
    }

    public void moveLeft() {
        if(currentChar > 0) {
            currentChar--;
        }
    }

    public void moveRight() {
        if(currentChar < newName.length - 1) {
            currentChar++;
        }
    }

    public int getCurrentChar() {
        return currentChar;
    }

    public char[] getNewName() {
        return newName;
    }

    public int getScore() {
        return score;
    }

    public void saveScore() {
        HighScoreHandler.gameData.addHighScore(score, new String(newName));
        HighScoreHandler.save();
    }
}
