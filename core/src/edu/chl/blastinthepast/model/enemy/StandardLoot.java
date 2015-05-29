package edu.chl.blastinthepast.model.enemy;

import edu.chl.blastinthepast.model.ammunition.Ammunition;
import edu.chl.blastinthepast.model.ammunition.AmmunitionInterface;
import edu.chl.blastinthepast.model.powerUp.PowerUpGenerator;
import edu.chl.blastinthepast.model.powerUp.PowerUpI;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jonas on 2015-05-21.
 */
public class StandardLoot implements LootInterface{



    @Override
    public HashMap<String, ArrayList<? extends Object>> generateLoot(PositionInterface spawnPosition, WeaponInterface weapon) {
        HashMap<String, ArrayList<? extends Object>> loot = new HashMap<String, ArrayList<? extends Object>>();
        ArrayList<PowerUpI> powerUpLoot = new ArrayList<PowerUpI>();
        ArrayList<AmmunitionInterface> ammoLoot = new ArrayList<AmmunitionInterface>();
        Random random = new Random();
        boolean hasAmmo = random.nextBoolean();
        if (hasAmmo) {
            int amount = random.nextInt(4)*10+20;
            Ammunition ammo = new Ammunition(spawnPosition, weapon.getProjectile(), amount);
            ammoLoot.add(ammo);
        }
        boolean hasPowerUp = random.nextBoolean();
        if (hasPowerUp) {
            PowerUpI powerUp = PowerUpGenerator.generatePowerUp();
            powerUp.setPosition(spawnPosition);
            powerUpLoot.add(powerUp);
        }
        loot.put("Ammunition Loot", ammoLoot);
        loot.put("PowerUp Loot", powerUpLoot);
        return loot;
    }
}
