package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import edu.chl.blastinthepast.model.ammunition.Ammunition;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.projectiles.AK47Projectile;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelInterface;
import edu.chl.blastinthepast.model.powerUp.*;
import edu.chl.blastinthepast.utils.GraphicalAssets;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.SoundAssets;
import edu.chl.blastinthepast.view.*;
import edu.chl.blastinthepast.view.characterviews.CharacterViewFactory;
import edu.chl.blastinthepast.view.characterviews.PlayerView;
import edu.chl.blastinthepast.view.projectileviews.ProjectileViewFactory;
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
    private CharacterViewFactory characterViewFactory;
    private ProjectileViewFactory projectileViewFactory;
    private PowerUpViewFactory powerUpViewFactory;
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
    private TiledMapTileLayer collisionLayer;
    private float tileWidth, tileHeight;
    private boolean playerBlocked = false;

    public PlayState(GameStateManager gsm, BPModel model, LevelInterface level) {
        super(gsm, model, level);
        this.model = model;
        chestView = new ChestView(model.getChest());
        characterViewFactory = new CharacterViewFactory();
        projectileViewFactory = new ProjectileViewFactory();
        powerUpViewFactory = new PowerUpViewFactory();
        worldObjects = new HashMap <>();
        worldObjectsRemoveList = new ArrayList<>();
        chestView = new ChestView(model.getChest());
        batch = new SpriteBatch();
        pcs = new PropertyChangeSupport(this);
        model.addObserver(this);
        init(model, level);
    }

    @Override
    public void init(BPModel model) {}

    @Override
    public void init(BPModel model, LevelInterface level) {
        setCrosshairCursor();
        spawnCharacterViews();

        //Configures and sets up the camera.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        camera.position.set(model.getMapWidth() / 2, model.getMapHeight() / 2, 0);
        camera.update();

        //Configures and sets the game map.
        if (level.getLevel() == LevelInterface.Level.ONE) {
            tiledMap = new TmxMapLoader().load("big_grass.tmx");
        }
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        collisionLayer = (TiledMapTileLayer)tiledMap.getLayers().get(1);
        tileWidth = collisionLayer.getTileWidth();
        tileHeight = collisionLayer.getTileHeight();

        // Sets the font and GUI representation of equipped weapon and ammo count.
        font = new BitmapFont();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        ammoLabel = new Label("ammo", labelStyle);
        weaponImage = new Image(playerView.getWeaponView().getTexture());

        //Paints out hearts representing the players health.
        heartIcons = new ArrayList<>(model.getPlayer().getHealth());
        for (int i=0; i < model.getPlayer().getHealth(); i++) {
            Texture heartTexture = GraphicalAssets.HEART;
            heartIcons.add(new Image(heartTexture));
        }
        updateHeartPositions();

        //Sets the music for the game.
        music = SoundAssets.SANIC_THEME;
        music.setVolume(ViewConstants.masterVolume);
        music.setLooping(true);
        music.stop();
        setCrosshairCursor();
        spawnCharacterViews();
        music.play();
    }

    @Override
    public void update(float dt) {
        if(!model.isPaused()) {
            checkForCollision();
            updateWeaponGUI();
            updateHearts();
            updateHeartPositions();
            updateCameraPosition();
            if (!music.isPlaying()) {
                music.play();
            }
            chestView.update();
        }
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
    }

    @Override
    public void draw() {
        tiledMapRenderer.render();

        chestView.draw(batch);
        for (WorldObject o : worldObjects.values()){
            o.draw(batch);
        }

        if (!worldObjectsRemoveList.isEmpty()) {
            removeObjects();
        }

        batch.begin();
        ammoLabel.draw(batch, 1);
        weaponImage.draw(batch, 1);
        for (Image image : heartIcons) {
            image.draw(batch, 1);
            image.setSize(32, 32);
        }
        batch.end();
    }

    public void updateHearts() {
        if (model.getPlayer().getHealth() < heartIcons.size()) {
            for (int i = 0; i < heartIcons.size() - model.getPlayer().getHealth(); i++) {
                heartIcons.remove(heartIcons.size() - 1);
            }
        } else if (model.getPlayer().getHealth() > heartIcons.size()) {
            for (int i=0; i < model.getPlayer().getHealth()-heartIcons.size(); i++) {
                heartIcons.add(new Image(GraphicalAssets.HEART));
            }
        }
    }

    public void updateHeartPositions() {
        if (!heartIcons.isEmpty()) {
            heartIcons.get(0).setPosition(camera.position.x - ViewConstants.CAMERA_WIDTH / 2 + 15, camera.position.y + ViewConstants.CAMERA_HEIGHT / 2 - 60);
        }
        for (int i=1; i<heartIcons.size(); i++) {
            heartIcons.get(i).setPosition(heartIcons.get(i - 1).getX() + 40, heartIcons.get(i - 1).getY());
        }
    }

    public void updateWeaponGUI() {
        ammoLabel.setPosition(camera.position.x - ViewConstants.CAMERA_WIDTH / 2 + 10, camera.position.y - ViewConstants.CAMERA_HEIGHT / 2 + 10);
        weaponImage.setPosition(ammoLabel.getX(), ammoLabel.getY() + ammoLabel.getHeight());
        ammoLabel.setText(model.getPlayer().getCurrentWeapon().getTotalBullets() + "/" + model.getPlayer().getCurrentWeapon().getbulletsLeftInMagazine());
    }

    public void updateCameraPosition() {
        camera.position.x = playerView.getCharacter().getPosition().getX();
        camera.position.y = playerView.getCharacter().getPosition().getY();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
    }

    public void spawnCharacterViews() {
        for (Character c : model.getCharacters()) {
            if(c.getCharacterType() == CharacterTypeEnum.PLAYER) {
                playerView = (PlayerView)characterViewFactory.getCharacterView(c);
                worldObjects.put(c, playerView);
            } else {
                worldObjects.put(c, characterViewFactory.getCharacterView(c));
            }
        }
    }

    public void checkForCollision() {
        int playerMapX, playerMapY;
        if (model.getPlayer().isMovingEast()) {
            playerMapX = Math.round((model.getPlayer().getPosition().getX() + playerView.getSprite().getWidth()) / tileWidth) - 1;
        } else {
            playerMapX = Math.round(model.getPlayer().getPosition().getX() / tileWidth) - 1;
        }
        if (model.getPlayer().isMovingNorth()) {
            playerMapY = Math.round((model.getPlayer().getPosition().getY() + playerView.getSprite().getHeight())/ tileHeight) - 1;
        } else {
            playerMapY = Math.round(model.getPlayer().getPosition().getY()/ tileHeight) - 1;
        }

        if (collisionLayer.getCell(playerMapX, playerMapY) != null) {
            if (collisionLayer.getCell(playerMapX, playerMapY).getTile().getProperties().containsKey("blocked")) {
                if (!playerBlocked) {
                    pcs.firePropertyChange("blocked", false, "");
                    playerBlocked = true;
                }
            }
        } else {
            if (playerBlocked) {
                playerBlocked = false;
                pcs.firePropertyChange("unblocked", false, true);
            }
        }
    }

    @Override
    public void dispose() {
        music.pause();
    }

    public PlayerView getPlayer() {
        return playerView;
    }

    public Position screenToWorldCoordinates(Position screenCoordinates){
        Vector3 screenCordinatesVector = new Vector3(screenCoordinates.getX(), screenCoordinates.getY(), 0);
        Vector3 worldCoordinatesVector = camera.unproject(screenCordinatesVector);
        Position worldCoordinates = new Position(worldCoordinatesVector.x, worldCoordinatesVector.y);
        return worldCoordinates;
    }

    public boolean addListener(PropertyChangeListener pcl) {
        for (int i = 0; i < pcs.getPropertyChangeListeners().length; i++){
            if (pcs.getPropertyChangeListeners()[i] == pcl){
                return false;
            }
        }
        pcs.addPropertyChangeListener(pcl);
        return true;
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
        if (ViewConstants.masterVolume == 0) {
            ViewConstants.masterVolume = 0.2f;
        } else {
            ViewConstants.masterVolume = 0;

        }
        music.setVolume(ViewConstants.masterVolume);
    }

    public void updateGUIWeapon() {
        weaponImage = new Image(playerView.getWeaponView().getTexture());
    }

    public void setCrosshairCursor() {
        Gdx.input.setCursorImage(GraphicalAssets.CROSSHAIR, GraphicalAssets.CROSSHAIR.getWidth() / 2, GraphicalAssets.CROSSHAIR.getHeight() / 2);
    }

    public void checkIfProjectile(Observable o, Object arg){
        if (arg instanceof ProjectileInterface){
            ProjectileInterface projectile = (ProjectileInterface) arg;
            worldObjects.put(arg, projectileViewFactory.getProjectileView(projectile));
        }
    }

    public void checkIfCharacter(Observable o, Object arg){
        if (arg instanceof Character) {
            Character character = (Character) arg;
            worldObjects.put(character, characterViewFactory.getCharacterView(character));
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
            worldObjects.put(powerUp, powerUpViewFactory.getPowerUpView(powerUp));
        }
    }

}