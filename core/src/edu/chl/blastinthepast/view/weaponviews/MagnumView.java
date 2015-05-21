package edu.chl.blastinthepast.view.weaponviews;

import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.GraphicalAssets;
import edu.chl.blastinthepast.view.weaponviews.WeaponView;

/**
 * Created by Shif on 12/05/15.
 */
public class MagnumView extends WeaponView {

    public MagnumView(WeaponInterface weapon) {
        super(weapon, GraphicalAssets.MAGNUM);
    }

}
