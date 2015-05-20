package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.model.Ammunition;

/**
 * Created by Mattias on 15-05-07.
 */
public class Boss extends Enemy {

    public Boss(Character player) {
        this(200, 15, player);
    }

    public Boss(int movementSpeed, int health, Character player) {
        super(movementSpeed, health, player);
    }

    @Override
    public void generateLoot() {
        Ammunition ammo = new Ammunition(getPosition(), getWeapon().getProjectile(), 100);
        getLoot().add(ammo);

        PowerUpI powerUp = PowerUpGenerator.generatePowerUp();
        powerUp.setPosition(getPosition());
        getLoot().add(powerUp);
    }

    @Override
    public String toString() {
        return "Boss";
    }

}