package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.powerUp.PowerUp;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by jonas on 2015-05-21.
 */
public class PowerUpFactory {

    public PowerUpView getPowerUpView(PowerUp powerUp){
        if (powerUp==null){
            return null;
        }
        switch (powerUp.toString()){
            case "HealthPowerUp":
                return new PowerUpView(powerUp, GraphicalAssets.BOSSUP);
            case "FireRatePowerUp":
                return new PowerUpView(powerUp, GraphicalAssets.CHESTOPEN);
            case "DamagePowerUp":
                return new PowerUpView(powerUp, GraphicalAssets.AK47);
            case "MovementSpeedPowerUp":
                return new PowerUpView(powerUp, GraphicalAssets.MAGNUM);
        }
        return null;
    }
}
