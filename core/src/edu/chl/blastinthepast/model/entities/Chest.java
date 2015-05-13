package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-05-05.
 */
public class Chest implements ChestInterface{
    private boolean isOpen = false;
    private WeaponInterface weapon;
    private final PositionInterface position;

    public Chest(WeaponInterface weapon) {
        this.weapon = weapon;
        position = weapon.getPosition();
    }

    public WeaponInterface open(Character character) {
            isOpen = true;
            return weapon;
    }

    public boolean isOpened() {
        return isOpen;
    }

    public PositionInterface getPosition() {
        return position;
    }

}
