package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import edu.chl.blastinthepast.model.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.model.Projectile;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.collisiondetection.CollisionDetection;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.SoundAssets;

import java.awt.event.ContainerAdapter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState{

    private BPModel model;
    private PlayerView playerView;
    private BossView bossView;
    private ArrayList<EnemyView> enemies;
    private ChestView chestView;
    private CollisionView collisionView;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private ArrayList<ProjectileView> projectiles = new ArrayList<ProjectileView>();
    //private ArrayList<PowerUpView> powerUps= new ArrayList<PowerUpView>();
    private Sound wowSound;
    private Music music;
    private PropertyChangeSupport pcs;
    private Label label;
    private Label.LabelStyle labelStyle;
    private BitmapFont font;

    public PlayState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
    }

    @Override
    public void init(BPModel model) {
        this.model=model;
        chestView = new ChestView();
        collisionView = new CollisionView();
        playerView = new PlayerView(model.getPlayer());
        bossView = new BossView(model.getBoss());
        enemies = new ArrayList<EnemyView>();
        batch = new SpriteBatch();
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        tiledMap = new TmxMapLoader().load("GrassTestMap1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        wowSound = SoundAssets.WOW_SOUND;
        music = SoundAssets.SANIC_THEME;
        music.setVolume(0.2f);
        music.setLooping(true);
        font = new BitmapFont();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        label = new Label("ammo", labelStyle);
        pcs=new PropertyChangeSupport(this);
        music.play();
        addEnemies();
    }

    private void addEnemies(){
        enemies.clear();
        ArrayList<Enemy> enemyArrayList=model.getEnemies();
        for (Enemy e : enemyArrayList) {
            enemies.add(new EnemyView(e));
        }
        enemies.add(bossView);
    }

    private void addProjectiles(){
        projectiles.clear();
        ArrayList<ProjectileInterface> projectileArray = model.getProjectiles();
        for (ProjectileInterface p: projectileArray){
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
        //music.play();
        /*for (EnemyView e : enemies) {
            e.update();
        }*/
        bossView.update();
        new CollisionDetection(enemies, playerView, projectiles, chestView, collisionView);
        if(!music.isPlaying()) {
            music.play();
        }
        label.setPosition(camera.position.x - Constants.CAMERA_WIDTH/2 + 10, camera.position.y - Constants.CAMERA_HEIGHT/2 + 10);
        label.setText(model.getPlayer().getWeapon().getTotalBullets() + "/" + model.getPlayer().getWeapon().getbulletsLeftInMagazine());
    }

    @Override
    public void draw() {
        addProjectiles();
        addEnemies();
        tiledMapRenderer.render();
        playerView.draw(batch);
        bossView.draw(batch);
        for (EnemyView e : enemies) {
            e.draw(batch);
        }
        for (ProjectileView p : projectiles) {
            p.draw(batch);
        }
        checkIfHit();
        batch.begin();
        label.draw(batch, 1);
        batch.end();
    }

    public void checkIfHit(){
        for (ProjectileView p: projectiles){
            for (EnemyView e: enemies){
                if (e.getRectangles().get(0).overlaps(p.getRectangles().get(0))){
                    pcs.firePropertyChange("characterHit", p.getProjectile(), e.getCharacter());
                }

            }
            if (playerView.getRectangles().get(0).overlaps(p.getRectangles().get(0))){
                pcs.firePropertyChange("characterHit", p.getProjectile(), playerView.getCharacter());
            }
        }
    }

    @Override
    public void handleInput() {}

    @Override
    public void dispose() {
        music.pause();
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

    public boolean addListener(PropertyChangeListener pcl) {
        for (int i=0; i<pcs.getPropertyChangeListeners().length; i++){
            if (pcs.getPropertyChangeListeners()[i]==pcl){
                return false;
            }
        }
        pcs.addPropertyChangeListener(pcl);
        return true;
    }


}
