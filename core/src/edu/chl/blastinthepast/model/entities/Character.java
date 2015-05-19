package edu.chl.blastinthepast.model.entities;

import java.util.ArrayList;

import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface Character {

    void move(float dt);
    void setMovementSpeed(int newSpeed);
    int getMovementSpeed();
    WeaponInterface getWeapon();
    void setWeapon (WeaponInterface weapon);
    int getHealth();
    void setHealth(int newHealth);
    ArrayList<ProjectileInterface> getProjectiles();
    PositionInterface getPosition();
    void update(float dt);
    PositionInterface getPrevPos();
    void setPosition(PositionInterface newPosition);
    void addBonusMovementSpeed(int bonusSpeed);
    int getBonusMovementSpeed();
    int getTotalMovementSpeed();
    ArrayList<WeaponInterface> getWeaponArray();
    void resetBonuses();
}