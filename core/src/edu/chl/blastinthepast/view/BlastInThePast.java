package edu.chl.blastinthepast.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlastInThePast extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture bucketImage;
	private Sprite bucketSprite;
	private Rectangle bucket;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bucketImage = new Texture(Gdx.files.local("bucket.png"));
		bucketSprite = new Sprite(bucketImage);
		bucket = new Rectangle();
		bucket.x = 800/2 - 64/2;
		bucket.y = 480/2 - 64/2;
		bucket.height = 64;
		bucket.width = 64;
		bucketSprite.setX(bucket.x);
		bucketSprite.setY(bucket.y);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		bucketSprite.setRotation(getAimDirection());
		bucketSprite.draw(batch);
		batch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			pcs.firePropertyChange("keyleft", true, false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			pcs.firePropertyChange("keyright", true, false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			pcs.firePropertyChange("keyup", true, false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			pcs.firePropertyChange("keydown", true, false);
		}
	}

	private float getAimDirection() {
		return (float)(-Math.atan2(Gdx.input.getY() - (bucket.y + bucket.height/2), Gdx.input.getX() - (bucket.x + bucket.width/2)) * (180/Math.PI)-90);
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
	 * Updates the bucket's (and bucketSprite's) location.
	 *
	 * @param direction - the direction in which to move the sprite. 0 = left, 1 = right, 2 = up, 3 = down.
	 */
	public void updatePos(int direction) {
		switch (direction) {
			case 0: // move left
				bucket.x -= 200 * Gdx.graphics.getDeltaTime();
				bucketSprite.setX(bucketSprite.getX() - 200 * Gdx.graphics.getDeltaTime());
				break;
			case 1: // move right
				bucket.x += 200 * Gdx.graphics.getDeltaTime();
				bucketSprite.setX(bucketSprite.getX() + 200 * Gdx.graphics.getDeltaTime());
				break;
			case 2: // move up
				bucket.y -= 200 * Gdx.graphics.getDeltaTime();
				bucketSprite.setY(bucketSprite.getY() + 200 * Gdx.graphics.getDeltaTime());
				break;
			case 3: // move down
				bucket.y += 200 * Gdx.graphics.getDeltaTime();
				bucketSprite.setY(bucketSprite.getY() - 200 * Gdx.graphics.getDeltaTime());
				break;
		}
	}

}
