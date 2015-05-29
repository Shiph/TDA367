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

    public Boss(Character player, LootInterface lootStyle) {
        this(200, 15, player, lootStyle);
    }

    private Boss(int movementSpeed, int health, Character player, LootInterface lootStyle) {
        super(movementSpeed, health, player, 128, 128, lootStyle);
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.BOSS;
    }
}