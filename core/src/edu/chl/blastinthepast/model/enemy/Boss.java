package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.loot.LootInterface;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;

/**
 * Created by Mattias on 15-05-07.
 */
public class Boss extends Enemy {

    public Boss(CharacterI player, LootInterface lootStyle) {
        this(200, 15, player, lootStyle);
    }

    private Boss(int movementSpeed, int health, CharacterI player, LootInterface lootStyle) {
        super(movementSpeed, health, player, 128, 128, lootStyle);
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.BOSS;
    }
}