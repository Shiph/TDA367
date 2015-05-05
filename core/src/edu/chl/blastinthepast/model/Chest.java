package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Mattias on 15-05-05.
 */
public class Chest {
    private boolean isOpen = false;
    private Weapon weapon;
    private final Position position;

    public Chest(Weapon weapon) {
        this.weapon = weapon;
        position = weapon.getPosition();
    }

    public Weapon open() {
        if(!isOpened()) {
            isOpen = true;
            return weapon;
        }
        return null;
    }

    public boolean isOpened() {
        return isOpen;
    }

    public Position getPosition() {
        return position;
    }

}
