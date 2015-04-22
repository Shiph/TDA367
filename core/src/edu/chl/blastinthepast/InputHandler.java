package edu.chl.blastinthepast;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.beans.PropertyChangeSupport;

/**
 * Created by jonas on 2015-04-22.
 */
public class InputHandler implements InputProcessor{
    private boolean north, south, west, east, shoot, isMenuUp;
    private int northKey, southKey, westKey, eastKey, shootKey, weapon1Key, weapon2Key, menuKey;
    private Player player;

    private PropertyChangeSupport pcs;

    InputHandler(Player player){

        this.player=player;

        pcs=new PropertyChangeSupport(this);

        northKey= Input.Keys.W;
        southKey=Input.Keys.S;
        westKey=Input.Keys.A;
        eastKey=Input.Keys.D;
        shootKey=Input.Buttons.LEFT;
        weapon1Key=Input.Keys.NUM_1;
        weapon2Key=Input.Keys.NUM_2;
        menuKey=Input.Keys.ESCAPE;
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
        if (keycode==menuKey) {
            isMenuUp=!isMenuUp;
        }
        if (keycode==weapon1Key) {
            pcs.firePropertyChange("Weapon 1", null, true);
        }
        if (keycode==weapon2Key){
            pcs.firePropertyChange("Weapon 2", null, true);
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
            shoot=true;
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

    /**
     * Function fires property change
     */
    public void update(){
        if (north){
            pcs.firePropertyChange("North", null, true);
        } else if (south){
            pcs.firePropertyChange("South", null, true);
        }

        if (west) {
            pcs.firePropertyChange("West", null, true);
        } else if (east){
            pcs.firePropertyChange("East", null, true);
        }

        if (shoot){
            pcs.firePropertyChange("Shoot", null, true);
        }

        if (!isMenuUp){
            pcs.firePropertyChange("Menu", null, true);
        } else{
            pcs.firePropertyChange("Menu", null, false);
        }
    }

}
