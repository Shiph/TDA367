package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private final String title = "Blast in the Past";
    private int currentItem;
    private String[] menuItems;
    private Music music;

    public MenuState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
        this.gsm = gsm;
    }

    @Override
    public void init(BPModel model) {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("sanic.mp3"));
        music.play();
        music.setLooping(true);
        music.setVolume(0.2f);
        titleFont = new BitmapFont();
        font = new BitmapFont();
        menuItems = new String[]{"Play", "Highscores", "Quit"};
    }

    public void update(float dt) {
    }

    public void draw() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        titleFont.setColor(Color.WHITE);
        float width = titleFont.getBounds(title).width;
        titleFont.draw(batch, title, (Gdx.graphics.getWidth()-width) / 2, 300);

        for(int i = 0; i < menuItems.length; i++) {
            width = font.getBounds(menuItems[i]).width;
            if(currentItem == i) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(batch, menuItems[i], Gdx.graphics.getWidth()/2 - 50, 180-35 * i);
        }

        batch.end();
    }

    public void handleInput() {
    }

    public void select() {
        if(currentItem == 0) {
            if (gsm == null) System.out.println("gsm is null, yo");
            gsm.setState(GameStateManager.PLAY);
        } else if(currentItem == 1) {
            //gsm.setState(GameStateManager.HIGHSCORES);
        } else if(currentItem == 2){
            Gdx.app.exit();
        }
    }

    public void dispose() {
        titleFont.dispose();
        music.stop();
    }

    public void moveUp() {
        if(currentItem > 0) {
            currentItem--;
            draw();
        }
    }

    public void moveDown() {
        if(currentItem < 2) {
            currentItem++;
            draw();
        }
    }

}
