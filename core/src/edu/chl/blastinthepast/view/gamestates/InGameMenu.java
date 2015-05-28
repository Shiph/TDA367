package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelInterface;
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class InGameMenu extends GameState {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private int currentItem;
    private String[] menuItems;
    private Sprite sprite;
    private Texture texture;
    private Label soundTextLabel;
    private Label.LabelStyle soundTextLabelStyle;
    private boolean soundOn = true;
    private Image soundImage;
    private Texture soundTexture;

    public InGameMenu (GameStateManager gsm, BPModel model) {
        super(gsm, model);
    }

    @Override
    public void init(BPModel model, LevelInterface level) {}

    @Override
    public void init(BPModel model) {
        texture = GraphicalAssets.INGAMEMENU;
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);
        sprite.setOrigin(0,0);
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        font = new BitmapFont();
        soundTexture = GraphicalAssets.SOUND;
        soundImage = new Image(soundTexture);
        soundImage.setSize(24, 24);
        soundImage.setPosition(Constants.CAMERA_WIDTH - 40, 20);
        soundTextLabelStyle = new Label.LabelStyle();
        soundTextLabelStyle.font = font;
        soundTextLabel = new Label("Press SPACE to toggle sound", soundTextLabelStyle);
        soundTextLabel.setPosition(soundImage.getX() - 200, soundImage.getY());
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
        if (menuItems != null) {
            for (int i = 0; i < menuItems.length; i++) {
                if (currentItem == i) {
                    font.setColor(Color.RED);
                } else {
                    font.setColor(Color.WHITE);
                }
                font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2 - 30, 140 - 35 * i);
            }
        }
        soundImage.draw(batch, 1);
        soundTextLabel.draw(batch, 1);
        batch.end();
    }

    @Override
    public void dispose() {}

    public void toggleSoundSprite() {
        if (soundOn) {
            soundTexture = GraphicalAssets.SOUND_OFF;
            soundImage = new Image(soundTexture);
            soundImage.setSize(24, 24);
            soundImage.setPosition(Constants.CAMERA_WIDTH - 40, 20);
            soundOn = false;
        } else {
            soundTexture = GraphicalAssets.SOUND;
            soundImage = new Image(soundTexture);
            soundImage.setSize(24, 24);
            soundImage.setPosition(Constants.CAMERA_WIDTH - 40, 20);
            soundOn = true;
        }
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public void setMenuItems(String[] menuItems) {
        this.menuItems = menuItems;
    }

}
