package edu.chl.blastinthepast.view.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.model.level.BPModel;
import edu.chl.blastinthepast.model.level.LevelInterface;
import edu.chl.blastinthepast.model.menu.GameOverModel;
import edu.chl.blastinthepast.utils.GraphicalAssets;
import edu.chl.blastinthepast.utils.HighScoreHandler;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class GameOverState extends GameState{

    private SpriteBatch batch;
    private Sprite sprite;
    private Texture texture;
    private OrthographicCamera camera;
    private final String title = "Game Over";
    private float width;
    private char[] newName;
    private int currentChar;
    private BitmapFont gameOverFont;
    private BitmapFont font;
    private GameOverModel gameOverModel;

    public GameOverState(GameStateManager gsm, BPModel model, GameOverModel gameOverModel) {
        super(gsm, model);
        this.gameOverModel = gameOverModel;
    }

    @Override
    public void init(BPModel model, LevelInterface level) {}

    @Override
    public void init(BPModel model) {
        texture = GraphicalAssets.GAMEOVER;
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);
        sprite.setOrigin(0,0);
        gameOverFont = new BitmapFont();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
    }

    @Override
    public void update(float dt) {
        currentChar = gameOverModel.getCurrentChar();
        newName = gameOverModel.getNewName();
    }


    @Override
    public void draw() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        width = gameOverFont.getBounds(title).width;
        gameOverFont.draw(batch, title, (Gdx.graphics.getWidth() - width) / 2, 4 * Gdx.graphics.getHeight() / 5 + 50);

        if(!gameOverModel.isNewHighScore()) {
            batch.end();
            return;
        }

        String newHighScore = "New High Score: " + gameOverModel.getScore();
        width = font.getBounds(newHighScore).width;
        font.draw(batch, newHighScore, (Gdx.graphics.getWidth() - width) / 2, 3 * Gdx.graphics.getHeight() / 4);

        for(int i = 0; i < newName.length; i++) {
            if(i == currentChar) {
                font.setColor(Color.RED);
            }
            font.draw(batch, Character.toString(newName[i]), (Gdx.graphics.getWidth() - 60) / 2 + 20 * i, 3 * Gdx.graphics.getHeight() / 5);
            font.setColor(Color.WHITE);
        }

        batch.end();
    }



    @Override
    public void dispose() {
        newName = new char[] {'A', 'A', 'A'};
        currentChar = 0;
    }

}
