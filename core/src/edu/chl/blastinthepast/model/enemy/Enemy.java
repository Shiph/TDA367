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
            updateMovementDirectionVector(movementDirection);
        }

    }

    private void updateMovementDirectionVector(int movementDirection) {
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

    public boolean isPlayerInRange() {
        if (Math.abs(playerDirectionVector.angle(aimVector)) < 150 &&
               playerDirectionVector.len() < 300) {
            return true;
        }
        return false;
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random r = new Random();
            movementDirection = r.nextInt(4);
        }
    }

    public ArrayList<Object> die() {
        HashMap<String, ArrayList<? extends Object>> newLoot = lootStuff.generateLoot(getPosition(), getWeapon());
        pcs.firePropertyChange("PowerUp drops", null, newLoot.get("PowerUp Loot"));
        pcs.firePropertyChange("Ammunition drops", null, newLoot.get("Ammunition Loot"));
        return loot;
    }

    public void addListener(PropertyChangeListener pcl){
        pcs.addPropertyChangeListener(pcl);
    }

    public void removeListener(PropertyChangeListener pcl){
        pcs.removePropertyChangeListener(pcl);
    }

}