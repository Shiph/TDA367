package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.player.*;
import edu.chl.blastinthepast.model.powerUp.PowerUp;
import edu.chl.blastinthepast.model.powerUp.PowerUpType;
import edu.chl.blastinthepast.model.powerUp.PowerUpTypeEnum;

/**
 * Created by jonas on 2015-05-30.
 */
public class MockPowerUp extends PowerUp{

    int powerUpApplications = 0;

    @Override
    public void applyPowerUp() {
     powerUpApplications++;
    }

    @Override
    public PowerUpTypeEnum getPowerUpType() {
        return null;
    }

    public void setDuration(int newDuration){
        duration = newDuration;
    }

    public CharacterI getCharacter(){
        return characterI;
    }
}
