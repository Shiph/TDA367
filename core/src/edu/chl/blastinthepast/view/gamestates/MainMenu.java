package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.level.LevelInterface;
import edu.chl.blastinthepast.view.assets.GraphicalAssets;
import edu.chl.blastinthepast.view.assets.SoundAssets;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class MainMenu extends GameState {
    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont font;
    private OrthographicCamera camera;
    private final String title = "blast in the past";
    private int currentItem;
    private Texture texture;
    private String[] menuItems;
    private Music music;
    private Sprite sprite;

    public MainMenu(GameStateManager gsm, BPModel model) {
        super(gsm, model);
    }

    @Override
    public void init(BPModel model, LevelInterface level) {}

    @Override
    public void init(BPModel model) {
        texture = GraphicalAssets.MAINMENU;
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);
        sprite.setOrigin(0,0);
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        music = SoundAssets.MENU_MUSIC;
        music.play();
        music.setLooping(true);
        music.setVolume(0.4f);
        titleFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        font = new BitmapFont();
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        float width = titleFont.getBounds(title).width;
        titleFont.draw(batch, title, (Gdx.graphics.getWidth() - width) / 2, 4 * Gdx.graphics.getHeight() / 5);
        if (menuItems != null) {
            for (int i = 0; i < menuItems.length; i++) {
                if (currentItem == i) {
                    font.setColor(Color.RED);
                } else {
                    font.setColor(Color.WHITE);
                }
                font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2 - 30, 170 - 35 * i);
            }
        }
        batch.end();
    }

    public void playMusic() {
        music.play();
    }

    public void setMenuItems(String[] menuItems) {
        this.menuItems = menuItems;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    @Override
    public void dispose() {
        music.stop();
        music.dispose();
    }

}
