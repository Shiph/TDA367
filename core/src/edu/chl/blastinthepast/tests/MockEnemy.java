package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.Rectangle;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by Shif on 2015-05-30.
 */
public class MockEnemy implements CharacterI {

    MockWeapon weapon = new MockWeapon();
    ArrayList<MockWeapon> weapons = new ArrayList<MockWeapon>();

    @Override
    public void move(float dt) {

    }

    @Override
    public void setMovementSpeed(int newSpeed) {

    }

    @Override
    public int getMovementSpeed() {
        return 0;
    }

    @Override
    public WeaponInterface getCurrentWeapon() {
        return null;
    }

    @Override
    public void setWeapon(WeaponInterface weapon) {

    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int newHealth) {

    }

    @Override
    public ArrayList<ProjectileInterface> getProjectiles() {
        return null;
    }

    @Override
    public PositionInterface getPosition() {
        return null;
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

    }

    @Override
    public void addBonusMovementSpeed(int bonusSpeed) {

    }

    @Override
    public int getBonusMovementSpeed() {
        return 0;
    }

    @Override
    public int getTotalMovementSpeed() {
        return 0;
    }

    @Override
    public ArrayList<WeaponInterface> getAllWeapons() {
        return null;
    }

    @Override
    public void resetBonuses() {

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
    public void addListener(PropertyChangeListener pcl) {

    }

    @Override
    public CharacterTypeEnum getCharacterType() {
        return null;
    }

    @Override
    public boolean isColliding(Collidable c) {
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

}
