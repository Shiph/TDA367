package edu.chl.blastinthepast.model.powerUp;

import edu.chl.blastinthepast.model.weapon.WeaponInterface;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp extends PowerUp {

    private int bonus = 2;

    @Override
    public void applyPowerUp() {
        for (WeaponInterface w : characterI.getAllWeapons()){
            w.addBonusFireRate(bonus);
        }
    }

    @Override
    public PowerUpTypeEnum getPowerUpType() {
        return PowerUpTypeEnum.FIRERATE;
    }

    @Override
    public boolean equals(Object obj){
        if (obj != null){
            if (obj instanceof FireRatePowerUp){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 13;
    }

}