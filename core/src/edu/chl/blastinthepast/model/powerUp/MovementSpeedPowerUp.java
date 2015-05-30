package edu.chl.blastinthepast.model.powerUp;

/**
 * Created by Mattias on 15-04-23.
 */
public class MovementSpeedPowerUp extends PowerUp {
    private int bonus = 40;

    @Override
    public void applyPowerUp() {
        characterI.addBonusMovementSpeed(bonus);
    }

    @Override
    public PowerUpTypeEnum getPowerUpType() {
        return PowerUpTypeEnum.MOVEMENTSPEED;
    }

    @Override
    public boolean equals(Object obj){
        if (obj!=null){
            if (obj instanceof MovementSpeedPowerUp){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 17;
    }

}