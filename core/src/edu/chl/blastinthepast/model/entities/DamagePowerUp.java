package edu.chl.blastinthepast.model.entities;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class DamagePowerUp implements PowerUp {

    @Override
    public void applyPowerUp(Character character) {
        ProjectileInterface projectile = character.getWeapon().getProjectile();
        projectile.setDamage(projectile.getDamage()+100);
    }

    public void removePowerUp(Character character) {
        ProjectileInterface projectile = character.getWeapon().getProjectile();
        projectile.setDamage(projectile.getDamage()-100);
    }

}