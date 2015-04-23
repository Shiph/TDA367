package edu.chl.blastinthepast;

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