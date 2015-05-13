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
import edu.chl.blastinthepast.utils.Constants;
import edu.chl.blastinthepast.utils.GraphicalAssets;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class InGameMenu extends GameState {

    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont font;
    private OrthographicCamera camera;
    private GameStateManager gsm;
    private final String title = "blast in the past";
    private int currentItem;
    private String[] menuItems;
    private Sprite sprite;
    private Label soundTextLabel;
    private Label.LabelStyle soundTextLabelStyle;
    private boolean soundOn = true;
    private Image soundImage;
    private Texture soundTexture;

    public InGameMenu (GameStateManager gsm, BPModel model) {
        super(gsm, model);
        this.gsm = gsm;
        currentItem = 0;
    }

    @Override
    public void init(BPModel model) {
        sprite = new Sprite();
        sprite.setOrigin(0,0);
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        titleFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        font = new BitmapFont();
        menuItems = new String[]{"Continue", "Save game", "Options", "Exit to main menu"};
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

    public void draw() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        float width = titleFont.getBounds(title).width;
        titleFont.draw(batch, title, (Gdx.graphics.getWidth() - width) / 2, 400);

        for (int i = 0; i < menuItems.length; i++) {
            if (currentItem == i) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2 - 30, 140 - 35 * i);
        }
        soundImage.draw(batch, 1);
        soundTextLabel.draw(batch, 1);
        batch.end();
    }

    @Override
    public void handleInput() {
    }

    public void select() {
        if(currentItem == 0) {
            gsm.setState(GameStateManager.PLAY, true);
        } else if (currentItem == 1) {
            //gsm.setState(GameStateManager.SAVES);
        } else if (currentItem == 2) {
            //gsm.setState(GameStateManager.OPTIONS, true);
        } else if (currentItem == 3) {
            gsm.setState(GameStateManager.MAIN_MENU, false);
        }
    }

    @Override
    public void dispose() {
        currentItem = 0;
    }

    public void moveUp() {
        if (currentItem > 0) {
            currentItem--;
            draw();
        } else {
            currentItem = menuItems.length-1;
            draw();
        }
    }

    public void moveDown() {
        if(currentItem < menuItems.length-1) {
            currentItem++;
            draw();
        } else {
            currentItem = 0;
        }
    }

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

}
