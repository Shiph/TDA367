package edu.chl.blastinthepast.model;

import java.util.ArrayList;

/**
 * Created by Mattias on 15-05-06.
 */
public interface InventoryInterface {

    public void addWeapon(Weapon weapon);

    public Weapon getWeapon (int index);

    public ArrayList<Weapon> getWeapons();

    public ArrayList<?> getAllItems();

}