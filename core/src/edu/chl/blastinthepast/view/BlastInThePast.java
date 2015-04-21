package edu.chl.blastinthepast.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlastInThePast extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		player = new Player();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		player.getSprite().setRotation(getAimDirection());
		player.getSprite().draw(batch);
		batch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			pcs.firePropertyChange("keyleft", true, false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			pcs.firePropertyChange("keyright", true, false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			pcs.firePropertyChange("keyup", true, false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			pcs.firePropertyChange("keydown", true, false);
		}
	}

	/**
	 * Calculates the angle from the player character to the mouse pointer.
	 *
	 * @return the angle.
	 */
	private float getAimDirection() {
		return (float)(-Math.atan2(Gdx.input.getY() - (player.getRectangle().y + player.getRectangle().height/2),
				Gdx.input.getX() - (player.getRectangle().x + player.getRectangle().width/2)) * (180/Math.PI)-90);
	}

	/**
	 * Adds a listener to the PropertyChangeSupport pcs.
	 *
	 * @param pcl - listener to be added.
	 */
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	/**
	 * Updates the position of the player character's rectangle and sprite.
	 *
	 * @param direction - the direction in which to move the sprite. 0 = left, 1 = right, 2 = up, 3 = down.
	 */
	public void updatePos(int direction) {
		switch (direction) {
			case 0: // move left
				player.getRectangle().x -= 200 * Gdx.graphics.getDeltaTime();
				player.getSprite().setX(player.getSprite().getX() - 200 * Gdx.graphics.getDeltaTime());
				break;
			case 1: // move right
				player.getRectangle().x += 200 * Gdx.graphics.getDeltaTime();
				player.getSprite().setX(player.getSprite().getX() + 200 * Gdx.graphics.getDeltaTime());
				break;
			case 2: // move up
				player.getRectangle().y -= 200 * Gdx.graphics.getDeltaTime();
				player.getSprite().setY(player.getSprite().getY() + 200 * Gdx.graphics.getDeltaTime());
				break;
			case 3: // move down
				player.getRectangle().y += 200 * Gdx.graphics.getDeltaTime();
				player.getSprite().setY(player.getSprite().getY() - 200 * Gdx.graphics.getDeltaTime());
				break;
		}
	}

}
