package edu.chl.blastinthepast.model.powerUp;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 *
 * An interface for power-ups. Since power-ups are only supposed to activate once the character has picked it up, the power-up
 * has an init method which should be called when the power-up should activate.
 */
public interface PowerUpI extends Collidable{


    void init(CharacterI characterI);

    /**
     * Checks if the power-up is still active and if so, applies it to the character.
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
