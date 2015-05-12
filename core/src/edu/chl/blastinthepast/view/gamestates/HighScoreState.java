package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.utils.HighScoreHandler;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class HighScoreState extends GameState {

    private HighScoreHandler highScoreHandler;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private final String title = "High Scores";
    private float width;
    private long[] highScores;
    private String[] names;

    public HighScoreState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
        this.gsm = gsm;
        init(model);
    }

    public void init(BPModel model) {
        highScoreHandler = new HighScoreHandler();
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        highScoreHandler.load();
        highScores = highScoreHandler.gameData.getHighScores();
        names = highScoreHandler.gameData.getNames();
    }

    public void draw() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        width = font.getBounds(title).width;
        font.draw(batch, title, (Gdx.graphics.getWidth() - width) / 2, 4 * Gdx.graphics.getHeight() / 5 + 50);
        for(int i = 0; i < highScores.length; i++) {
            String temp = String.format("%2d. %7s %s", i + 1, highScores[i], names[i]);
            width = font.getBounds(temp).width;
            font.draw(batch, temp, (Gdx.graphics.getWidth()-width) / 2, 4 *Gdx.graphics.getHeight() / 5 - 35 * i);
        }
        batch.end();
    }

    public void dispose() {

    }

    public void update(float dt) {

    }

    public void handleInput() {

    }
}
