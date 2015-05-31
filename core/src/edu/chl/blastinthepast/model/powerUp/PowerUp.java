package edu.chl.blastinthepast.model.powerUp;


import edu.chl.blastinthepast.model.Collidable;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.position.PositionInterface;
import edu.chl.blastinthepast.utils.Rectangle;
import edu.chl.blastinthepast.utils.RectangleAdapter;

/**
 * Created by jonas on 2015-05-19.
 */
public abstract class PowerUp implements PowerUpI{
    private PositionInterface position;
    private long activationTime = 0;
    private boolean hasExpired = false;
    private boolean firstUpdate = true;
    private final int size = 64;
    private Rectangle rectangle = new RectangleAdapter(size, size);
    protected int duration = 10*1000;
    protected CharacterI characterI;

    @Override
    public void init(CharacterI characterI) {
        this.characterI = characterI;
        activationTime = System.currentTimeMillis();
    }

    /**
     * The power-up will always be applied the first time the update method is called. This is for allowing
     * permanent changes to a character.
     */
    @Override
    public void update(){
        if (System.currentTimeMillis() - activationTime < duration){
            applyPowerUp();
        } else if (firstUpdate){
            applyPowerUp();
            firstUpdate = false;
        } else {
            hasExpired = true;
        }
    }

    @Override
    public abstract void applyPowerUp();

    @Override
    public void setPosition(PositionInterface newPosition){
        position=newPosition;
        rectangle.setPosition(position.getX(), position.getY());
    }

    @Override
    public PositionInterface getPosition(){
        return position;
    }

    @Override
    public boolean getHasExpired(){
        return hasExpired;
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
