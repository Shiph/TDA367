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
import edu.chl.blastinthepast.*;
import edu.chl.blastinthepast.controller.InputHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.Random;

public class BPView extends ApplicationAdapter implements PropertyChangeListener {

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
	private GameStateManager gsm;
	private InputHandler inputHandler;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera= new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		tiledMap = new TmxMapLoader().load("GrassTestMap1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
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
		gsm = new GameStateManager();
		if (gsm.getGameState() instanceof PlayState) {
			player = ((PlayState)gsm.getGameState()).getPlayer();
		} else {
			System.out.println("Can't create player, state is not in PlayState");
		}

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.set(player.getRectangle().getX() + player.getRectangle().getWidth() / 2, player.getRectangle().getY() + player.getRectangle().getWidth() / 2, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		inputHandler.checkForInput();
		batch.begin();
		for (Projectile p : projectiles) {
			p.getSprite().setRotation(p.getDirection());
			p.getSprite().draw(batch);
		}
		for (Enemy e : enemyArray) {
			e.getSprite().draw(batch);
		}
		batch.end();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
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
			if((p.getRectangle().y + player.getSprite().getHeight() < 0) || (p.getRectangle().y > Constants.MAP_HEIGHT) ||
					(p.getRectangle().x > Constants.MAP_WIDTH) || (p.getRectangle().x + player.getSprite().getWidth() < 0)) {
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		pcs.firePropertyChange(evt);
	}

	public GameStateManager getGameStateController() {
		return gsm;
	}

}
