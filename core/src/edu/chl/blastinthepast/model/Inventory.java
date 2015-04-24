package edu.chl.blastinthepast.model;

import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Weapon;

import java.util.LinkedList;

/**
 * Created by Mattias on 15-04-23.
 */
public class Inventory {

    LinkedList<Array> allItems= new LinkedList<Array>();
    Array<Weapon> weapons = new Array<Weapon>();

    public Inventory() {
        allItems.add(weapons);
    }

    /**
     * Adds a weapon to the inventory when picked up.
     * @param weapon
     */
    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    /**
     * Choose which weapon to equip from the inventory.
     * @param index
     * @return the selected weapon in the inventory.
     */
    public Weapon getWeapon (int index) {
        return weapons.get(index-1);
    }

    /**
     * Getter for the weapons in a players inventory.
     * @return all weapons in the inventory.
     */
    public Array<Weapon> getWeapons() {
        return weapons;
    }

    public LinkedList<Array> getAllItems() {
        return allItems;
    }

}