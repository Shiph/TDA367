package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jonas on 2015-05-21.
 *
 * LootInterface is a part of the Strategy pattern implementation for loot generation.
 * When creating a new loot strategy, implement the LootStrategy interface and classes that inherits the enemy class
 * will be able to use the new strategy.
 *
 * The Strategy pattern is used for loot generation since it allows the
 * same class of enemies to generate different types of loot.
 *
 */
public interface LootInterface {

    public HashMap<String, ArrayList<? extends Object>> generateLoot(PositionInterface spawnPosition, WeaponInterface weapon);

}