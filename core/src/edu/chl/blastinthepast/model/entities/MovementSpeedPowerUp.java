package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mattias on 15-04-23.
 */
public class MovementSpeedPowerUp extends PowerUpDecorator {

    private int bonusMovementSpeed=200;

    @Override
    public void applyPowerUp() {
        previousPlayer.addBonusMovementSpeed(bonusMovementSpeed);
        System.out.println("Movement speed bonus applied");
    }
}