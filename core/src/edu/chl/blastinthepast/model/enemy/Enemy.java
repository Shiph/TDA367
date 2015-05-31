package edu.chl.blastinthepast.model.enemy;

import com.badlogic.gdx.math.Vector2;
import edu.chl.blastinthepast.model.loot.LootInterface;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.weapon.WeaponFactory;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.weapon.WeaponTypeEnum;
import edu.chl.blastinthepast.model.player.Character;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * Created by Mattias on 15-04-21.
 *
 * Classes that extends Enemy are Pleb and Boss.
 */
public abstract class Enemy extends Character {

    private final CharacterI player;
    private int movementSpeed;
    private WeaponFactory weaponFactory;
    private MyActionListener actionListener;
    private Timer timer;
    private int movementDirection;
    private Vector2 aimVector;
    private Vector2 playerDirectionVector;
    private Vector2 movementVector;
    private int range = 500;
    private ArrayList<Object> loot;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private LootInterface lootStuff;

    /**
     * Creates a new Enemy.
     *
     * @param movementSpeed - the movement speed of the enemy
     * @param health - the health (HP) of the enemy
     * @param player - reference to the active player
     * @param width - width of the enemy
     * @param height - height of the enemy
     * @param lootStyle - what type of loot/drop the enemy should leave behind when it dies
     * @param position - the position of the enemy
     */
    public Enemy(int movementSpeed, int health, CharacterI player, int width, int height, LootInterface lootStyle, PositionInterface position) {
        getRectangle().setSize(width, height);
        loot= new ArrayList<>();
        this.movementSpeed = movementSpeed;
        setHealth(health);
        this.player = player;
        setPosition(position);
        actionListener = new MyActionListener();
        Random r = new Random();
        movementDirection = r.nextInt(4);
        aimVector = new Vector2(1, 0);
        movementVector = new Vector2(1, 0);
        playerDirectionVector = new Vector2();
        weaponFactory = new WeaponFactory();
        setWeapon(weaponFactory.getWeapon(getPosition(), aimVector, movementVector, WeaponTypeEnum.AK47));
        timer = new Timer(1000, actionListener);
        timer.setRepeats(true);
        timer.start();
        lootStuff=lootStyle;
    }

    /**
     * The enemy can move in four different directions (West, East, North, South), determined by an int (0, 1, 2, 3).
     * The movement direction is updated in the private inner class MyActionListener.
     *
     * @param dt - delta time (the time span between the current frame and the last frame in seconds)
     */
    public void move(float dt) {
        movementVector.setLength(movementSpeed * dt);
        switch (movementDirection) {
            case 0: // move west
                movementVector.setAngle(180);
                getPosition().setX(getPosition().getX() + movementVector.x);
                aimVector.set(getPosition().getX() - range, getPosition().getY());
                break;
            case 1: // move east
                movementVector.setAngle(0);
                getPosition().setX(getPosition().getX() + movementVector.x);
                aimVector.set(getPosition().getX() + range, getPosition().getY());
                break;
            case 2: // move north
                movementVector.setAngle(90);
                getPosition().setY(getPosition().getY() + movementVector.y);
                aimVector.set(getPosition().getX(), getPosition().getY() + range);
                break;
            case 3: // move south
                movementVector.setAngle(270);
                getPosition().setY(getPosition().getY() + movementVector.y);
                aimVector.set(getPosition().getX(), getPosition().getY() - range);
                break;
            default:
                break;
        }
        setRectangle(getPosition());
    }

    public void setMovementDirection(int movementDirection) {
        this.movementDirection = movementDirection;
    }

    public int getMovementDirection() {
        return movementDirection;
    }

    @Override
    public ArrayList<WeaponInterface> getAllWeapons() {
        ArrayList<WeaponInterface> weaponArray=new ArrayList<WeaponInterface>();
        weaponArray.add(getWeapon());
        return weaponArray;
    }

    /**
     * Updates the vector from the enemy to the player. Updates the enemy's weapon's position. Fires weapon if the player is in range.
     *
     * @param dt - delta time (the time span between the current frame and the last frame in seconds)
     */
    public void update(float dt) {
        setPrevpos(new Position(getPosition()));
        playerDirectionVector.set(player.getPosition().getX() - getPosition().getX(), player.getPosition().getY() - getPosition().getY());
        getWeapon().setPosition(getPosition());
        if(isPlayerInRange()) {
            aimVector.set(playerDirectionVector);
            ProjectileInterface p = getWeapon().pullTrigger();
            if (p!=null){
                getProjectiles().add(p);
                pcs.firePropertyChange("New Projectile", null, p);
            }
        } else {
            updateAimVector(movementDirection);
        }

    }

    /**
     * Updates the aim vector so that it aligns with the movement direction.
     *
     * @param movementDirection - the enemies movement direction.
     */
    private void updateAimVector(int movementDirection) {
        switch (movementDirection) {
            case 0: // moving west
                aimVector.set(getPosition().getX() - range, getPosition().getY());
                break;
            case 1: // moving east
                aimVector.set(getPosition().getX() + range, getPosition().getY());
                break;
            case 2: // moving north
                aimVector.set(getPosition().getX(), getPosition().getY() + range);
                break;
            case 3: // moving south
                aimVector.set(getPosition().getX(), getPosition().getY() - range);
                break;
            default:
                break;
        }
    }

    /**
     * Checks if the player is in range. The enemy has a "vision range" of 300 pixels and a "vision span" of 300 degrees.
     *
     * @return <code>true</code> if player is in range, <code>false</code> otherwise.
     */
    public boolean isPlayerInRange() {
        if (Math.abs(playerDirectionVector.angle(aimVector)) < 150 &&
               playerDirectionVector.len() < 300) {
            return true;
        }
        return false;
    }


    private class MyActionListener implements ActionListener {
        @Override
        /**
         * Sets the enemy's movement direction randomly (out of four).
         */
        public void actionPerformed(ActionEvent e) {
            Random r = new Random();
            movementDirection = r.nextInt(4);
        }
    }

    /**
     * Is executed when the enemy runs out of health. Randomly creates new loot/drop for the player to pick up.
     *
     * @return
     */
    public ArrayList<Object> die() {
        Random r = new Random();
        if (player.getAllWeapons()!=null && player.getAllWeapons().size()>0) {
            WeaponInterface w = player.getAllWeapons().get(r.nextInt(player.getAllWeapons().size()));
            HashMap<String, ArrayList<? extends Object>> newLoot = lootStuff.generateLoot(getPosition(), w);
            pcs.firePropertyChange("PowerUp drops", null, newLoot.get("PowerUp Loot"));
            pcs.firePropertyChange("Ammunition drops", null, newLoot.get("Ammunition Loot"));
        }
        return loot;
    }

    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

}