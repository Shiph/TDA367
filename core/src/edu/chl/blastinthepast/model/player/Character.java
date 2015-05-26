package edu.chl.blastinthepast.model.player;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface Character extends Collidable{

    enum CharacterType {
        PLAYER, PLEB, BOSS
    }

    void move(float dt);
    void setMovementSpeed(int newSpeed);
    int getMovementSpeed();
    WeaponInterface getCurrentWeapon();
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
    ArrayList<WeaponInterface> getAllWeapons();
    void resetBonuses();
    Vector2 getMovementVector();
    Vector2 getAimVector();
    CharacterType getCharacterType();
    void addListener(PropertyChangeListener pcl);

}