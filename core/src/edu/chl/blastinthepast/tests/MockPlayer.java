package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.*;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by Shif on 11/05/15.
 */
public class MockPlayer implements CharacterI {

    public PositionInterface position;
    public Rectangle rectangle;
    public int movementSpeed = 100;
    public WeaponInterface weapon;
    public int health = 100;
    public ArrayList<WeaponInterface> weapons = new ArrayList<WeaponInterface>();
    public int bonusMovementSpeed = 0;

    public MockPlayer() {
        super();
        position = new MockPosition();
        rectangle = new RectangleAdapter();
        rectangle.setSize(10);
    }

    @Override
    public void move(float dt) {
        position.setX(position.getX() + movementSpeed * dt);
    }

    @Override
    public void setMovementSpeed(int newSpeed) {
        this.movementSpeed = newSpeed;
    }

    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public WeaponInterface getCurrentWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    @Override
    public ArrayList<ProjectileInterface> getProjectiles() {
        return null;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public PositionInterface getPrevPos() {
        return null;
    }

    @Override
    public void setPosition(PositionInterface newPosition) {
        position.setPosition(newPosition);
        rectangle.setPosition(position.getX(), position.getY());
    }

    @Override
    public void addBonusMovementSpeed(int bonusSpeed) {
        bonusMovementSpeed+=bonusSpeed;
    }

    @Override
    public int getBonusMovementSpeed() {
        return bonusMovementSpeed;
    }

    @Override
    public int getTotalMovementSpeed() {
        return movementSpeed+bonusMovementSpeed;
    }

    @Override
    public ArrayList<WeaponInterface> getAllWeapons() {
        return weapons;
    }

    @Override
    public void resetBonuses() {
        bonusMovementSpeed=0;
    }

    @Override
    public Vector2 getMovementVector() {
        return null;
    }

    @Override
    public Vector2 getAimVector() {
        return null;
    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return null;
    }

    @Override
    public void addListener(PropertyChangeListener pcl) {

    }

    @Override
    public boolean isColliding(Collidable c) {
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void addWeapon(WeaponInterface w){
        weapons.add(w);
        setWeapon(w);
    }


}
