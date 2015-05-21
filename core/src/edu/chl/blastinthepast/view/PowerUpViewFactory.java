package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.powerUp.PowerUp;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by jonas on 2015-05-21.
 */
public class PowerUpViewFactory {

    public PowerUpView getPowerUpView(PowerUpI powerUp) {
        if (powerUp == null){
            return null;
        }
        switch (powerUp.getPowerUpType()){
            case HEALTH:
                return new PowerUpView(powerUp, GraphicalAssets.BOSSUP);
            case FIRERATE:
                return new PowerUpView(powerUp, GraphicalAssets.CHESTOPEN);
            case DAMAGE:
                return new PowerUpView(powerUp, GraphicalAssets.AK47);
            case MOVEMENTSPEED:
                return new PowerUpView(powerUp, GraphicalAssets.MAGNUM);
        }
        return null;
    }
}
