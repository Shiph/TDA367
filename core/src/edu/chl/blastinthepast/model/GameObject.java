package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-13.
 */
public interface GameObject {
    public PositionInterface getPosition();
    public void setPosition(PositionInterface position);
}
