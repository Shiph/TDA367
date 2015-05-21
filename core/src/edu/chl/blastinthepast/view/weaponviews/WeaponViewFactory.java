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
        switch(weapon.toString()) {
            case "AK47":
                return new AK47View(weapon);
            case "Magnum":
                return new MagnumView(weapon);
        }
        return null;
    }

}
