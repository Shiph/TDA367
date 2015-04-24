package edu.chl.blastinthepast.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.utils.Constants;

import java.util.Iterator;

/**
 * Created by Shif on 20/04/15.
 */
public class BPModel {
    private Array<Projectile> projectiles = new Array<Projectile>();
    public BPModel() {

    }

    public void update(){
        isProjectilesOutOfBounds();
    }

    /**
     * Checks if a projectile is outside the map and if so removes it
     */
    private void isProjectilesOutOfBounds() {
        Iterator<Projectile> iter = projectiles.iterator();
        while(iter.hasNext()) {
            Projectile p = iter.next();
            if((p.getPosition().getY() < 0) || (p.getPosition().getY() > Constants.MAP_HEIGHT) ||
                    (p.getPosition().getX() > Constants.MAP_WIDTH) || (p.getPosition().getX() < 0)) {
                iter.remove();
            }
        }
    }



}
