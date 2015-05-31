package edu.chl.blastinthepast.model.chest;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

/**
 * Created by Mattias on 15-05-05.
 */
public class Chest implements ChestInterface{
    private boolean isOpen = false;
    private WeaponInterface weapon;
    private final PositionInterface position;
    private int width = 64;
    private int height = 64;
    private Rectangle rectangle = new RectangleAdapter();

    public Chest(WeaponInterface weapon, PositionInterface position) {
        this.weapon = weapon;
        this.position = position;
        rectangle.setSize(width, height);
    }

    @Override
    public WeaponInterface open(CharacterI characterI) {
            isOpen = true;
            return weapon;
    }

    @Override
    public boolean isOpened() {
        return isOpen;
    }

    @Override
    public PositionInterface getPosition() {
        return position;
    }

    @Override
    public boolean isColliding(Collidable c){
        return rectangle.overlaps(c.getRectangle());
    }

    @Override
    public Rectangle getRectangle(){
        return rectangle;
    }

}
