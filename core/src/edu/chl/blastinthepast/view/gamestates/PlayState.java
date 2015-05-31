package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import edu.chl.blastinthepast.model.ammunition.Ammunition;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.projectiles.AK47Projectile;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelInterface;
import edu.chl.blastinthepast.model.powerUp.*;
import edu.chl.blastinthepast.view.assets.GraphicalAssets;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.view.assets.SoundAssets;
import edu.chl.blastinthepast.view.*;
import edu.chl.blastinthepast.view.characterviews.CharacterViewFactory;
import edu.chl.blastinthepast.view.characterviews.PlayerView;
import edu.chl.blastinthepast.view.gui.GUI;
import edu.chl.blastinthepast.view.powerupview.PowerUpViewFactory;
import edu.chl.blastinthepast.view.projectileviews.ProjectileViewFactory;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;


/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState implements PropertyChangeListener{

    private BPModel model;
    private PlayerView playerView;
    private GUI gui;
    private ChestView chestView;
    private CharacterViewFactory characterViewFactory;
    private ProjectileViewFactory projectileViewFactory;
    private PowerUpViewFactory powerUpViewFactory;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private Music music;
    private HashMap <Object, WorldObject> worldObjects;
    private ArrayList <Object> worldObjectsRemoveList;

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
        gui = new GUI();
        init(model, level);
        model.addListener(this);
    }

    @Override
    public void init(BPModel model) {}

    @Override
    public void init(BPModel model, LevelInterface level) {
        spawnCharacterViews();

        //Configures and sets up the camera.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        camera.position.set(level.getMapWidth() / 2, level.getMapHeight() / 2, 0);
        camera.update();

        //Updates GUI.
        gui.setCrosshairCursor();
        gui.init(model.getPlayer(), playerView);
        gui.updateHeartPositions(camera);

        //Configures and sets the game map.
        tiledMap = new TmxMapLoader().load(level.getMapName());
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //Sets the music for the game.
        music = SoundAssets.GAME_MUSIC;
        music.setVolume(ViewConstants.masterVolume);
        music.setLooping(true);
        music.stop();
        music.play();
    }

    @Override
    public void update(float dt) {
        if(!model.isPaused()) {
            updateCameraPosition();
            gui.updateWeaponGUI(camera, model.getPlayer());
            gui.updateHearts(model.getPlayer());
            gui.updateHeartPositions(camera);
            if (!music.isPlaying()) {
                music.play();
            }
            chestView.update();
        }
    }

    @Override
    public void draw() {
        tiledMapRenderer.render();

        chestView.draw(batch);
        for (WorldObject o : worldObjects.values()){
            o.draw(batch);
        }
        gui.draw(batch);

        if (!worldObjectsRemoveList.isEmpty()) {
            removeObjects();
        }
    }

    public void updateCameraPosition() {
        camera.position.x = playerView.getCharacter().getPosition().getX();
        camera.position.y = playerView.getCharacter().getPosition().getY();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
    }

    public void spawnCharacterViews() {
        for (CharacterI c : model.getCharacterIs()) {
            if(c.getCharacterType() == CharacterTypeEnum.PLAYER) {
                playerView = (PlayerView)characterViewFactory.getCharacterView(c);
                worldObjects.put(c, playerView);
            } else {
                worldObjects.put(c, characterViewFactory.getCharacterView(c));
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

    public GUI getGUI() {
        return gui;
    }

    public Position screenToWorldCoordinates(Position screenCoordinates){
        Vector3 screenCordinatesVector = new Vector3(screenCoordinates.getX(), screenCoordinates.getY(), 0);
        Vector3 worldCoordinatesVector = camera.unproject(screenCordinatesVector);
        Position worldCoordinates = new Position(worldCoordinatesVector.x, worldCoordinatesVector.y);
        return worldCoordinates;
    }

    /**
     *  Since removing objects without using the iterator while iterating through a list is met with an error
     *  it is necessary to mark them for removal and remove them when the iteration is complete.
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
            ViewConstants.masterVolume = 0.4f;
        } else {
            ViewConstants.masterVolume = 0;
        }
        music.setVolume(ViewConstants.masterVolume);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case "New Projectile":
                ProjectileInterface p = (ProjectileInterface)evt.getNewValue();
                worldObjects.put(p, projectileViewFactory.getProjectileView(p));
                break;
            case "New PowerUp":
                PowerUpI powerUp = (PowerUpI) evt.getNewValue();
                worldObjects.put(powerUp, powerUpViewFactory.getPowerUpView(powerUp));
                break;
            case "New Ammunition":
                Ammunition ammo = (Ammunition) evt.getNewValue();
                if (ammo.getType() instanceof AK47Projectile) {
                    worldObjects.put(ammo, new AmmunitionView(ammo, GraphicalAssets.TRIFORCE_BULLET));
                }
                break;
            case "New Character":
                CharacterI characterI = (CharacterI) evt.getNewValue();
                worldObjects.put(characterI, characterViewFactory.getCharacterView(characterI));
                break;
            case "Remove Character":
                removeObject(evt.getNewValue());
                //Play sound
                break;
            case "Remove PowerUp":
                removeObject(evt.getNewValue());
                //Play sound
                break;
            case "Remove Ammunition":
                removeObject(evt.getNewValue());
                //Play sound
                break;
            case "Remove Projectile":
                removeObject(evt.getNewValue());
                //Play sound
                break;
        }
    }

    public void removeObject(Object obj){
        if (worldObjects.containsKey(obj)) {
            if (!worldObjectsRemoveList.contains(obj)) {
                worldObjectsRemoveList.add(obj);
            }
        }
    }

}
