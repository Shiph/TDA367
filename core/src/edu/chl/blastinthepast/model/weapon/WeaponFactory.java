package edu.chl.blastinthepast.model.weapon;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-05-21.
 */
public class WeaponFactory {

    public WeaponInterface getWeapon(PositionInterface position, Vector2 aimVector, Vector2 movementVector, WeaponInterface.WeaponType weaponType) {

        switch(weaponType) {
            case AK47:
                return new AK47(position, aimVector, movementVector);
            case MAGNUM:
                return new Magnum(position, aimVector, movementVector);
        }
        return null;
    }

}