package edu.chl.blastinthepast.view;

import edu.chl.blastinthepast.model.entities.WeaponInterface;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by Mattias on 15-05-12.
 */
public class AK47View extends WeaponView {

    public AK47View(WeaponInterface weapon) {
        super(weapon, GraphicalAssets.AK47);
    }

}