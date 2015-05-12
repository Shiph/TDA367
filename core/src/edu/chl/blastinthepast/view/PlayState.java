package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ArrayMap;
import edu.chl.blastinthepast.model.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import edu.chl.blastinthepast.model.Character;
import edu.chl.blastinthepast.model.Enemy;
import edu.chl.blastinthepast.model.Projectile;
import edu.chl.blastinthepast.utils.collisiondetection.CollisionDetection;
import edu.chl.blastinthepast.utils.Position;
import edu.chl.blastinthepast.utils.SoundAssets;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.CharacterIterator;
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
    //private ArrayList<PowerUpView> powerUps= new ArrayList<PowerUpView>();
    private Sound wowSound;
    private Music music;
    private PropertyChangeSupport pcs;
    private HashMap <ProjectileInterface, ProjectileView> projectiles = new HashMap <ProjectileInterface, ProjectileView>();
    private HashMap <Character, CharacterView> characters;
    private ArrayList <Character> removeChar;
    private ArrayList <ProjectileInterface> removeProj;

    public PlayState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
    }

    @Override
    public void init(BPModel model) {
        this.model=model;
        model.addObserver(this);
        characters = new HashMap <Character, CharacterView>();
        removeChar= new ArrayList<Character>();
        removeProj = new ArrayList<ProjectileInterface>();
        chestView = new ChestView();
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
        pcs=new PropertyChangeSupport(this);
        music.play();
        for (Character c : model.getCharacters()){
            if (c instanceof Player) {
                Player p = (Player) c;
                playerView = new PlayerView(p);
                characters.put(p, playerView);
            } else if (c instanceof Boss) {
                Boss b = (Boss) c;
                characters.put(b, new BossView(b));
            } else if (c instanceof Enemy){
                Enemy e= (Enemy)c;
                characters.put(e, new EnemyView(e));
            }
        }
    }


    @Override
    public void update(float dt) {
        camera.position.set(playerView.getRectangles().get(0).getX() + playerView.getRectangles().get(0).getWidth() / 2, playerView.getRectangles().get(0).getY() + playerView.getRectangles().get(0).getWidth() / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
        //new CollisionDetection(enemies, playerView, projectiles, chestView, collisionView);
        if(!music.isPlaying()) {
            music.play();
        }
    }

    @Override
    public void draw() {
        tiledMapRenderer.render();
        for (CharacterView c : characters.values()) {
            c.draw(batch);
        }
        for (ProjectileView p : projectiles.values()) {
            p.draw(batch);
        }
        checkIfHit();
        removeObjects();
    }

    public void checkIfHit(){
        for (ProjectileView p: projectiles.values()){
            for (CharacterView c: characters.values()){
                if (c.getRectangles().get(0).overlaps(p.getRectangles().get(0)) && !(c.getCharacter().getProjectiles().contains(p.getProjectile()))){
                    pcs.firePropertyChange("characterHit", p.getProjectile(), c.getCharacter());
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
            if (projectiles.containsKey(arg)) {
                if (!removeProj.contains(arg)) {
                    removeProj.add((ProjectileInterface) arg);
                }
            } else {
                projectiles.put((ProjectileInterface)arg, new ProjectileView((ProjectileInterface)arg));
            }
        }
        if (arg instanceof Character){
            if (characters.containsKey(arg)) {
                if (!removeChar.contains(arg)) {
                    removeChar.add((Character) arg);
                }
            } else if (arg instanceof Boss){
                characters.put((Boss)arg, new BossView((Boss)arg));
            } else if (arg instanceof Enemy){
                characters.put((Enemy)arg, new EnemyView((Enemy)arg));
            }  else if (arg instanceof Player){
                characters.put((Player)arg, new PlayerView((Player)arg));
            }
        }

    }

    public void removeObjects(){
        Iterator<Character> charIter=removeChar.iterator();
        Iterator<ProjectileInterface> projIter = removeProj.iterator();
        while (charIter.hasNext()){
            Character c = charIter.next();
            characters.remove(c);
            charIter.remove();
        }
        while (projIter.hasNext()){
            ProjectileInterface p = projIter.next();
            projectiles.remove(p);
            projIter.remove();
        }
    }
}