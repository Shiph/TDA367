package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.powerUp.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by jonas on 2015-05-30.
 */
public class PowerUpTest {

    HealthPowerUp healthPowerUp;
    DamagePowerUp damagePowerUp;
    FireRatePowerUp fireRatePowerUp;
    MovementSpeedPowerUp movementSpeedPowerUp;
    MockPowerUp mockPowerUp;
    MockPlayer mockPlayer;


    @Before
    public void before(){
        mockPlayer = new MockPlayer();
        healthPowerUp = new HealthPowerUp();
        damagePowerUp = new DamagePowerUp();
        fireRatePowerUp = new FireRatePowerUp();
        movementSpeedPowerUp = new MovementSpeedPowerUp();
        mockPowerUp = new MockPowerUp();
        healthPowerUp.init(mockPlayer);
        damagePowerUp.init(mockPlayer);
        fireRatePowerUp.init(mockPlayer);
        movementSpeedPowerUp.init(mockPlayer);
        mockPowerUp.init(mockPlayer);
    }

    @Test
    public void testInit(){
        assertTrue(mockPowerUp.getCharacter().equals(mockPlayer));
    }

    @Test
    public void testUpdate(){
        mockPowerUp.setDuration(0);
        mockPowerUp.update();
        assertTrue(mockPowerUp.powerUpApplications==1); //Test if the power up is applied once even if the power-up
        // has technically expired

        mockPowerUp.update();
        assertTrue(mockPowerUp.getHasExpired());//Test if the power up expires when the duration is over
        assertTrue(mockPowerUp.powerUpApplications==1); //Test if the power up is applied when it has expired

        mockPowerUp.setDuration(10000);
        mockPowerUp.update();
        assertTrue(mockPowerUp.powerUpApplications==2);

        mockPowerUp.setDuration(-10000);
        mockPowerUp.update();
        assertTrue(mockPowerUp.powerUpApplications==2);
    }

    @Test
    public void testCollision(){
        MockCollidable collidable = new MockCollidable();
        collidable.rectangle.set(0, 0, 100, 100);
        assertTrue(mockPowerUp.isColliding(collidable));
    }

    @Test
    public void testHealthPowerUp(){
        mockPlayer.setHealth(0);
        healthPowerUp.update();
        assertTrue(mockPlayer.getHealth()>0);
        int health = mockPlayer.getHealth();
        healthPowerUp.update();
        assertTrue(mockPlayer.getHealth()==health);

        HealthPowerUp hp1 = new HealthPowerUp();
        HealthPowerUp hp2 = new HealthPowerUp();
        assertTrue(hp1.equals(hp2));

        Object hp3 = new HealthPowerUp();
        assertTrue(hp1.equals(hp3));

        assertFalse(healthPowerUp.equals(damagePowerUp));
    }

    @Test
    public void testDamagePowerUp(){
        MockWeapon w = new MockWeapon();
        mockPlayer.addWeapon(w);
        assertTrue(mockPlayer.getCurrentWeapon().getBonusDamage()==0);
        damagePowerUp.update();
        assertTrue(mockPlayer.getCurrentWeapon().getBonusDamage()>0);
        w.resetBonuses();

        MockWeapon w2 = new MockWeapon();
        mockPlayer.addWeapon(w2);
        damagePowerUp.update();
        assertTrue(w.getBonusDamage()>0);

        DamagePowerUp dp1 = new DamagePowerUp();
        DamagePowerUp dp2 = new DamagePowerUp();
        assertTrue(dp1.equals(dp2));

        Object dp3 = new DamagePowerUp();
        assertTrue(dp1.equals(dp3));

        assertFalse(damagePowerUp.equals(healthPowerUp));
    }

    @Test
    public void testFireRatePowerUp(){
        MockWeapon w = new MockWeapon();
        mockPlayer.addWeapon(w);
        assertTrue(mockPlayer.getCurrentWeapon().getBonusFireRate()==0);
        fireRatePowerUp.update();
        assertTrue(mockPlayer.getCurrentWeapon().getBonusFireRate()>0);

        FireRatePowerUp fp1 = new FireRatePowerUp();
        FireRatePowerUp fp2 = new FireRatePowerUp();
        assertTrue(fp1.equals(fp2));

        Object fp3 = new FireRatePowerUp();
        assertTrue(fp1.equals(fp3));

        assertFalse(fireRatePowerUp.equals(movementSpeedPowerUp));
    }

    @Test
    public void testMovementSpeedPowerUp(){
        assertTrue(mockPlayer.getBonusMovementSpeed()==0);
        movementSpeedPowerUp.update();
        assertTrue(mockPlayer.getBonusMovementSpeed()>0);

        MovementSpeedPowerUp mp1 = new MovementSpeedPowerUp();
        MovementSpeedPowerUp mp2 = new MovementSpeedPowerUp();
        assertTrue(mp1.equals(mp2));

        Object mp3 = new MovementSpeedPowerUp();
        assertTrue(mp1.equals(mp3));

        assertFalse(movementSpeedPowerUp.equals(fireRatePowerUp));
    }

}
