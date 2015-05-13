package edu.chl.blastinthepast.model.entities;

/**
 * Created by Mattias on 15-04-23.
 */
public class MovementSpeedPowerUp implements PowerUp {

    public void applyPowerUp(edu.chl.blastinthepast.model.entities.Character character) {
        character.setMovementSpeed(character.getMovementSpeed()+100);
    }

    public void removePowerUp(Character character) {
        character.setMovementSpeed(character.getMovementSpeed()-100);
    }

}