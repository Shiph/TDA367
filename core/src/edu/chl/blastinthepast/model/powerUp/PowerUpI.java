package edu.chl.blastinthepast.model.powerUp;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface PowerUpI extends Collidable{

    public enum PowerUpType {
        DAMAGE, FIRERATE, HEALTH, MOVEMENTSPEED
    }

    void init(Character character);
    void update();
    void applyPowerUp();
    void setPosition(PositionInterface newPosition);
    PositionInterface getPosition();
    boolean getHasExpired();
    PowerUpType getPowerUpType();

}