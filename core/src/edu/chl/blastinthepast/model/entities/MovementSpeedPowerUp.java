package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public class MovementSpeedPowerUp implements PowerUp {

    PositionInterface position;

    public void applyPowerUp(edu.chl.blastinthepast.model.entities.Character character) {
        character.setMovementSpeed(character.getMovementSpeed()+100);
    }

    public void removePowerUp(Character character) {
        character.setMovementSpeed(character.getMovementSpeed()-100);
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