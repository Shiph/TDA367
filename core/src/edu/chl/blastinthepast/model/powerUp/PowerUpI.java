package edu.chl.blastinthepast.model.powerUp;

import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUpI {

    void init(edu.chl.blastinthepast.model.player.Character character);

    void update();

    void applyPowerUp();

    void setPosition(PositionInterface newPosition);

    PositionInterface getPosition();

    boolean getHasExpired();

}
