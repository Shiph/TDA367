package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Position;
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
        if(!isOpen && character.getPosition().overlaps(position)) {
            isOpen = true;
            return weapon;
        }
        return null;
    }

    public boolean isOpened() {
        return isOpen;
    }

    public PositionInterface getPosition() {
        return position;
    }

}
