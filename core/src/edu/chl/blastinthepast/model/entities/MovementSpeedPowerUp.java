package edu.chl.blastinthepast.model.entities;

/**
 * Created by Mattias on 15-04-23.
 */
public class MovementSpeedPowerUp extends PowerUp {
    private int bonus=40;

    @Override
    public void applyPowerUp() {
        System.out.println("Maximum speed");
        character.addBonusMovementSpeed(bonus);
    }

    @Override
    public boolean equals(Object obj){
        if (obj!=null){
            if (obj instanceof MovementSpeedPowerUp){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 17;
    }

}