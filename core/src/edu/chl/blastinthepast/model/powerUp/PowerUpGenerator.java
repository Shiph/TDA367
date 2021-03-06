package edu.chl.blastinthepast.model.powerUp;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jonas on 2015-05-15.
 */
public class PowerUpGenerator {

    public static PowerUpI generatePowerUp(){
        Random random = new Random();
        ArrayList<PowerUpI> powerUps = new ArrayList<PowerUpI>();

        powerUps.add(new DamagePowerUp());
        powerUps.add(new MovementSpeedPowerUp());
        powerUps.add(new FireRatePowerUp());
        powerUps.add(new HealthPowerUp());

        return powerUps.get(random.nextInt(powerUps.size()));
    }
}
