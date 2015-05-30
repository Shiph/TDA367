package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.enemy.EnemyFactory;
import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.player.*;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

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
        enemy = enemyFactory.getEnemy(new MockPlayer(), CharacterTypeEnum.PLEB, new MockPosition());
    }

    @Test
    public void testMove() throws Exception {
        MockPosition p = new MockPosition(500, 500);
        // move west
        enemy.setPosition(new MockPosition(p));
        enemy.setMovementDirection(0);
        enemy.move(100);
        assertTrue(p.getX() > enemy.getPosition().getX());
        // move east
        enemy.setMovementDirection(1);
        enemy.setPosition(new MockPosition(p));
        enemy.move(100);
        assertTrue(p.getX() < enemy.getPosition().getX());
        // move north
        enemy.setMovementDirection(2);
        enemy.setPosition(new MockPosition(p));
        enemy.move(100);
        assertTrue(p.getY() < enemy.getPosition().getY());
        // move south
        enemy.setMovementDirection(3);
        enemy.setPosition(new MockPosition(p));
        enemy.move(100);
        assertTrue(p.getY() > enemy.getPosition().getY());
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

    @Test
    public void testIsPlayerInRange() {
        MockPlayer player = new MockPlayer();
        player.setPosition(new MockPosition(500, 500));
        enemy = enemyFactory.getEnemy(player, CharacterTypeEnum.PLEB, new MockPosition());
        // west
        enemy.setPosition(new MockPosition(799, 500));
        enemy.setMovementDirection(0);
        assertTrue(enemy.isPlayerInRange());
        enemy.setPosition(new MockPosition(801, 500));
        enemy.update(100);
        assertFalse(enemy.isPlayerInRange());
        // east
        enemy.setPosition(new MockPosition(201, 500));
        enemy.setMovementDirection(1);
        enemy.update(100);
        assertTrue(enemy.isPlayerInRange());
        enemy.setPosition(new MockPosition(199, 500));
        enemy.update(100);
        assertFalse(enemy.isPlayerInRange());
        // north
        enemy.setPosition(new MockPosition(500, 201));
        enemy.setMovementDirection(2);
        enemy.update(100);
        assertTrue(enemy.isPlayerInRange());
        enemy.setPosition(new MockPosition(500, 199));
        enemy.update(100);
        assertFalse(enemy.isPlayerInRange());
        // south
        enemy.setPosition(new MockPosition(500, 799));
        enemy.setMovementDirection(3);
        enemy.update(100);
        assertTrue(enemy.isPlayerInRange());
        enemy.setPosition(new MockPosition(199, 801));
        enemy.update(100);
        assertFalse(enemy.isPlayerInRange());
    }

    @Test
    public void testDie() {
        MockPCL pcl = new MockPCL();
        enemy.addListener(pcl);
        enemy.die();
        assertTrue(pcl.eventName.equals("Ammunition drops"));
        assertTrue(pcl.newValue instanceof ArrayList);
    }

    //should we have testUpdate as well?

}