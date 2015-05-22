package edu.chl.blastinthepast.model.ammunition;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-15.
 */
public interface AmmunitionInterface extends Collidable{
    public PositionInterface getPosition();
    public void setPosition(PositionInterface position);
    public ProjectileInterface getType();
    public int getAmount();
}
