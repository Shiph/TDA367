package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import edu.chl.blastinthepast.model.Weapon;

/**
 * Created by jonas on 2015-04-23.
 */
public class WeaponView {
    private Texture texture;
    private Sprite sprite;
    private Weapon weapon;

    public WeaponView(Weapon newWeapon){
        weapon=newWeapon;
    }
}
