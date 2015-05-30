package edu.chl.blastinthepast.model.powerUp;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUpI extends Collidable{

    void init(CharacterI characterI);
    void update();
    void applyPowerUp();
    void setPosition(PositionInterface newPosition);
    PositionInterface getPosition();
    boolean getHasExpired();
    PowerUpTypeEnum getPowerUpType();

}
