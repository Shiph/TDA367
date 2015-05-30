package edu.chl.blastinthepast.model.chest;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.model.player.CharacterI;

/**
 * Created by Shif on 06/05/15.
 */
public interface ChestInterface extends Collidable {

    WeaponInterface open(CharacterI characterI);
    boolean isOpened();
    PositionInterface getPosition();

}
