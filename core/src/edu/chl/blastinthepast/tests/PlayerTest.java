package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.model.position.Position;
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
        player=new Player(new MockPosition(0, 0));
    }

    @Test
    public void testCalculateDirection(){
        player.calculateDirection(new MockPosition(1, 1));
        Vector2 v = new Vector2(1, 1);
        v.scl(1 / v.len());
        assertTrue(player.getAimVector().equals(v));
    }

    @Test
    public void testMoveWest(){
        player.setMovementDirection("west");
        player.move(200f);
        assertTrue(player.getPosition().getX()<0);
        assertTrue(player.getRectangle().getY()<0);

    }

    @Test
    public void testMoveEast(){
        player.setMovementDirection("east");
        player.move(200f);
        assertTrue(player.getPosition().getX()>0);
        assertTrue(player.getRectangle().getX()>0);
    }

    @Test
    public void testMoveNorth(){
        player.setMovementDirection("north");
        player.move(200f);
        assertTrue(player.getPosition().getY()>0);
        assertTrue(player.getRectangle().getY()>0);

        player.setPosition(new Position(0,0));
        player.setMovementDirection("east");
        player.move(200f);
        assertTrue(player.getPosition().getY()>0);
        assertTrue(player.getPosition().getX()>0);
        assertTrue(player.getRectangle().getY()>0);
        assertTrue(player.getRectangle().getX()>0);

        player.setPosition(new Position(0,0));
        player.setMovementDirection("west");
        player.move(200f);
        assertTrue(player.getPosition().getY()>0);
        assertTrue(player.getPosition().getX()<0);
        assertTrue(player.getRectangle().getY()>0);
        assertTrue(player.getRectangle().getX()<0);
    }

    @Test
    public void testMoveSouth(){
        player.setMovementDirection("south");
        player.move(200f);
        assertTrue(player.getPosition().getY()<0);
        assertTrue(player.getRectangle().getY()<0);

        player.setPosition(new Position(0,0));
        player.setMovementDirection("east");
        player.move(200f);
        assertTrue(player.getPosition().getY()<0);
        assertTrue(player.getPosition().getX()>0);
        assertTrue(player.getRectangle().getY()<0);
        assertTrue(player.getRectangle().getX()>0);

        player.setPosition(new Position(0,0));
        player.setMovementDirection("west");
        player.move(200f);
        assertTrue(player.getPosition().getY()<0);
        assertTrue(player.getPosition().getX()<0);
        assertTrue(player.getRectangle().getY()<0);
        assertTrue(player.getRectangle().getX()<0);
    }

    @Test
    public void testBonusMovementSpeed(){
        player.addBonusMovementSpeed(-5);
        assertTrue(player.getMovementSpeed()>=0);
        player.addBonusMovementSpeed(5);
        assertTrue(player.getTotalMovementSpeed()== (player.getBonusMovementSpeed() + player.getMovementSpeed()) );
    }



    @After
    public void tearDown() throws Exception {

    }
}