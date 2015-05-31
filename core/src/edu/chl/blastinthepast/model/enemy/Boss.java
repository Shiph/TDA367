package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.loot.LootInterface;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.position.PositionInterface;

import java.util.Random;

/**
 * A tougher enemy than the pleb.
 * Guarantees to loot both ammunition and a power-up when killed.
 *
 * Created by Mattias on 15-05-07.
 */
public class Boss extends Enemy {

    public Boss(CharacterI player, LootInterface lootStyle, PositionInterface position) {
        this(200, 15, player, lootStyle, position);
    }

    private Boss(int movementSpeed, int health, CharacterI player, LootInterface lootStyle, PositionInterface position) {
        super(movementSpeed, health, player, 40, 60, lootStyle, position);
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.BOSS;
    }

}