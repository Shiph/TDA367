package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.*;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState {

    private Player player;
    private Array<Enemy> enemyArray;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private Array<Projectile> projectiles = new Array<Projectile>();
    private Sound wowSound;
    private Music gottaGoFaster;

    public PlayState(GameStateManager gsc) {
        super(gsc);
    }

    @Override
    public void init() {
        player = new Player();
        enemyArray = new Array<Enemy>();
        spawnEnemies();
        for (Enemy e : enemyArray) {
            Random r = new Random();
            e.setX(r.nextFloat() * 800);
            e.setY(r.nextFloat() * 480);
        }
        batch = new SpriteBatch();
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        tiledMap = new TmxMapLoader().load("GrassTestMap1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        wowSound = Gdx.audio.newSound(Gdx.files.internal("wow.mp3"));
        gottaGoFaster = Gdx.audio.newMusic(Gdx.files.internal("sanic.mp3"));
        gottaGoFaster.setVolume(0.5f);
        gottaGoFaster.setLooping(true);
        gottaGoFaster.play();
    }

    private void spawnEnemies() {
        for (int i = 0; i < 5; i++) {
            enemyArray.add(new Enemy());
        }
    }

    @Override
    public void update(float dt) {
        camera.position.set(player.getRectangle().getX() + player.getRectangle().getWidth() / 2, player.getRectangle().getY() + player.getRectangle().getWidth() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
        calculateProjectilePos();
    }

    @Override
    public void draw() {
        tiledMapRenderer.render();
        player.draw(batch);
        for (Enemy e : enemyArray) {
            e.draw(batch);
        }
        for (Projectile p : projectiles) {
            p.draw(batch);
        }
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void dispose() {

    }

    public Player getPlayer() {
        return player;
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

    public void spawnProjectile() {
        if (player.getWeapon().hasAmmo()) {
            Projectile newProjectile = player.getWeapon().fire();
            newProjectile.setX(player.getRectangle().getX());
            newProjectile.setY(player.getRectangle().getY());
            newProjectile.setDirection(getAimDirection());
            projectiles.add(newProjectile);
            wowSound.play();
        }
    }

    private float getAimDirection() {
        return (float)(-Math.atan2(Gdx.input.getY() - (480-64-player.getRectangle().y + player.getRectangle().height/2),
                Gdx.input.getX() - (player.getRectangle().x + player.getRectangle().width/2)) * (180/Math.PI)-90);
    }

}
