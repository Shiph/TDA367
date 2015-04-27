package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.controller.GameState;
import edu.chl.blastinthepast.model.BPModel;

/**
 * Created by MattiasJ on 2015-04-24.
 */
public class MenuState extends GameState {

    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont font;
    private OrthographicCamera camera;
    private GameStateManager gsm;
    private final String title = "blast in the past";
    private int currentItem;
    private Texture texture;
    private String[] menuItems;
    private boolean inGame;
    private Music music;
    private Sprite sprite;

    public MenuState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
        this.gsm = gsm;
        inGame = false;
        currentItem = 2;
    }

    @Override
    public void init(BPModel model) {
        texture = new Texture(Gdx.files.internal("menu.jpg"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);
        sprite.setOrigin(0,0);
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
        music.play();
        music.setLooping(true);
        music.setVolume(0.2f);
        titleFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        font = new BitmapFont();
        menuItems = new String[]{"Resume", "Save game", "Load game", "New game", "Highscores", "Options", "Quit"};
    }

    public void update(float dt) {
        music.play();
    }

    public void draw() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        float width = titleFont.getBounds(title).width;
        titleFont.draw(batch, title, (Gdx.graphics.getWidth()-width) / 2, 400);

        if(!inGame) {
            for (int i = 2; i < menuItems.length; i++) {
                if (currentItem == i) {
                    font.setColor(Color.RED);
                } else {
                    font.setColor(Color.WHITE);
                }
                font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2 - 30, 230 - 35 * i);
            }
        } else {
            for (int i = 0; i < menuItems.length; i++) {
                if (currentItem == i) {
                    font.setColor(Color.RED);
                } else {
                    font.setColor(Color.WHITE);
                }
                font.draw(batch, menuItems[i], Gdx.graphics.getWidth() / 2 - 30, 230 - 35 * i);
            }
        }
        batch.end();
    }

    public void handleInput() {
    }

    public void select() {
        if(currentItem == 0) {
            gsm.setState(GameStateManager.PLAY, false);
        } else if(currentItem == 1) {
            //gsm.setState(GameStateManager.SAVEGAME);
        } else if (currentItem == 2) {
            //gsm.setState(GameStateManager.LOADGAME);
        } else if (currentItem == 3) {
            inGame = true;
            currentItem = 0;
            gsm.setState(GameStateManager.PLAY, true);
        } else if (currentItem == 4) {
            //gsm.setState(GameStateManager.HIGHSCORES);
        } else if (currentItem == 5) {
            //gsm.setState(GameStateManager.OPTIONS);
        } else if (currentItem == 6) {
            Gdx.app.exit();
        }
    }

    public void dispose() {
        music.pause();
    }

    public void moveUp() {
        if (!inGame) {
            if (currentItem > 2) {
                currentItem--;
                draw();
            } else {
                currentItem = menuItems.length-1;
            }
        } else {
            if (currentItem > 0) {
                currentItem--;
                draw();
            } else {
                currentItem = menuItems.length-1;
            }
        }
    }

    public void moveDown() {
        if(currentItem < menuItems.length-1) {
            currentItem++;
            draw();
        } else if (inGame){
            currentItem = 0;
        } else {
            currentItem = 2;
        }
    }

    public boolean isInGame() {
        return inGame;
    }

}
