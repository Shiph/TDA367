package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.loot.LootInterface;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class Pleb extends Enemy {

    public Pleb (CharacterI player, LootInterface lootStyle) {
        this(150, 5, player, lootStyle);
    }

    private Pleb(int movementSpeed, int health, CharacterI player, LootInterface lootStyle) {
        super(movementSpeed, health, player, 64, 64, lootStyle);
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.PLEB;
    }
}
