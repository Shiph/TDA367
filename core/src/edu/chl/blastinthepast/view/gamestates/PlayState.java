package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import edu.chl.blastinthepast.model.Ammunition;
import edu.chl.blastinthepast.model.entities.*;
import edu.chl.blastinthepast.model.entities.Character;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.GraphicalAssets;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.SoundAssets;
import edu.chl.blastinthepast.view.*;
import edu.chl.blastinthepast.view.characterviews.BossView;
import edu.chl.blastinthepast.view.characterviews.EnemyView;
import edu.chl.blastinthepast.view.characterviews.PlayerView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;


/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState implements Observer{

    private BPModel model;
    private PlayerView playerView;
    private ChestView chestView;
    private CollisionView collisionView;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private Sound wowSound;
    private Music music;
    private PropertyChangeSupport pcs;
    private Label ammoLabel;
    private Label.LabelStyle labelStyle;
    private BitmapFont font;
    private Image weaponImage;
    private HashMap <Object, WorldObject> worldObjects;
    private ArrayList <Object> worldObjectsRemoveList;

    public PlayState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
    }

    @Override
    public void init(BPModel model) {
        this.model = model;
        chestView = new ChestView(model.getChest());
        model.addObserver(this);
        worldObjects = new HashMap <Object, WorldObject>();
        worldObjectsRemoveList = new ArrayList<Object>();
        chestView = new ChestView(model.getChest());
        collisionView = new CollisionView();
        playerView = new PlayerView(model.getPlayer());
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
        ammoLabel = new Label("ammo", labelStyle);
        weaponImage = new Image(playerView.getWeaponView().getTexture());
        pcs=new PropertyChangeSupport(this);
        music.play();
        for (Character c : model.getCharacters()){
            if (c instanceof Player) {
                Player p = (Player) c;
                playerView = new PlayerView(p);
                worldObjects.put(p, playerView);
            } else if (c instanceof Boss) {
                Boss b = (Boss) c;
                worldObjects.put(b, new BossView(b));
            } else if (c instanceof Enemy){
                Enemy e= (Enemy)c;
                worldObjects.put(e, new EnemyView(e));
            }
        }
    }


    @Override
    public void update(float dt) {
        if(!model.isPaused()) {
            chestView.update();
            camera.position.set(playerView.getRectangles().get(0).getX() + playerView.getRectangles().get(0).getWidth() / 2, playerView.getRectangles().get(0).getY() + playerView.getRectangles().get(0).getWidth() / 2, 0);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            tiledMapRenderer.setView(camera);
            //new CollisionDetection(enemies, playerView, projectiles, chestView, collisionView);
            if (!music.isPlaying()) {
                music.play();
            }
            ammoLabel.setPosition(camera.position.x - Constants.CAMERA_WIDTH / 2 + 10, camera.position.y - Constants.CAMERA_HEIGHT / 2 + 10);
            weaponImage.setPosition(ammoLabel.getX(), ammoLabel.getY() + ammoLabel.getHeight());
            ammoLabel.setText(model.getPlayer().getWeapon().getTotalBullets() + "/" + model.getPlayer().getWeapon().getbulletsLeftInMagazine());
        }
    }

    @Override
    public void draw() {
        tiledMapRenderer.render();

        chestView.draw(batch);
        for (WorldObject o : worldObjects.values()){
            o.draw(batch);
        }

        checkForCollision();

        if (!worldObjectsRemoveList.isEmpty()) {
            removeObjects();
        }

        batch.begin();
        ammoLabel.draw(batch, 1);
        weaponImage.draw(batch, 1);
        batch.end();
    }

    public void checkForCollision(){
        for (WorldObject o1 : worldObjects.values()){
            for (WorldObject o2 : worldObjects.values()){
                if (o1.getRectangle().overlaps(o2.getRectangle()) && o1!=o2){
                    pcs.firePropertyChange("Collision", o1.getObject(), o2.getObject());
                }
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ProjectileInterface){
            if (worldObjects.containsKey(arg)) {
                if (!worldObjectsRemoveList.contains(arg)) {
                    worldObjectsRemoveList.add(arg);
                }
            } else {
                if (arg instanceof AK47Projectile) {
                    worldObjects.put(arg, new AK47ProjectileView((ProjectileInterface) arg));
                } else if (arg instanceof MagnumProjectile) {
                    worldObjects.put(arg, new MagnumProjectileView((ProjectileInterface) arg));
                }
            }
        }
        if (arg instanceof Character){
            if (worldObjects.containsKey(arg)) {
                if (!worldObjectsRemoveList.contains(arg)) {
                    worldObjectsRemoveList.add(arg);
                }
            } else if (arg instanceof Boss){
                worldObjects.put(arg, new BossView((Boss)arg));
            } else if (arg instanceof Enemy){
                worldObjects.put(arg, new EnemyView((Enemy)arg));
            }  else if (arg instanceof Player){
                worldObjects.put(arg, new PlayerView((Player)arg));
            }
        }
        if (arg instanceof Ammunition) {
            if (worldObjects.containsKey(arg)) {
                if (!worldObjectsRemoveList.contains(arg)){
                    worldObjectsRemoveList.add(arg);
                }
            } else {
                Ammunition ammo = (Ammunition) arg;
                if (ammo.getType() instanceof AK47Projectile) {
                    worldObjects.put(ammo, new AmmunitionView(ammo, GraphicalAssets.TRIFORCE_BULLET));
                }
            }
        }
        if (arg instanceof String) {
            if (arg.equals("paused")) {
                music.pause();
            } else if (arg.equals("unpaused")) {
                music.play();
            }
        }
    }

    public void removeObjects(){
        Iterator<Object> objectIterator = worldObjectsRemoveList.iterator();

        while (objectIterator.hasNext()){
            Object o = objectIterator.next();
            worldObjects.remove(o);
            objectIterator.remove();
        }
    }

    public void toggleSound() {
        if (music.getVolume() == 0) {
            music.setVolume(0.2f);
        } else {
            music.setVolume(0);
        }
    }

    public void updateGUIWeapon() {
        weaponImage = new Image(playerView.getWeaponView().getTexture());
    }

}