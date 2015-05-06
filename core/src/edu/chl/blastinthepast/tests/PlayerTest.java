package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Player;
import edu.chl.blastinthepast.utils.Position;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonas on 2015-05-06.
 */
public class PlayerTest {

    private Player player;

    @Before
    public void before(){
        player=new Player();
    }

    @Test
    public void testCalculateDirection(){
        player.calculateDirection(new Position(1, 1));
        Vector2 v = new Vector2(1, 1);
        v.scl(1 / v.len());
        assertTrue(player.getAimDirection().equals(v));
    }

    @Test
    public void testMove(){
        player.setPosition(0, 0);
        player.setMovementDirection("north");
        player.move(200f);
        assertTrue(player.getPosition().getY()>0);
        player.setPosition(0, 0);
        player.setMovementDirection("south");
        player.move(200f);
        assertTrue(player.getPosition().getY()<0);
        player.setPosition(0, 0);
        player.setMovementDirection("west");
        player.move(200f);
        assertTrue(player.getPosition().getX()<0);
        player.setPosition(0, 0);
        player.setMovementDirection("east");
        player.move(200f);
        assertTrue(player.getPosition().getX()>0);
    }


    @After
    public void tearDown() throws Exception {

    }
}