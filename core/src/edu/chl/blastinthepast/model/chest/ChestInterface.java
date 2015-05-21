package edu.chl.blastinthepast.model.chest;

import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;
import edu.chl.blastinthepast.model.player.Character;

/**
 * Created by Shif on 06/05/15.
 */
public interface ChestInterface {

    WeaponInterface open(Character character);
    boolean isOpened();
    PositionInterface getPosition();

}
