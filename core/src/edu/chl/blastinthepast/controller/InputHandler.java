package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.InputProcessor;
import edu.chl.blastinthepast.utils.Position;

/**
 * Created by jonas on 2015-04-22.
 *
 * InputHandler listens to input by the user and fires PropertyChangeEvents when bound keys are pressed or are being held down
 * Movement and shoot keys have accompanying flags since it's necessary to check if they are being held down
 * The checkForInput needs to be called during each render cycle to check if keys are being held down continuously
 *
 */
public class InputHandler implements InputProcessor{
    protected boolean menuIsUp=false;
    private BPController bpController;

    public InputHandler(BPController bpController){
        this.bpController = bpController;
    }

    @Override
    public boolean keyDown(int keyCode) {
        bpController.keyDown(keyCode);
        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
        bpController.keyUp(keyCode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        bpController.touchDown();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        bpController.touchUp();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Position p=new Position(screenX, screenY);
        bpController.touchDragged(p);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        bpController.mouseMoved(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}