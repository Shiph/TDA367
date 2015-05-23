package edu.chl.blastinthepast.view.projectileviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;

import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.view.WorldObject;

/**
 * Created by jonas on 2015-04-23.
 */
public abstract class ProjectileView implements WorldObject {
    private Texture texture;
    private Sprite sprite;
    private ProjectileInterface projectile;

    public ProjectileView(ProjectileInterface projectile, Texture texture, Sound sound){
        this.projectile=projectile;
        this.texture = texture;
        sprite = new Sprite(texture);
        sound.play(Constants.masterVolume);
        sprite.setX(projectile.getPosition().getX());
        sprite.setY(projectile.getPosition().getY());
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    private void updatePosition() {
        sprite.setPosition(projectile.getPosition().getX(), projectile.getPosition().getY());
    }

    private void setRotation(){
        sprite.setRotation(projectile.getAimVector().angle());
    }

    @Override
    public Object getObject() {
        return projectile;
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        setRotation();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void dispose() {
        texture.dispose();
    }


    public ProjectileInterface getProjectile(){
        return projectile;
    }
}
