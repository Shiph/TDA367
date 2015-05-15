package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.model.entities.ProjectileInterface;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-15.
 */
public interface AmmunitionInterface {
    public PositionInterface getPosition();
    public void setPosition(PositionInterface position);
    public ProjectileInterface getType();
    public int getAmount();
}
