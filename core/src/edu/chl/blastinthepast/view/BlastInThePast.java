package edu.chl.blastinthepast.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.Enemy;
import edu.chl.blastinthepast.InputHandler;
import edu.chl.blastinthepast.Player;
import edu.chl.blastinthepast.Projectile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.Random;

public class BlastInThePast extends ApplicationAdapter implements PropertyChangeListener {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	private Array<Enemy> enemyArray;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private Array<Projectile> projectiles = new Array<Projectile>();
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private Sound wowSound;
	private Music gottaGoFaster;
	InputHandler inputHandler;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera= new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		tiledMap = new TmxMapLoader().load("GrassTestMap1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		player = new Player();
		gottaGoFaster = Gdx.audio.newMusic(Gdx.files.internal("sanic.mp3"));
		gottaGoFaster.setVolume(0.5f);
		gottaGoFaster.setLooping(true);
		gottaGoFaster.play();
		enemyArray = new Array<Enemy>();
		wowSound = Gdx.audio.newSound(Gdx.files.internal("wow.mp3"));
		for (int i = 0; i < 5; i++) {
			spawnEnemy();
		}
		for (Enemy e : enemyArray) {
			Random r = new Random();
			e.setX(r.nextFloat() * 800);
			e.setY(r.nextFloat() * 480);
		}
		inputHandler= new InputHandler();
		inputHandler.addListener(this);
		Gdx.input.setInputProcessor(inputHandler);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(player.getSprite().getX() + player.getSprite().getWidth() / 2, player.getSprite().getY() + player.getSprite().getWidth() / 2, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		inputHandler.checkForInput();
		batch.begin();
		player.getSprite().setRotation(getAimDirection());
		player.getSprite().draw(batch);
		for (Projectile p : projectiles) {
			p.getSprite().setRotation(p.getDirection());
			p.getSprite().draw(batch);
		}
		for (Enemy e : enemyArray) {
			e.getSprite().draw(batch);
		}
		batch.end();
		calculateProjectilePos();
	}

	private void calculateProjectilePos() {
		Iterator<Projectile> iter = projectiles.iterator();
		while(iter.hasNext()) {
			Projectile p = iter.next();
			p.getRectangle().y += Math.cos(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime();
			p.getRectangle().x -= Math.sin(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime();
			p.getSprite().setY((float) (p.getSprite().getY() + Math.cos(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime()));
			p.getSprite().setX((float) (p.getSprite().getX() - Math.sin(Math.toRadians(p.getDirection())) * p.getSpeed() * Gdx.graphics.getDeltaTime()));
			if((p.getRectangle().y + 64 < 0) || (p.getRectangle().y > 4000) || (p.getRectangle().x > 4000) || (p.getRectangle().x + 64 < 0)) {
				iter.remove();
			}
		}
	}

	public void spawnEnemy() {
		enemyArray.add(new Enemy());
	}

	/**
	 * Spawns a projectile at the player's location.
	 */
	public void spawnProjectile() {
		Projectile newProjectile = player.getWeapon().fire();
		newProjectile.setX(player.getRectangle().getX());
		newProjectile.setY(player.getRectangle().getY());
		newProjectile.setDirection(getAimDirection());
		projectiles.add(newProjectile);
		wowSound.play();
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		pcs.firePropertyChange(evt);
	}

}
