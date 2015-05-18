package edu.chl.blastinthepast.model.entities;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jonas on 2015-05-15.
 */
public class PowerUpGenerator {

    public static PowerUpDecorator generatePowerUp(){
        Random random = new Random();
        ArrayList<PowerUpDecorator> powerUps = new ArrayList<PowerUpDecorator>();

        powerUps.add(new DamagePowerUp());
        powerUps.add(new MovementSpeedPowerUp());
        powerUps.add(new FireRatePowerUp());

        return powerUps.get(random.nextInt(powerUps.size()));
    }
}
