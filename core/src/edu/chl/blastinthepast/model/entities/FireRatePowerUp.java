package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class FireRatePowerUp implements PowerUp {

    private PositionInterface position;
    private int duration=10*1000; //Duration in milliseconds
    private int bonus=20;

    @Override
    public void applyPowerUp(final Player player) {
        System.out.println("Maximum fire rate");
        for (WeaponInterface w : player.getWeaponArray()){
            w.setBonusFireRate(w.getFireRate()+bonus);
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
            w.setBonusDamage(w.getBonusDamage()-bonus);
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
}