package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

/**
 * Created by jonas on 2015-05-22.
 */
public class MockCollidable implements Collidable {

    public Rectangle rectangle = new RectangleAdapter();

    @Override
    public boolean isColliding(Collidable c) {
        return rectangle.contains(c.getRectangle());
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
}
