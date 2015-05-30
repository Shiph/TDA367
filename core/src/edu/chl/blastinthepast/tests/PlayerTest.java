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
        player=new Player(new MockPosition());
    }

    @Test
    public void testCalculateDirection(){
        player.calculateDirection(new MockPosition(1, 1));
        Vector2 v = new Vector2(1, 1);
        v.scl(1 / v.len());
        assertTrue(player.getAimVector().equals(v));
        Vector2 v2 = new Vector2(5, 2);
        assertFalse(player.getAimVector().equals(v2));
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

    @Test
    public void testCollision(){
        MockCollidable c = new MockCollidable();
        c.getRectangle().setSize(100, 100);
        c.getRectangle().setPosition(0, 0);
        assertTrue(player.isColliding(c));

        c.getRectangle().setPosition(player.getRectangle().getWidth(), player.getRectangle().getHeight());
        assertFalse(player.isColliding(c));

        c.getRectangle().setPosition(-10, -10);
        assertTrue(player.isColliding(c));

        c.getRectangle().setSize(-1, -1);
        c.getRectangle().setPosition(player.getRectangle().getWidth()/2, player.getRectangle().getHeight()/2);
        assertTrue(player.isColliding(c));

        c.getRectangle().setSize(0, 0);
        assertTrue(player.isColliding(c));

        c.getRectangle().setSize(1, 1);
        c.getRectangle().setSize(player.getRectangle().getWidth()-1, player.getRectangle().getHeight()-1);
        assertTrue(player.isColliding(c));
    }

    @Test
    public void testShoot(){
        MockWeapon w = new MockWeapon();
        player.addWeapon(w);
        assertTrue(player.getProjectiles().size() == 0);
        w.bulletsInMag=0;
        player.shoot();
        assertTrue(player.getProjectiles().size() == 0);
        w.bulletsInMag=5;
        player.shoot();
        assertTrue(player.getProjectiles().size()>0);

    }

    @Test
    public void testReloadCurrentWeapon(){
        MockWeapon w = new MockWeapon();
        player.addWeapon(w);
        player.shoot();
        assertTrue(player.getCurrentWeapon().getbulletsLeftInMagazine()<player.getCurrentWeapon().getMagazineCapacity());
        player.reloadCurrentWeapon();
        assertTrue(player.getCurrentWeapon().getbulletsLeftInMagazine()==player.getCurrentWeapon().getMagazineCapacity());
    }

    @Test
    public void testAddWeapon(){
        MockWeapon w = new MockWeapon();
        player.addWeapon(w);
        assertTrue(w.equals(player.getWeapon()));
    }

    @Test
    public void testDie(){
        MockPCL deathPcl = new MockPCL();
        player.addListener(deathPcl);
        player.die();
        assertTrue(deathPcl.eventName.equals("Player died"));
    }

    @After
    public void tearDown() throws Exception {

    }
}