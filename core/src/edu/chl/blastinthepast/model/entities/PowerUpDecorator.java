package edu.chl.blastinthepast.model.entities;

import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.PositionInterface;

/**
 * Created by jonas on 2015-05-17.
 *
 * The PowerUpDecorator is meant to facilitate the creation of new power ups. By extending the PowerUpDecorator
 * all that is left to do is to implement the applyPowerUp method and optionally assign the duration variable a new value.
 * The linked list is needed to call on the previous powerUps update method in the chain and to remove the power up object
 * once the timer has expired without breaking the chain.
 */
public abstract class PowerUpDecorator extends Player{
    //protected Player previousPlayer;
    protected int duration=1*1000;
    protected long activationTime;
    protected PositionInterface position;
    protected boolean toBeRemoved=false;

    /**
     * The init method exists so that it is possible to create a power up without assigning it to a player,
     * it is doubly useful since the power up is time-limited and the call for the init method marks a clear beginning
     * for the countdown to begin.
     * @param player
     */
    public Player init(Player player){
        previousPlayer=player;
        /*if (player instanceof PowerUpDecorator){
            PowerUpDecorator powerUp = (PowerUpDecorator) player;
            powerUp.setNextPowerUp(this);
        }*/
        previousPlayer.setNextPowerUp(this);
        activationTime=System.currentTimeMillis();
        return this;
    }

    public abstract void applyPowerUp();

    public Player removeFromChain(){
        if (getNextPowerUp()!=null) {
            getNextPowerUp().previousPlayer = previousPlayer;
            previousPlayer.setNextPowerUp(getNextPowerUp());
        } else {
            previousPlayer.setNextPowerUp(null);
        }
        return previousPlayer;
    }

    /**
     * The modified traits will be applied every update call until the power up expires at which point the power up
     * removes itself from the possible chain of power ups.
     * @param dt
     */
    @Override
    public void update(float dt){
        if (System.currentTimeMillis() - activationTime < duration) {
            applyPowerUp();
        } else {
            toBeRemoved=true;
        }
        previousPlayer.update(dt);
    }

    public void setPosition(PositionInterface newPosition){
        position=newPosition;
    }

    public PositionInterface getPosition(){
        return new Position(position);
    }

}
