package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import edu.chl.blastinthepast.model.GameState;
import edu.chl.blastinthepast.controller.GameStateManager;
import edu.chl.blastinthepast.model.BPModel;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.model.Projectile;
import edu.chl.blastinthepast.utils.Position;

import java.util.ArrayList;


/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState {

    private BPModel model;
    private PlayerView playerView;
    private ArrayList<EnemyView> enemies;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private ArrayList<ProjectileView> projectiles = new ArrayList<ProjectileView>();
    private Sound wowSound;
    private Music music;

    public PlayState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
    }

    @Override
    public void init(BPModel model) {
        this.model=model;
        playerView = new PlayerView(model.getPlayer());
        enemies = new ArrayList<EnemyView>();
        batch = new SpriteBatch();
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        tiledMap = new TmxMapLoader().load("GrassTestMap1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        wowSound = Gdx.audio.newSound(Gdx.files.internal("wow.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sanic.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        addEnemies();
    }

    private void addEnemies(){
        ArrayList<Enemy> enemies=model.getEnemies();
        for (Enemy e : enemies) {
            this.enemies.add(new EnemyView(e));
        }
    }

    private void addProjectiles(){
        for (ProjectileView p : projectiles) {
            p.dispose();
        }
        projectiles.clear();
        ArrayList<Projectile> projectileArray = model.getProjectiles();
        for (Projectile p: projectileArray){
            projectiles.add(new ProjectileView(p));
        }
    }

    @Override
    public void update(float dt) {
        model.update(dt);
        camera.position.set(playerView.getRectangles().get(0).getX() + playerView.getRectangles().get(0).getWidth() / 2, playerView.getRectangles().get(0).getY() + playerView.getRectangles().get(0).getWidth() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
        addProjectiles();
        //music.play();
        for (EnemyView e : enemies) {
            e.update();
        }
    }

    @Override
    public void draw() {
        tiledMapRenderer.render();
        playerView.draw(batch);
        for (EnemyView e : enemies) {
            e.draw(batch);
        }
        for (ProjectileView p : projectiles) {
            p.draw(batch);
        }
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void dispose() {
        music.pause();
        playerView.dispose();
        for (ProjectileView p : projectiles) {
            p.dispose();
        }
        for (EnemyView e : enemies) {
            e.dispose();
        }
    }

    public PlayerView getPlayer() {
        return playerView;
    }

    private float getAimDirection() {
        return (float)(-Math.atan2(Gdx.input.getY() - (480-64-playerView.getRectangles().get(0).y + playerView.getRectangles().get(0).height/2),
                Gdx.input.getX() - (playerView.getRectangles().get(0).x + playerView.getRectangles().get(0).width/2)) * (180/Math.PI)-90);
    }

    public Position screenToWorldCoordinates(Position screenCoordinates){
        Vector3 screenCordinatesVector=new Vector3(screenCoordinates.getX(), screenCoordinates.getY(), 0);
        Vector3 worldCoordinatesVector=camera.unproject(screenCordinatesVector);
        Position worldCoordinates=new Position(worldCoordinatesVector.x, worldCoordinatesVector.y);
        return worldCoordinates;
    }

}
