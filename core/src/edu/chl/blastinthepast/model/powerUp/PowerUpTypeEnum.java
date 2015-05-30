package edu.chl.blastinthepast.model.powerUp;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public enum PowerUpTypeEnum implements PowerUpType {
    DAMAGE("Damage"), FIRERATE("Firerate"), HEALTH("Health"), MOVEMENTSPEED("Movement speed");

    private String type;

    PowerUpTypeEnum(String type) {
        this.type = type;
    }

    public String getID() {
        return type;
    }
}
