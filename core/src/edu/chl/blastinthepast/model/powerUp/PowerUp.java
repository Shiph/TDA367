package edu.chl.blastinthepast.model.powerUp;


import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-19.
 */
public abstract class PowerUp implements PowerUpI{
    private PositionInterface position;
    private long activationTime=0;
    private boolean hasExpired=false;
    private boolean firstUpdate=true;
    protected int duration=10*1000;
    protected edu.chl.blastinthepast.model.player.Character character;

    public void init(Character character){
        this.character=character;
    }

    @Override
    public void update(){
        if (System.currentTimeMillis() - activationTime < duration){
            applyPowerUp();
        } else if (firstUpdate){
            applyPowerUp();
            firstUpdate=false;
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
