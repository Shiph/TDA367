package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.enemy.EnemyFactory;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.player.*;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
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
        enemy = enemyFactory.getEnemy(new MockPlayer(), CharacterTypeEnum.PLEB);
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

    @Test
    public void testCollision(){
        MockCollidable collidable = new MockCollidable();
        collidable.rectangle.setPosition(0, 0);
        collidable.rectangle.setSize(100, 100);
        enemy.setPosition(new MockPosition(0, 0));
        assertTrue(enemy.isColliding(collidable));
    }

    //should we have testUpdate as well?

}