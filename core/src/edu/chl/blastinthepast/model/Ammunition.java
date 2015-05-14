package edu.chl.blastinthepast.model;

import edu.chl.blastinthepast.model.entities.ProjectileInterface;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;


/**
 * Created by jonas on 2015-05-13.
 */
public class Ammunition {
    private PositionInterface position;
    private ProjectileInterface type;
    private int amount;

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
