package edu.chl.blastinthepast.model.ammunition;

import edu.chl.blastinthepast.model.projectile.ProjectileInterface;
import edu.chl.blastinthepast.utils.PositionInterface;


/**
 * Created by jonas on 2015-05-13.
 */
public class Ammunition implements AmmunitionInterface{
    private PositionInterface position;
    private final ProjectileInterface type;
    private final int amount;

    public Ammunition(PositionInterface position, ProjectileInterface type, int amount){
        this.position=position;
        this.type=type;
        this.amount=amount;
    }

    //@Override
    public PositionInterface getPosition(){
        return position;
    }

    //@Override
    public void setPosition(PositionInterface position) {
        this.position.setPosition(position);
    }

    public ProjectileInterface getType(){
        return type;
    }

    public int getAmount(){
        return amount;
    }
}
