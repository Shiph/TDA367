package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private Music music;
    private PropertyChangeSupport pcs;
    private Label ammoLabel;
    private Label.LabelStyle labelStyle;
    private BitmapFont font;
    private Image weaponImage;
    private HashMap <Object, WorldObject> worldObjects;
    private ArrayList <Object> worldObjectsRemoveList;
    private ArrayList<Image> heartIcons;

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
        playerView = new PlayerView(model.getPlayer());
        batch = new SpriteBatch();
        camera= new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        camera.position.set(Constants.MAP_WIDTH / 2, Constants.MAP_HEIGHT / 2, 0);
        camera.update();
        tiledMap = new TmxMapLoader().load("big_grass.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        music = SoundAssets.SANIC_THEME;
        music.setVolume(Constants.masterVolume);
        music.setLooping(true);
        font = new BitmapFont();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        ammoLabel = new Label("ammo", labelStyle);
        weaponImage = new Image(playerView.getWeaponView().getTexture());

        heartIcons = new ArrayList<>(model.getPlayer().getHealth());

        for (int i=0; i<model.getPlayer().getHealth(); i++) {
            Texture heartTexture = GraphicalAssets.HEART;
            heartIcons.add(new Image(heartTexture));
        }
        updateHeartPositions();

        pcs=new PropertyChangeSupport(this);
        music.stop();
        music.play();
        setCrosshairCursor();
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
            ammoLabel.setPosition(camera.position.x - Constants.CAMERA_WIDTH / 2 + 10, camera.position.y - Constants.CAMERA_HEIGHT / 2 + 10);
            weaponImage.setPosition(ammoLabel.getX(), ammoLabel.getY() + ammoLabel.getHeight());
            ammoLabel.setText(model.getPlayer().getWeapon().getTotalBullets() + "/" + model.getPlayer().getWeapon().getbulletsLeftInMagazine());

            if (model.getPlayer().getHealth() < heartIcons.size()) {
                for (int i=0; i < heartIcons.size()-model.getPlayer().getHealth(); i++) {
                    heartIcons.remove(heartIcons.size()-i-1);
                }
            } else if (model.getPlayer().getHealth() > heartIcons.size()) {
                for (int i=0; i<model.getPlayer().getHealth()-heartIcons.size(); i++) {
                    heartIcons.add(new Image(GraphicalAssets.HEART));
                }
            }
            updateHeartPositions();
            if (playerView.getRectangles().get(0).getX() + playerView.getRectangles().get(0).getWidth() / 2 - Constants.CAMERA_WIDTH/2 > 0 &&
                    playerView.getRectangles().get(0).getX() + playerView.getRectangles().get(0).getWidth() / 2 + Constants.CAMERA_WIDTH/2 < Constants.MAP_WIDTH) {
                camera.position.x = playerView.getRectangles().get(0).getX() + playerView.getRectangles().get(0).getWidth() / 2;
            }
            if (playerView.getRectangles().get(0).getY() + playerView.getRectangles().get(0).getWidth() / 2 + Constants.CAMERA_HEIGHT/2 < Constants.MAP_HEIGHT &&
                    playerView.getRectangles().get(0).getY() + playerView.getRectangles().get(0).getWidth() / 2 - Constants.CAMERA_HEIGHT/2 > 0) {
                camera.position.y = playerView.getRectangles().get(0).getY() + playerView.getRectangles().get(0).getWidth() / 2;
            }
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            tiledMapRenderer.setView(camera);
            if (!music.isPlaying()) {
                music.play();
            }
        }
    }

    private void updateHeartPositions() {
        heartIcons.get(0).setPosition(camera.position.x - Constants.CAMERA_WIDTH/2 + 15, camera.position.y + Constants.CAMERA_HEIGHT/2 - 80);
        for (int i=1; i<heartIcons.size(); i++) {
            heartIcons.get(i).setPosition(heartIcons.get(i - 1).getX() + 40, heartIcons.get(i - 1).getY());
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
        for (Image i : heartIcons) {
            i.draw(batch, 1);
            i.setSize(32, 32);
        }
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
        if (worldObjects.containsKey(arg)) {
            if (!worldObjectsRemoveList.contains(arg)) {
                worldObjectsRemoveList.add(arg);
            }
        } else {
            checkIfProjectile(o, arg);
            checkIfCharacter(o, arg);
            checkIfAmmunition(o, arg);
            checkIfPowerUp(o, arg);
        }

        if (arg instanceof String) {
            if (arg.equals("paused")) {
                music.pause();
            } else if (arg.equals("unpaused")) {
                music.play();
            }
        }
    }

    /**
     *  Since removing objects without using the iterator while iterating through a list is met with an error
     *  it is necessary to mark them for removal and remove when the iteration is complete.
     */
    public void removeObjects(){
        Iterator<Object> objectIterator = worldObjectsRemoveList.iterator();

        while (objectIterator.hasNext()){
            Object o = objectIterator.next();
            worldObjects.remove(o);
            objectIterator.remove();
        }
    }

    public void toggleSound() {
        if (Constants.masterVolume == 0) {
            Constants.masterVolume = 0.2f;
        } else {
            Constants.masterVolume = 0;

        }
        music.setVolume(Constants.masterVolume);
    }

    public void updateGUIWeapon() {
        weaponImage = new Image(playerView.getWeaponView().getTexture());
    }

    public void setCrosshairCursor() {
        Gdx.input.setCursorImage(GraphicalAssets.CROSSHAIR, GraphicalAssets.CROSSHAIR.getWidth() / 2, GraphicalAssets.CROSSHAIR.getHeight() / 2);
    }

    public void checkIfProjectile(Observable o, Object arg){
        if (arg instanceof  ProjectileInterface){
            if (arg instanceof AK47Projectile) {
                    worldObjects.put(arg, new AK47ProjectileView((ProjectileInterface) arg));
                } else if (arg instanceof MagnumProjectile) {
                    worldObjects.put(arg, new MagnumProjectileView((ProjectileInterface) arg));
                }
        }

    }

    public void checkIfCharacter(Observable o, Object arg){
        if (arg instanceof Character){
            if (arg instanceof Boss){
                worldObjects.put(arg, new BossView((Boss)arg));
            } else if (arg instanceof Enemy){
                worldObjects.put(arg, new EnemyView((Enemy)arg));
            }  else if (arg instanceof Player){
                worldObjects.put(arg, new PlayerView((Player)arg));
            }
        }
    }

    public void checkIfAmmunition(Observable o, Object arg){
        if (arg instanceof Ammunition){
            Ammunition ammo = (Ammunition) arg;
            if (ammo.getType() instanceof AK47Projectile) {
                worldObjects.put(ammo, new AmmunitionView(ammo, GraphicalAssets.TRIFORCE_BULLET));
            }
        }

    }

    public void checkIfPowerUp(Observable o, Object arg){
        if (arg instanceof PowerUpI){
            PowerUpI powerUp = (PowerUpI) arg;
            if (powerUp instanceof DamagePowerUp){
                worldObjects.put(powerUp, new PowerUpView(powerUp, GraphicalAssets.AK47));
            } else if (powerUp instanceof MovementSpeedPowerUp){
                worldObjects.put(powerUp, new PowerUpView(powerUp, GraphicalAssets.MAGNUM));
            } else if (powerUp instanceof FireRatePowerUp){
                worldObjects.put(powerUp, new PowerUpView(powerUp, GraphicalAssets.CHESTOPEN));
            }
        }
    }
}
