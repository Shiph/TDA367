package edu.chl.blastinthepast.model.powerUp;


import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.utils.PositionInterface;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

/**
 * Created by jonas on 2015-05-19.
 */
public abstract class PowerUp implements PowerUpI{
    private PositionInterface position;
    private long activationTime=0;
    private boolean hasExpired=false;
    private boolean firstUpdate=true;
    private final int size = 64;
    private Rectangle rectangle = new RectangleAdapter(size, size);
    protected int duration=10*1000;
    protected edu.chl.blastinthepast.model.player.Character character;

    public void init(Character character){
        this.character=character;
        activationTime=System.currentTimeMillis();
    }

    public void update(){
        if (System.currentTimeMillis() - activationTime < duration){
            applyPowerUp();
        } else if (firstUpdate){
            applyPowerUp();
            firstUpdate=false;
        } else {
            hasExpired=true;
        }
    }

    public abstract void applyPowerUp();


    public void setPosition(PositionInterface newPosition){
        position=newPosition;
        rectangle.setPosition(position);
    }

    public PositionInterface getPosition(){
        return position;
    }

    public boolean getHasExpired(){
        return hasExpired;
    }

    public boolean isColliding(Collidable c){
        return rectangle.overlaps(c.getRectangle());
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
}
