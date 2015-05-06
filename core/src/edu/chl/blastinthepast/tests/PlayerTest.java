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
        System.out.println("hejhej");
        player=new Player();
    }

    @Test
    public void testCalculateDirection(){
        System.out.println("bajbaj");
        player.setPosition(0, 0);
        player.calculateDirection(new Position(1, 1));
        Vector2 v=new Vector2(1, 1);
        v.scl(1 / v.len());
        assertTrue(player.getAimDirection().equals(v));
    }

    @After
    public void after(){
        System.out.println("hejdå");
        player=new Player();
    }


    @After
    public void tearDown() throws Exception {

    }
}