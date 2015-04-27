package edu.chl.blastinthepast.model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Mattias on 15-04-23.
 */
public class Inventory {

    ArrayList<ArrayList> allItems = new ArrayList<ArrayList>();
    ArrayList<Weapon> weapons = new ArrayList<Weapon>();

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
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<?> getAllItems() {
        return allItems;
    }

}