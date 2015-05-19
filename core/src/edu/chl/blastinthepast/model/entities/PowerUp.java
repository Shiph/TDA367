package edu.chl.blastinthepast.model.entities;


import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-19.
 */
public abstract class PowerUp implements PowerUpI{
    private PositionInterface position;
    private long activationTime=0;
    private boolean hasExpired=false;
    protected int duration=10*1000;
    protected Character character;

    public void init(Character character){
        this.character=character;
        activationTime=System.currentTimeMillis();
    }

    @Override
    public void update(){
        if (System.currentTimeMillis() - activationTime < duration){
            applyPowerUp();
        } else {
            hasExpired=true;
        }
    }

    public abstract void applyPowerUp();


    public void setPosition(PositionInterface newPosition){
        position=newPosition;
    }

    public PositionInterface getPosition(){
        return position;
    }

    @Override
    public boolean getHasExpired(){
        return hasExpired;
    }
}
