package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.Position;
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

    @Override
    public void applyPowerUp(final Character character) {
        System.out.println("Maximum fire rate");
        character.getWeapon().setFireRate(character.getWeapon().getFireRate() + (int) Math.round(character.getWeapon().getFireRate() * 0.25));
        ActionListener activate = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePowerUp(character);
            }
        };
        Timer powerUpTimer = new Timer(duration, activate);
        powerUpTimer.setRepeats(false);
        powerUpTimer.start();
    }

    @Override
    public void removePowerUp(Character character) {
        System.out.println("Maximum fire rate deactivated");
        character.getWeapon().setFireRate(character.getWeapon().getFireRate() - (int) Math.round(character.getWeapon().getFireRate() * 0.25));
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