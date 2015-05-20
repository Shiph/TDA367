package edu.chl.blastinthepast.model.powerUp;

/**
 * Created by jonas on 2015-05-19.
 */
public class HealthPowerUp extends PowerUp {

    private int bonusHealth=2;

    public HealthPowerUp(){
        duration=0;
    }

    @Override
    public void applyPowerUp() {
        character.setHealth(character.getHealth()+bonusHealth);
    }
}
