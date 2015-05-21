package edu.chl.blastinthepast.model.weapon;

import edu.chl.blastinthepast.model.player.Character;

/**
 * Created by Mattias on 15-05-21.
 */
public class WeaponFactory {

    public WeaponInterface getWeapon(Character character, WeaponInterface.WeaponType weaponType) {

        if(character == null) {
            return null;
        }

        switch(weaponType) {
            case AK47:
                return new AK47(character.getPosition(), character.getAimVector(), character.getMovementVector());
            case MAGNUM:
                return new Magnum(character.getPosition(), character.getAimVector(), character.getMovementVector());
        }

        return null;
    }

}
