package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.model.Ammunition;

import java.util.Random;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class Pleb extends Enemy {

    public Pleb (Character player) {
        this(150, 5, player);
    }

    public Pleb(int movementSpeed, int health, Character player) {
        super(movementSpeed, health, player);
    }

    @Override
    public void generateLoot() {
        Random random = new Random();
        boolean hasAmmo = random.nextBoolean();
        if (hasAmmo) {
            int amount = random.nextInt(4)*10+20;
            Ammunition ammo = new Ammunition(getPosition(), getWeapon().getProjectile(), amount);
            getLoot().add(ammo);
        }
        boolean hasPowerUp = random.nextBoolean();
        if (hasPowerUp) {
            PowerUpI powerUp = PowerUpGenerator.generatePowerUp();
            powerUp.setPosition(getPosition());
            getLoot().add(powerUp);
        }
    }

    @Override
    public String toString() {
        return "Pleb";
    }
}
