package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Shif on 06/05/15.
 */
public interface ChestInterface {

    public Weapon open(Character character);
    public boolean isOpened();
    public Position getPosition();

}
