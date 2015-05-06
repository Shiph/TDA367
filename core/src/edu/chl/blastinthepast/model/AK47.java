package edu.chl.blastinthepast.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mattias on 15-05-05.
 */
public class AK47 extends Weapon {

    public AK47(PositionInterface position, Vector2 direction) {
        super(position, direction, 1000, 100, 20);
    }

    @Override
    public Projectile fire() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - latestShot) >= fireRate) {
            latestShot = System.currentTimeMillis();
            bulletsLeftInMagazine--;
            return new AK47Projectile(position, direction);
        }
        return null;
    }

}
