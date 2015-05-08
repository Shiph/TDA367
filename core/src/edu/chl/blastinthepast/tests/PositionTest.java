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
     */
    @Test
    public void testEquals() {
        Position pos = position.getPosition();
        assertTrue(pos.equals(position));
        assertFalse(pos == position);
    }

}
