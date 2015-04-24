package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.Enemy;
import edu.chl.blastinthepast.GameState;
import edu.chl.blastinthepast.Player;

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
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void draw() {
        player.draw(batch);
        for (Enemy e : enemyArray) {
            e.draw(batch);
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

}
