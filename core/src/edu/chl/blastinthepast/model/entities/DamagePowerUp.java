package edu.chl.blastinthepast.model.entities;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class DamagePowerUp extends PowerUpDecorator {

    private int bonusDamage=100;

    @Override
    public void applyPowerUp() {
        for (WeaponInterface w : previousPlayer.getWeaponArray()){
            w.addBonusDamage(bonusDamage);
        }
        System.out.println("Bonus damage applied");
    }
}
