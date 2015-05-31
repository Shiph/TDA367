package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import edu.chl.blastinthepast.view.assets.GraphicalAssets;

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
                return new PowerUpView(powerUp, GraphicalAssets.HEART);
            case FIRERATE:
                return new PowerUpView(powerUp, GraphicalAssets.FIRERATE_UP);
            case DAMAGE:
                return new PowerUpView(powerUp, GraphicalAssets.DMG_UP);
            case MOVEMENTSPEED:
                return new PowerUpView(powerUp, GraphicalAssets.BOOT);
        }
        return null;
    }
}
