package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

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
    private int northKey, southKey, westKey, eastKey, shootKey, reloadKey, weapon1Key, weapon2Key, menuKey;
    protected boolean north=false, south=false, west=false, east=false, shoot=false, menuIsUp=false;
    private PropertyChangeSupport pcs;

    public InputHandler(){
        pcs=new PropertyChangeSupport(this);
        System.out.println("new input handler created");
        northKey= Input.Keys.W;
        southKey=Input.Keys.S;
        westKey=Input.Keys.A;
        eastKey=Input.Keys.D;
        shootKey=Input.Buttons.LEFT;
        reloadKey=Input.Keys.R;
        weapon1Key=Input.Keys.NUM_1;
        weapon2Key=Input.Keys.NUM_2;
        menuKey=Input.Keys.ESCAPE;
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("inside keyDown");
        if (keycode==northKey){
            System.out.println("northKey pressed");
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (button==shootKey){
            shoot=true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (button==shootKey){
            shoot=false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
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
    public void checkForInput(){
        if (north){
            pcs.firePropertyChange("north", null, true);
        } else if (south){
            pcs.firePropertyChange("south", null, true);
        }
        if (west){
            pcs.firePropertyChange("west", null, true);
        } else if (east){
            pcs.firePropertyChange("east", null, true);
        }
        if (shoot){
            pcs.firePropertyChange("shoot", null, true);
        }
    }


}
