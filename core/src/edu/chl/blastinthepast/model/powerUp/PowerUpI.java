package edu.chl.blastinthepast.model.powerUp;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUpI extends Collidable{

    void init(CharacterI characterI);

    /**
     * Checks if the power-up is still active.
     */
    void update();

    void applyPowerUp();

    void setPosition(PositionInterface newPosition);

    PositionInterface getPosition();

    boolean getHasExpired();

    /**
     * Returns an enum value representing the power-up type.
     * @return
     */
    PowerUpTypeEnum getPowerUpType();

}
