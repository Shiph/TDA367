package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp implements PowerUp {

    PositionInterface position;

    @Override
    public void applyPowerUp(Character character) {
        character.getWeapon().setFireRate(character.getWeapon().getFireRate()+50);
    }

    @Override
    public void removePowerUp(Character character) {
        character.getWeapon().setFireRate(character.getWeapon().getFireRate()-50);
    }

    @Override
    public void setPosition(PositionInterface newPosition) {
        position = newPosition;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }
}