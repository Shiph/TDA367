package edu.chl.blastinthepast.tests;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

import java.util.ArrayList;

/**
 * Created by Shif on 11/05/15.
 */
public class MockPlayer implements Character {

    public PositionInterface position = new Position(0,0);
    public int movementSpeed = 100;
    public WeaponInterface weapon;
    private int health = 100;

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
    public CharacterType getCharacterType() {
        return null;
    }

}
