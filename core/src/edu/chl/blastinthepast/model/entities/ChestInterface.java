package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Shif on 06/05/15.
 */
public interface ChestInterface {

    public WeaponInterface open(Character character);
    public boolean isOpened();
    public PositionInterface getPosition();

}
