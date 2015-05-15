package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.model.entities.Character;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUp {

    /**
     * Applies the picked up power-up on the player character.
     * @param character
     */
    public void applyPowerUp(final Character character);

    public void removePowerUp(final Character character);

    public void setPosition(PositionInterface newPosition);

    public PositionInterface getPosition();

}
