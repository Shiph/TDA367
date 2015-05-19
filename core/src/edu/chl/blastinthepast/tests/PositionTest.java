package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.utils.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mattias on 15-05-08.
 */
public class PositionTest {

    Position position;
    Position pos;

    @Before
    public void setUp() {
        position = new Position(15,15);
        pos = position.getPosition();
    }

    /**
     * Checks that the two positions does not share reference but that they still have the same x and y coordinates.
     * Also checks that two different positions does not return true.
     */
    @Test
    public void testEquals() {
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
        pos.setPosition(-10,-10);
        assertTrue(pos.overlaps(position));

        pos.setPosition(100,100);
        assertFalse(pos.overlaps(position));

        pos.setPosition(position.getPosition());
        assertTrue(pos.overlaps(position));
    }

    @After
    public void tearDown() {}

}
