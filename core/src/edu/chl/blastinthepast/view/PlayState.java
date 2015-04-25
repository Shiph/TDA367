package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.controller.GameState;
import edu.chl.blastinthepast.model.BPModel;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.model.Projectile;


/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState {

    private BPModel model;
    private PlayerView playerView;
    private Array<EnemyView> enemyArray;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private Array<ProjectileView> projectiles = new Array<ProjectileView>();
    private Sound wowSound;
    private Music gottaGoFaster;

    public PlayState(GameStateManager gsc, BPModel model) {
        super(gsc, model);
    }

    @Override
    public void init(BPModel model) {
        this.model=model;
        playerView = new PlayerView(model.getPlayer());
        enemyArray = new Array<EnemyView>();
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

    private void addEnemies(){
        Array<Enemy> enemies=model.getEnemies();
        for (Enemy e : enemies) {
            enemyArray.add(new EnemyView(e));
        }
    }

    private void addProjectiles(){
        Array<Projectile> projectileArray=model.getProjectiles();
        for (Projectile p: projectileArray){
            projectiles.add(new ProjectileView(p));
        }
    }

    @Override
    public void update(float dt) {
        model.update();
        camera.position.set(playerView.getRectangle().getX() + playerView.getRectangle().getWidth() / 2, playerView.getRectangle().getY() + playerView.getRectangle().getWidth() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.setView(camera);
    }

    @Override
    public void draw() {
        tiledMapRenderer.render();
        playerView.draw(batch);
        for (EnemyView e : enemyArray) {
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

    }

    public PlayerView getPlayer() {
        return playerView;
    }

    private float getAimDirection() {
        return (float)(-Math.atan2(Gdx.input.getY() - (480-64-playerView.getRectangle().y + playerView.getRectangle().height/2),
                Gdx.input.getX() - (playerView.getRectangle().x + playerView.getRectangle().width/2)) * (180/Math.PI)-90);
    }

}
