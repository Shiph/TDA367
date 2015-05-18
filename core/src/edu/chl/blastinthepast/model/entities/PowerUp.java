package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUp {

    /**
     * Applies the picked up power-up on the player character.
     * @param player
     */
    public void applyPowerUp(final Player player);

    public void removePowerUp(final Player player);

    public void setPosition(PositionInterface newPosition);

    public PositionInterface getPosition();

}
