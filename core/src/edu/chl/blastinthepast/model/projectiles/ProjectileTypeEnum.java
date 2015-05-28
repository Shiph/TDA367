package edu.chl.blastinthepast.model.projectiles;

/**
 * Created by Mattias on 15-05-28.
 */
public enum ProjectileTypeEnum implements ProjectileType {
    AK47("AK47"), MAGNUM("Magnum");

    String type;

    ProjectileTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String getID() {
        return type;
    }
}
