package edu.chl.blastinthepast.model.entities;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp extends PowerUp {

    private int bonus=2;

    @Override
    public void applyPowerUp() {
        System.out.println("Maximum fire rate");
        for (WeaponInterface w : character.getWeaponArray()){
            w.addBonusFireRate(bonus);
        }
    }

    @Override
    public boolean equals(Object obj){
        if (obj!=null){
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