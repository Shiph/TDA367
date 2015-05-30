package edu.chl.blastinthepast.model.ammunition;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by jonas on 2015-05-15.
 */
public interface AmmunitionInterface extends Collidable{
    public PositionInterface getPosition();
    public void setPosition(PositionInterface newPosition);
    public ProjectileInterface getType();
    public int getAmount();
}
