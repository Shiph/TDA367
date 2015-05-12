package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.utils.Constants;

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
    private ImageButton soundButton;
    private Texture soundTexture;
    private Sprite soundSprite;

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
        soundTexture = new Texture(Gdx.files.internal("sound.png"));
        soundSprite = new Sprite(soundTexture);
        soundButton = new ImageButton(new SpriteDrawable(soundSprite));
        soundButton.setSize(24, 24);
        soundButton.setPosition(Constants.CAMERA_WIDTH - 40, 20);
    }

    public void update(float dt) {}

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
        soundButton.draw(batch, 1);
        batch.end();
    }

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
            gsm.setState(GameStateManager.MAINMENU, false);
        }
    }

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

}
