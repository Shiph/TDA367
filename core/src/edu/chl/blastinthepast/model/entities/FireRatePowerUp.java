package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp implements PowerUp, WeaponPowerUp {

    private PositionInterface position;
    private int duration=10*1000; //Duration in milliseconds
    private int fireRateBonus=2;

    @Override
    public void applyPowerUp(final Player player) {
        System.out.println("Maximum fire rate");
        for (WeaponInterface w : player.getWeaponArray()){
            w.setFireRate(player.getCurrentWeapon().getFireRate() + fireRateBonus);
        }
        ActionListener activate = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePowerUp(player);
            }
        };
        Timer powerUpTimer = new Timer(duration, activate);
        powerUpTimer.setRepeats(false);
        powerUpTimer.start();
    }

    @Override
    public void removePowerUp(Player player) {
        System.out.println("Maximum fire rate deactivated");
        for (WeaponInterface w : player.getWeaponArray()){
            System.out.println(w+" fire rate : "+w.getFireRate());
            w.setFireRate(player.getCurrentWeapon().getFireRate() - fireRateBonus);
        }
    }

    @Override
    public void setPosition(PositionInterface newPosition) {
        position = newPosition;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public void setDuration(int newDuration) {
        duration=newDuration;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void applyPowerUpToWeapon(WeaponInterface weapon, int powerUpStrength) {
        weapon.setFireRate(weapon.getFireRate()+fireRateBonus*powerUpStrength);
        System.out.println(weapon);
    }
}