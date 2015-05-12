package edu.chl.blastinthepast.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Mattias on 15-04-23.
 */
public interface Character {

    public void move(float dt);

    public void setMovementSpeed(int newSpeed);

    public int getMovementSpeed();

    public Weapon getWeapon();

    public int getHealth();

    public void setHealth(int newHealth);

    public ArrayList<ProjectileInterface> getProjectiles();


}