package edu.chl.blastinthepast.model.player;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

import java.util.ArrayList;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public abstract class Character implements CharacterI {

    private int bonusMovementSpeed;
    private int movementSpeed;
    private Vector2 movementVector;
    private ArrayList<ProjectileInterface> projectiles;
    private Vector2 aimVector;
    private PositionInterface position;
    private int health;
    private Rectangle rectangle;
    private PositionInterface prevPos;
    private WeaponInterface weapon;

    public Character() {
        bonusMovementSpeed = 0;
        movementVector = new Vector2(1, 0);
        aimVector = new Vector2(1,0);
        projectiles = new ArrayList<>();
        position = new Position(0,0);
        rectangle = new RectangleAdapter();
    }

    public Vector2 getMovementVector() {
        return movementVector;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle (PositionInterface position) {
        rectangle.setPosition(position);
    }

    public Vector2 getAimVector() {
        return aimVector;
    }

    public WeaponInterface getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    public PositionInterface getPosition(){
        return position;
    }

    public PositionInterface getPrevPos(){
        return prevPos;
    }

    public void setPrevpos(PositionInterface position) {
        prevPos = position;
    }

    public WeaponInterface getCurrentWeapon() {
        return weapon;
    };

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public ArrayList<ProjectileInterface> getProjectiles(){
        return projectiles;
    }

    public int getBonusMovementSpeed() {
        return bonusMovementSpeed;
    }

    public void addBonusMovementSpeed(int bonusSpeed) {
        if (bonusSpeed > 0) {
            bonusMovementSpeed += bonusSpeed;
        }
    }

    public void setBonusMovementSpeed(int bonusMovementSpeed) {
        this.bonusMovementSpeed = bonusMovementSpeed;
    }

    public void resetBonuses(){
        setBonusMovementSpeed(0);
    }

    public int getTotalMovementSpeed() {
        return movementSpeed + bonusMovementSpeed;
    }

    public void setPosition (PositionInterface position) {
        this.position = position;
        rectangle.setPosition(position);
    }

    public boolean isColliding(Collidable c){
        return rectangle.overlaps(c.getRectangle());
    }

}
