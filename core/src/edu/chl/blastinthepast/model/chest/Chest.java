package edu.chl.blastinthepast.model.chest;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.weapon.WeaponInterface;
import edu.chl.blastinthepast.utils.PositionInterface;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

/**
 * Created by Mattias on 15-05-05.
 */
public class Chest implements ChestInterface{
    private boolean isOpen = false;
    private WeaponInterface weapon;
    private final PositionInterface position;
    private int width=64;
    private int height=64;
    private Rectangle rectangle = new RectangleAdapter();

    public Chest(WeaponInterface weapon) {
        this.weapon = weapon;
        position = weapon.getPosition();
        rectangle.setSize(width, height);
    }

    public WeaponInterface open(edu.chl.blastinthepast.model.player.Character character) {
            isOpen = true;
            return weapon;
    }

    public boolean isOpened() {
        return isOpen;
    }

    public PositionInterface getPosition() {
        return position;
    }

    public boolean isColliding(Collidable c){
        return rectangle.overlaps(c.getRectangle());
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}