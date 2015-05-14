package edu.chl.blastinthepast.model.entities;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp implements PowerUp {

    @Override
    public void applyPowerUp(Character character) {
        character.getWeapon().setFireRate(character.getWeapon().getFireRate()+50);
    }

    @Override
    public void removePowerUp(Character character) {
        character.getWeapon().setFireRate(character.getWeapon().getFireRate()-50);
    }
}