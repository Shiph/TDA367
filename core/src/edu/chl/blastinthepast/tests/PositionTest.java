package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.utils.Position;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mattias on 15-05-08.
 */
public class PositionTest {

    Position position = new Position(15,15);

    /**
     * Checks that the two positions does not share reference but that they still have the same x and y coordinates.
     * Also checks that two different positions does not return true.
     */
    @Test
    public void testEquals() {
        Position pos = position.getPosition();
        assertTrue(pos.equals(position));
        assertFalse(pos == position);

        pos.setPosition(-15,-15);
        assertFalse(pos.equals(position));
    }

    /**
     * This method is used for looting a chest and will test if positions near it will return true, even if negative values.
     */
    @Test
    public void testOverlaps() {
        Position pos = new Position(-10,-10);
        assertTrue(pos.overlaps(position));

        pos.setPosition(100,100);
        assertFalse(pos.overlaps(position));

        pos.setPosition(position.getPosition());
        assertTrue(pos.overlaps(position));
    }

}
