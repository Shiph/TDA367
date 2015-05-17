package edu.chl.blastinthepast.model.entities;

/**
 * Created by jonas on 2015-05-17.
 */
public abstract class PowerUpDecorator extends Player{
    protected Player previousPlayer;
    protected PowerUpDecorator nextPowerUp;
    protected int duration;
    protected long activationTime;

    public void init(Player player){
        this.previousPlayer=player;
        if (player instanceof PowerUpDecorator){
            PowerUpDecorator powerUp = (PowerUpDecorator) player;
            powerUp.nextPowerUp=this;
        }
        activationTime=System.currentTimeMillis();
    }

    public abstract void applyPowerUp();

    public void removeFromList(){
        this.nextPowerUp.previousPlayer=previousPlayer;
        this.previousPlayer.setNextPowerUp(nextPowerUp);
    }

    @Override
    public void update(float dt){
        if (System.currentTimeMillis() - activationTime < duration*1000) {
            applyPowerUp();
            previousPlayer.update(dt);
        } else {
            removeFromList();
        }
    }

}
