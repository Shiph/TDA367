package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.model.entities.Character;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUp {

    /**
     * Applies the picked up power-up on the player character.
     * @param character
     */
    public void applyPowerUp(Character character);

    public void removePowerUp(Character character);

}
