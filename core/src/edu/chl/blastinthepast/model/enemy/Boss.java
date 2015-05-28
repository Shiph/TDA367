package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.ammunition.Ammunition;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.powerUp.PowerUpGenerator;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;

import java.util.Random;

/**
 * Created by Mattias on 15-05-07.
 */
public class Boss extends Enemy {

    public Boss(Character player) {
        this(200, 15, player);
    }

    private Boss(int movementSpeed, int health, Character player) {
        super(movementSpeed, health, player, 128, 128);
    }

    @Override
    public void generateLoot() {
        Random random = new Random();
        boolean hasAmmo = true;
        if (hasAmmo) {
            int amount = random.nextInt(4)*10+40;
            Ammunition ammo = new Ammunition(getPosition(), getWeapon().getProjectile(), amount);
            ammunitionDrops.add(ammo);
            getLoot().add(ammo);
        }
        boolean hasPowerUp =true;
        if (hasPowerUp) {
            PowerUpI powerUp = PowerUpGenerator.generatePowerUp();
            powerUp.setPosition(getPosition());
            powerUpDrops.add(powerUp);
            getLoot().add(powerUp);
        }
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.BOSS;
    }
}