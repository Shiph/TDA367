package edu.chl.blastinthepast.model.player;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;

/**
 * Created by Mattias on 15-04-23.
 */
public interface CharacterI extends Collidable{

    void move(float dt);

    /**
     * Returns the currently equipped weapon.
     * @return
     */
    WeaponInterface getCurrentWeapon();

    /**
     * Returns the character type represented as an enum.
     * @return an enum representing the character type.
     */
    CharacterTypeEnum getCharacterType();

    /**
     * Returns a position containing x and y coordinates.
     * @return
     */
    PositionInterface getPosition();

    /**
     * Updates the state of the character.
     * Typically this means the application checks if the character has ran out of health or if a power-up is still active.
     * @param dt
     */
    void update(float dt);

    /**
     * If a movement power-up is active, more movement speed is added through this method.
     * @param bonusSpeed
     */
    void addBonusMovementSpeed(int bonusSpeed);

    int getBonusMovementSpeed();
    int getTotalMovementSpeed();
    PositionInterface getPrevPos();
    void setPosition(PositionInterface newPosition);
    void setMovementSpeed(int newSpeed);
    void setWeapon (WeaponInterface weapon);
    int getHealth();
    void setHealth(int newHealth);
    ArrayList<ProjectileInterface> getProjectiles();
    int getMovementSpeed();
    ArrayList<WeaponInterface> getAllWeapons();
    void resetBonuses();
    Vector2 getMovementVector();
    Vector2 getAimVector();
    void addListener(PropertyChangeListener pcl);

}