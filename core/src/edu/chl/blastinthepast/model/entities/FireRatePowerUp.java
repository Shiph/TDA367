package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp extends PowerUpDecorator {

    private int bonusFireRate=30;

    @Override
    public void applyPowerUp() {
        for (WeaponInterface w : previousPlayer.getWeaponArray()){
            w.addBonusFireRate(bonusFireRate);
        }
        System.out.println("Fire rate bonus applied");
    }
}