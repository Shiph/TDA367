package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Rectangle;

/**
 * Created by jonas on 2015-05-21.
 */
public interface Collidable {
    boolean isColliding(Collidable c);
    Rectangle getRectangle();
}
