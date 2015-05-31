package edu.chl.blastinthepast.model.chest;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.model.player.CharacterI;

/**
 * Created by Shif on 06/05/15.
 */
public interface ChestInterface extends Collidable {

    /**
     * Flags the chest that it's opened and returns a weapon.
     * @param characterI The character that loots the chest.
     * @return a new weapon.
     */
    WeaponInterface open(CharacterI characterI);

    /**
     * This method is called before (and if) the player character actually opens the chest.
     * @return <code>true</code> if the chest is already opened.
     */
    boolean isOpened();

    PositionInterface getPosition();

}
