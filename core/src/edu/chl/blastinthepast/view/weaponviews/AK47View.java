package edu.chl.blastinthepast.view.weaponviews;

import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.view.assets.GraphicalAssets;

/**
 * Created by Mattias on 15-05-12.
 */
public class AK47View extends WeaponView {

    public AK47View(WeaponInterface weapon) {
        super(weapon, GraphicalAssets.AK47);
    }

}