package edu.chl.blastinthepast.model.enemy;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.ammunition.Ammunition;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.powerUp.PowerUpGenerator;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class Pleb extends Enemy {

    public Pleb (Character player) {
        this(150, 5, player);
    }

    private Pleb(int movementSpeed, int health, Character player) {
        super(movementSpeed, health, player, 64, 64);
    }

    @Override
    public void generateLoot() {
        Random random = new Random();
        boolean hasAmmo = random.nextBoolean();
        if (hasAmmo) {
            int amount = random.nextInt(4)*10+20;
            Ammunition ammo = new Ammunition(getPosition(), getWeapon().getNewProjectile(), amount);
            ammunitionDrops.add(ammo);
            getLoot().add(ammo);
        }
        boolean hasPowerUp = random.nextBoolean();
        if (hasPowerUp) {
            PowerUpI powerUp = PowerUpGenerator.generatePowerUp();
            powerUp.setPosition(getPosition());
            powerUpDrops.add(powerUp);
            getLoot().add(powerUp);
        }
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.PLEB;
    }
}
