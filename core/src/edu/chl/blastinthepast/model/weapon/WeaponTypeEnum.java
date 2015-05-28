package edu.chl.blastinthepast.model.weapon;

/**
 * Created by Mattias on 15-05-28.
 */
public enum WeaponTypeEnum implements WeaponType {
    AK47("AK47"), MAGNUM("Magnum");

    private String type;

    WeaponTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String getID() {
        return type;
    }

}