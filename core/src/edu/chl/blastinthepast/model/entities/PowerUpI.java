package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUpI {

    void init(Character character);

    void update();

    void applyPowerUp();

    void setPosition(PositionInterface newPosition);

    PositionInterface getPosition();

    boolean getHasExpired();

}
