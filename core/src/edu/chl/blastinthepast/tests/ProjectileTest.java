package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.projectiles.AK47Projectile;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public class ProjectileTest {

    ProjectileInterface projectile;
    MockPosition pos;
    MockCollidable collidable;
    private int deltaTime = 1;
    private int offset = 10;

    @Before
    public void setUp() {
        projectile = new AK47Projectile(new MockPosition(), new Vector2(0,0), new Vector2(0,0), 0);
        pos = new MockPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
        collidable = new MockCollidable();
    }

    /**
     * Asserts that the projectile actually moves by comparing its position before and after.
     */
    @Test
    public void testMove() {
        projectile.move(deltaTime);
        assertFalse(pos == new MockPosition(projectile.getPosition().getX(), projectile.getPosition().getY()));
    }

    /**
     * Tests if the projectile is colliding with an object with two given positions,
     * where the first position is the same as the projectile's and where the other position is mildly off.
     */
    @Test
    public void testIsColliding() {
        collidable.getRectangle().setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
        assertTrue(projectile.isColliding(collidable));
        collidable.getRectangle().setPosition(projectile.getPosition().getX() - offset, projectile.getPosition().getY() - offset);
        assertFalse(projectile.isColliding(collidable));
    }

    @After
    public void tearDown() {

    }
}