package edu.chl.blastinthepast.view.weaponviews;


import edu.chl.blastinthepast.model.weapon.WeaponInterface;

/**
 * Created by Mattias on 15-05-21.
 */
public class WeaponViewFactory {

    public WeaponView getWeaponView (WeaponInterface weapon) {

        if(weapon == null) {
            return null;
        }

        switch(weapon.getWeaponType()) {
            case AK47:
                return new AK47View(weapon);
            case MAGNUM:
                return new MagnumView(weapon);
        }

        return null;
    }

}