package edu.chl.blastinthepast.model.chest;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public interface ChestInterface extends Collidable {

    public WeaponInterface open(edu.chl.blastinthepast.model.player.Character character);
    public boolean isOpened();
    public PositionInterface getPosition();

}
