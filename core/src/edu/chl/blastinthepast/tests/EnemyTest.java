package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.model.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Shif on 11/05/15.
 */
public class EnemyTest {

    private Enemy enemy = new Enemy(new MockPlayer(), new MockPosition());

    @Before
    public void before(){
        enemy = new Enemy(new MockPlayer(), new MockPosition());
    }

    @Test
    public void testMove() throws Exception {
        enemy.setPosition(new MockPosition(500, 500));
        PositionInterface pos = new MockPosition();
        pos.setPosition(enemy.getPosition());
        System.out.println("Mock position: " + pos.toString());
        enemy.move(1000);
        assertTrue(!pos.equals(enemy.getPosition()));
    }

    @Test
    public void testSetWeapon() throws Exception {
        WeaponInterface weapon = enemy.getWeapon();
        enemy.setWeapon(new MockWeapon());
        assertTrue(!weapon.equals(enemy.getWeapon()));
    }

    @Test
    public void testSetPosition() throws Exception {
        PositionInterface pos = enemy.getPosition();
        enemy.setPosition(new MockPosition(13,37));
        assertTrue(!pos.equals(enemy.getPosition()));
    }

    //should we have testUpdate as well?

}