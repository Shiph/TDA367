package edu.chl.blastinthepast.model.ammunition;

import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;


/**
 * Created by jonas on 2015-05-13.
 */
public class Ammunition implements AmmunitionInterface{
    private PositionInterface position;
    private final ProjectileInterface type;
    private final int amount;
    private int size = 32;
    private Rectangle rectangle = new RectangleAdapter();

    public Ammunition(PositionInterface position, ProjectileInterface type, int amount){
        setPosition(position);
        this.type=type;
        this.amount=amount;
        rectangle.setSize(size);
    }

    @Override
    public PositionInterface getPosition(){
        return position;
    }

    @Override
    public void setPosition(PositionInterface newPosition) {
        this.position = newPosition;
        rectangle.setPosition(position);
    }

    public ProjectileInterface getType(){
        return type;
    }

    public int getAmount(){
        return amount;
    }

    public boolean isColliding(Collidable c){
        return rectangle.overlaps(c.getRectangle());
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
}
