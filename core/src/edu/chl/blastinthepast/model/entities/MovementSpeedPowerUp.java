package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.Position;
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

    public void applyPowerUp(final Character character) {
        System.out.println("Maximum speed");
        character.setMovementSpeed(character.getMovementSpeed() + (int) Math.round(character.getMovementSpeed() * 0.25));
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

    public void removePowerUp(Character character) {
        System.out.println("Maximum speed deactivated");
        character.setMovementSpeed(character.getMovementSpeed()-(int)Math.round(character.getMovementSpeed()*0.25));
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