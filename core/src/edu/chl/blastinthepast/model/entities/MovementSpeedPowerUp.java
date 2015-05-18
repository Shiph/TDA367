package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mattias on 15-04-23.
 */
public class MovementSpeedPowerUp implements PowerUp {

    private PositionInterface position;
    private int duration = 10*1000;
    private int bonus=25;

    public void applyPowerUp(final Player player) {
        System.out.println("Maximum speed");
        player.setBonusMovementSpeed(player.getBonusMovementSpeed() + bonus);
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

    public void removePowerUp(Player player) {
        System.out.println("Maximum speed deactivated");
        player.setBonusMovementSpeed(player.getBonusMovementSpeed() - bonus);
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