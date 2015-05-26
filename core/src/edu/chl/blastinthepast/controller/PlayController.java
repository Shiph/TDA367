package edu.chl.blastinthepast.controller;

import com.badlogic.gdx.Input;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.view.gamestates.GameStateManager;
import edu.chl.blastinthepast.view.gamestates.PlayState;

/**
 * Created by Shif on 21/05/15.
 */
public class PlayController extends GameStateController {

    private BPModel model;

    public PlayController(BPController bpController, GameStateManager gsm, BPModel model) {
        super(bpController, gsm);
        this.model = model;
    }

    @Override
    public void keyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.A:
                model.getPlayer().setMovementDirection("west");
                break;
            case Input.Keys.D:
                model.getPlayer().setMovementDirection("east");
                break;
            case Input.Keys.W:
                model.getPlayer().setMovementDirection("north");
                break;
            case Input.Keys.S:
                model.getPlayer().setMovementDirection("south");
                break;
            case Input.Keys.E:
                if (!model.getChest().isOpened() && model.getPlayer().getPosition().overlaps(model.getChest().getPosition())) {
                    model.getPlayer().addWeapon(model.getChest().open(model.getPlayer()));
                    gsm.getPlayState().getPlayer().changeWeaponView();
                    gsm.getPlayState().updateGUIWeapon();
                }
                break;
            case Input.Keys.R:
                model.getPlayer().reloadCurrentWeapon();
                break;
            case Input.Keys.NUM_1:
                try {
                    model.getPlayer().setWeapon(model.getPlayer().getAllWeapons().get(0));
                    ((PlayState) gsm.getGameState()).getPlayer().changeWeaponView();
                    ((PlayState) gsm.getGameState()).updateGUIWeapon();
                } catch (IndexOutOfBoundsException e) {}
                break;
            case Input.Keys.NUM_2:
                try {
                    model.getPlayer().setWeapon(model.getPlayer().getAllWeapons().get(1));
                    ((PlayState) gsm.getGameState()).getPlayer().changeWeaponView();
                    ((PlayState) gsm.getGameState()).updateGUIWeapon();
                } catch (IndexOutOfBoundsException e) {}
                break;
            case Input.Keys.SPACE:
                gsm.getPlayState().toggleSound();
                gsm.getInGameMenu().toggleSoundSprite();
                break;
            case Input.Keys.ESCAPE:
                gsm.setState(GameStateManager.IN_GAME_MENU, true);
                model.getPlayer().resetMovementDirection();
                bpController.setActiveController(BPController.ActiveController.INGAME_MENU);
                break;
        }
    }

    public void touchDown() {
        model.getPlayer().isShooting(true);
    }

    public void touchUp() {
        model.getPlayer().isShooting(false);
    }

    public void touchDragged(Position p) {
        Position mouseWorldPos = gsm.getPlayState().screenToWorldCoordinates(p);
        model.getPlayer().calculateDirection(mouseWorldPos);
    }

    public void mouseMoved(int screenX, int screenY) {
        Position mouseScreenPos = new Position(screenX, screenY);
        Position mouseWorldPos = gsm.getPlayState().screenToWorldCoordinates(mouseScreenPos);
        model.getPlayer().calculateDirection(mouseWorldPos);
    }

    public void keyUp(int keyCode) {
        if (keyCode == Input.Keys.W || keyCode == Input.Keys.A || keyCode == Input.Keys.S || keyCode == Input.Keys.D) {
            keyDown(keyCode);
        }
    }

}