package edu.chl.blastinthepast.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.blastinthepast.Enemy;
import edu.chl.blastinthepast.Player;
import edu.chl.blastinthepast.Projectile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;

public class BlastInThePast extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	private Enemy enemy;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private Array<Projectile> projectiles = new Array<Projectile>();
	private MyInputProcessor inputProcessor;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		player = new Player();
		enemy = new Enemy();
		inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
	}

	public void create(TiledMap tm) {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		player = new Player();
		for (int i=0; i<5; i++) {
			Enemy enemy = new Enemy();
			enemy.getRectangle().x = 100;
			enemy.getRectangle().y = 100;
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		player.getSprite().setRotation(getAimDirection());
		player.getSprite().draw(batch);
		for (Projectile p : projectiles) {
			p.getSprite().setRotation(p.getDirection());
			p.getSprite().draw(batch);
		}
		enemy.getSprite().draw(batch);
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
		Iterator<Projectile> iter = projectiles.iterator();
		while(iter.hasNext()) {
			Projectile p = iter.next();
			p.getRectangle().y += Math.cos(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime();
			p.getRectangle().x -= Math.sin(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime();
			p.getSprite().setY((float) (p.getSprite().getY() + Math.cos(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime()));
			p.getSprite().setX((float) (p.getSprite().getX() - Math.sin(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime()));
			if((p.getRectangle().y + 64 < 0) || (p.getRectangle().y > 480) || (p.getRectangle().x > 800) || (p.getRectangle().x + 64 < 0)) {
				iter.remove();
			}
		}
	}

	/**
	 * Spawns a projectile at the player's location.
	 */
	private void spawnProjectile() {
		projectiles.add(new Projectile(player.getRectangle().getX(), player.getRectangle().getY(), getAimDirection()));
	}

	/**
	 * Calculates the angle from the player character to the mouse pointer.
	 *
	 * @return the angle.
	 */
	private float getAimDirection() {
		return (float)(-Math.atan2(Gdx.input.getY() - (480-64-player.getRectangle().y + player.getRectangle().height/2),
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
	public void updatePlayerPos(int direction) {
		switch (direction) {
			case 0: // move left
				player.getRectangle().x -= player.getMovementSpeed() * Gdx.graphics.getDeltaTime();
				player.getSprite().setX(player.getSprite().getX() - player.getMovementSpeed() * Gdx.graphics.getDeltaTime());
				break;
			case 1: // move right
				player.getRectangle().x += player.getMovementSpeed() * Gdx.graphics.getDeltaTime();
				player.getSprite().setX(player.getSprite().getX() + player.getMovementSpeed() * Gdx.graphics.getDeltaTime());
				break;
			case 2: // move up
				player.getRectangle().y += player.getMovementSpeed() * Gdx.graphics.getDeltaTime();
				player.getSprite().setY(player.getSprite().getY() + player.getMovementSpeed() * Gdx.graphics.getDeltaTime());
				break;
			case 3: // move down
				player.getRectangle().y -= player.getMovementSpeed() * Gdx.graphics.getDeltaTime();
				player.getSprite().setY(player.getSprite().getY() - player.getMovementSpeed() * Gdx.graphics.getDeltaTime());
				break;
		}
	}

	private class MyInputProcessor extends InputAdapter {

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			spawnProjectile();
			return false;
		}

	}

}
