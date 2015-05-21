package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.enemy.EnemyFactory;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Shif on 11/05/15.
 */
public class EnemyTest {

    private Enemy enemy;
    private EnemyFactory enemyFactory;

    @Before
    public void before(){
        enemyFactory = new EnemyFactory();
        enemy = enemyFactory.getEnemy("Pleb", new MockPlayer());
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
        WeaponInterface weapon = enemy.getCurrentWeapon();
        enemy.setWeapon(new MockWeapon());
        assertTrue(!weapon.equals(enemy.getCurrentWeapon()));
    }

    @Test
    public void testSetPosition() throws Exception {
        PositionInterface pos = enemy.getPosition();
        enemy.setPosition(new MockPosition(13,37));
        assertTrue(!pos.equals(enemy.getPosition()));
    }

    //should we have testUpdate as well?

}