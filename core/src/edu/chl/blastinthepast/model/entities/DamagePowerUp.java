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
public class DamagePowerUp implements PowerUp {

    private PositionInterface position;
    int duration = 10*1000;

    @Override
    public void applyPowerUp(final Character character) {
        System.out.println("Maximum strength");
        character.getWeapon().setBonusDamage(character.getWeapon().getBonusDamage() + (int) Math.round(character.getWeapon().getProjectile().getDamage() * 0.5));
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
        System.out.println("Maximum strength deactivated");
        character.getWeapon().setBonusDamage(character.getWeapon().getBonusDamage() - (int)Math.round(character.getWeapon().getProjectile().getDamage()*0.5));
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