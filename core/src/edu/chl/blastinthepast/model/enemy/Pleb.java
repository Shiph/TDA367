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

    public Pleb (Character player, LootInterface lootStyle) {
        this(150, 5, player, lootStyle);
    }

    private Pleb(int movementSpeed, int health, Character player, LootInterface lootStyle) {
        super(movementSpeed, health, player, 64, 64, lootStyle);
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return CharacterTypeEnum.PLEB;
    }
}
