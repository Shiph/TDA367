package edu.chl.blastinthepast.model.powerUp;

import edu.chl.blastinthepast.model.weapon.WeaponInterface;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class DamagePowerUp extends PowerUp{

    private int bonus=5;

    @Override
    public void applyPowerUp() {
        for (WeaponInterface w : character.getAllWeapons()) {
            w.addBonusDamage(bonus);
        }
    }

    @Override
    public boolean equals(Object obj){
        if (obj!=null){
            if (obj instanceof DamagePowerUp){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 19;
    }
}