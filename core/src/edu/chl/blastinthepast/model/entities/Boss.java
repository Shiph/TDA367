package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.Position;

/**
 * Created by Mattias on 15-05-07.
 */
public class Boss extends Enemy {

    public Boss(Player player, Position position) {
        this(200, 15, position, player);
    }

    public Boss(int movementSpeed, int health, Position position, Player player) {
        super(movementSpeed, health, position, player);
    }

}