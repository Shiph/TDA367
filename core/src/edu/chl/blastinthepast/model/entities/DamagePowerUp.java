package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.lang.*;

/**
 * Created by Mattias on 15-04-23.
 */
public class DamagePowerUp implements PowerUp {

    PositionInterface position;

    @Override
    public void applyPowerUp(Character character) {
        ProjectileInterface projectile = character.getWeapon().getProjectile();
        projectile.setDamage(projectile.getDamage()+100);
    }

    public void removePowerUp(Character character) {
        ProjectileInterface projectile = character.getWeapon().getProjectile();
        projectile.setDamage(projectile.getDamage()-100);
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