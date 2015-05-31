package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Rectangle;

/**
 * Created by jonas on 2015-05-21.
 */
public interface Collidable {

    /**
     * Checks if two collidables are colliding.
     *
     * @param c - an object that can collide
     * @return <code>true</code>  if a collidable's rectangle overlaps the given collidables rectangle. <code>false</code> otherwise.
     */
    boolean isColliding(Collidable c);

    Rectangle getRectangle();

}
