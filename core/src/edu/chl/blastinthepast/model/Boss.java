package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Mattias on 15-05-07.
 */
public class Boss extends Enemy {

    public Boss(Player player) {
        this(200, 1500, player);
    }

    public Boss(int movementSpeed, int health, Player player) {
        super(movementSpeed, health, player);
    }

}