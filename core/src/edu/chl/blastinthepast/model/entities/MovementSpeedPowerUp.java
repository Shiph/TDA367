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
    private int speedIncrease=100;

    public void applyPowerUp(final Player player) {
        System.out.println("Maximum speed");
        player.setMovementSpeed(player.getMovementSpeed() + speedIncrease);
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
        player.setMovementSpeed(player.getMovementSpeed()-speedIncrease);
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


}