package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.model.Character;
import edu.chl.blastinthepast.model.PowerUp;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp implements PowerUp {

    @Override
    public void applyPowerUp(edu.chl.blastinthepast.model.Character character) {
        character.getWeapon().setFireRate(character.getWeapon().getFireRate()+50);
    }

    @Override
    public void removePowerUp(Character character) {
        character.getWeapon().setFireRate(character.getWeapon().getFireRate()-50);
    }
}