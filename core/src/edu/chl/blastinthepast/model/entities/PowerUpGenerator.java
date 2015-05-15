package edu.chl.blastinthepast.model.entities;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jonas on 2015-05-15.
 */
public class PowerUpGenerator {

    public static PowerUp generatePowerUp(){
        Random random = new Random();
        ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();

        powerUps.add(new DamagePowerUp());
        powerUps.add(new MovementSpeedPowerUp());
        powerUps.add(new FireRatePowerUp());

        return powerUps.get(random.nextInt(powerUps.size()));
    }
}
