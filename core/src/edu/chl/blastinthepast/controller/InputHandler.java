package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.blastinthepast.utils.Position;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by jonas on 2015-04-22.
 *
 * InputHandler listens to input by the user and fires PropertyChangeEvents when bound keys are pressed or are being held down
 * Movement and shoot keys have accompanying flags since it's necessary to check if they are being held down
 * The checkForInput needs to be called during each render cycle to check if keys are being held down continuously
 *
 */
public class InputHandler implements InputProcessor{
    private int spaceKey, northKey, southKey, westKey, eastKey, shootKey, enterKey, reloadKey, weapon1Key, weapon2Key, menuKey, upKey, downKey, leftKey, rightKey, escapeKey, useKey;
    protected boolean north=false, south=false, west=false, east=false, shoot=false, menuIsUp=false;
    private PropertyChangeSupport pcs;

    public InputHandler(){
        pcs=new PropertyChangeSupport(this);
        escapeKey = Input.Keys.ESCAPE;
        northKey= Input.Keys.W;
        southKey=Input.Keys.S;
        westKey=Input.Keys.A;
        eastKey=Input.Keys.D;
        useKey=Input.Keys.E;
        shootKey=Input.Buttons.LEFT;
        enterKey = Input.Keys.ENTER;
        reloadKey=Input.Keys.R;
        weapon1Key=Input.Keys.NUM_1;
        weapon2Key=Input.Keys.NUM_2;
        menuKey=Input.Keys.ESCAPE;
        upKey = Input.Keys.UP;
        downKey = Input.Keys.DOWN;
        leftKey = Input.Keys.LEFT;
        rightKey = Input.Keys.RIGHT;
        spaceKey = Input.Keys.SPACE;

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode==northKey){
            north=true;
            south=false;
        }
        if (keycode==southKey){
            south=true;
            north=false;
        }
        if (keycode==westKey){
            west=true;
            east=false;
        }
        if (keycode==eastKey){
            east=true;
            west=false;
        }
        if (keycode==shootKey){
            shoot=true;
        }
        if (keycode==enterKey){
            pcs.firePropertyChange("enter", null, true);
        }
        if (keycode==upKey){
            pcs.firePropertyChange("up", null, true);
        }
        if (keycode==downKey){
            pcs.firePropertyChange("down", null, true);
        }
        if (keycode==leftKey){
            pcs.firePropertyChange("left", null, true);
        }
        if (keycode==rightKey){
            pcs.firePropertyChange("right", null, true);
        }
        if (keycode==reloadKey){
            pcs.firePropertyChange("reload", null, true);
        }
        if (keycode==menuKey) {
            pcs.firePropertyChange("menu", null, !menuIsUp);
            menuIsUp=!menuIsUp;
        }
        if (keycode==weapon1Key) {
            pcs.firePropertyChange("weapon1", null, true);
        }
        if (keycode==weapon2Key){
            pcs.firePropertyChange("weapon2", null, true);
        }
        if (keycode==escapeKey){
            pcs.firePropertyChange("escape", null, true);
        }
        if (keycode==escapeKey){
            pcs.firePropertyChange("use", null, true);
        }
        if (keycode == spaceKey) {
            pcs.firePropertyChange("space", null, true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode==northKey){
            north=false;
        }
        if (keycode==southKey){
            south=false;
        }
        if (keycode==westKey){
            west=false;
        }
        if (keycode==eastKey){
            east=false;
        }
        if (keycode==shootKey){
            shoot=false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button==shootKey){
            shoot=true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button==shootKey){
            shoot=false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        Position p=new Position(screenX, screenY);
        pcs.firePropertyChange("mouseMoved", null, p);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Position p=new Position(screenX, screenY);
        pcs.firePropertyChange("mouseMoved", null, p);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void addListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    /**
     * Checks if movement and shoot keys are held down continuously
     * Should be called once during every render cycle
     */
    public void checkForInput() {
        if (north) {
            pcs.firePropertyChange("north", null, true);
        } else if (south) {
            pcs.firePropertyChange("south", null, true);
        }
        if (west) {
            pcs.firePropertyChange("west", null, true);
        } else if (east) {
            pcs.firePropertyChange("east", null, true);
        }
        if (shoot) {
            pcs.firePropertyChange("shoot", null, true);
        }
    }

}