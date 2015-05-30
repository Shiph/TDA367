package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.loot.LootInterface;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class Pleb extends Enemy {

    public Pleb (CharacterI player, LootInterface lootStyle, PositionInterface position) {
        this(150, 5, player, lootStyle, position);
    }

    private Pleb(int movementSpeed, int health, CharacterI player, LootInterface lootStyle, PositionInterface position) {
        super(movementSpeed, health, player, 64, 64, lootStyle, position);
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.PLEB;
    }

}
