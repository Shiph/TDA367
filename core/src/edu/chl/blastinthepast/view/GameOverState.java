package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.blastinthepast.controller.GameStateManager;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.GameState;
import edu.chl.blastinthepast.utils.HighScoreHandler;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class GameOverState extends GameState{

    private SpriteBatch batch;
    private GameStateManager gsm;
    private boolean newHighScore;
    private OrthographicCamera camera;
    private final String title = "Game Over";
    private float width;
    private ShapeRenderer shapeRenderer;
    private char[] newName;
    private int currentChar;
    private BitmapFont gameOverFont;
    private BitmapFont font;

    public GameOverState(GameStateManager gsm, BPModel model) {
        super(gsm, model);
        this.gsm = gsm;
    }

    public void init(BPModel model) {
        gameOverFont = new BitmapFont();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        newHighScore = HighScoreHandler.gameData.isHighScore(HighScoreHandler.gameData.getTentativeScore());
        if(newHighScore) {
            newName = new char[] {'A', 'A', 'A'};
            currentChar = 0;
        }
    }

    public void update(float dt) {}

    public void draw() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        width = gameOverFont.getBounds(title).width;
        gameOverFont.draw(batch, title, (Gdx.graphics.getWidth() - width) / 2, 4 * Gdx.graphics.getHeight() / 5 + 50);

        if(!newHighScore) {
            batch.end();
            return;
        }

        String s = "New High Score: " + HighScoreHandler.gameData.getTentativeScore();
        width = font.getBounds(s).width;
        font.draw(batch, s, (Gdx.graphics.getWidth() - width) / 2, 4 * Gdx.graphics.getHeight() / 5);

        for(int i = 0; i < newName.length; i++) {
            font.draw(batch, Character.toString(newName[i]), Gdx.graphics.getWidth() + 30 * i, 4 * Gdx.graphics.getHeight() / 7);
        }

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(Gdx.graphics.getWidth() + 30 * currentChar, 100, 4 * Gdx.graphics.getHeight() / 7 + 14 * currentChar, 100);
        shapeRenderer.end();

    }

    public void handleInput() {}

    public void select() {
        if(newHighScore) {
            HighScoreHandler.gameData.addHighScore(HighScoreHandler.gameData.getTentativeScore(), new String(newName));
            HighScoreHandler.save();
        }
    }

    public void moveUp() {
        if(newName[currentChar] == ' ') {
            newName[currentChar] = 'Z';
        } else {
            newName[currentChar] --;
            if(newName[currentChar] < 'A') {
                newName[currentChar] = ' ';
            }
        }
    }

    public void moveDown() {
        if(newName[currentChar] == ' ') {
            newName[currentChar] = 'A';
        } else {
            newName[currentChar] ++;
            if(newName[currentChar] > 'Z') {
                newName[currentChar] = ' ';
            }
        }
    }

    public void moveLeft() {

    }

    public void moveRight() {
        if(currentChar < newName.length - 1) {
            currentChar++;
        }
    }

    public void dispose() {
        if(currentChar > 0) {
            currentChar--;
        }
    }

}
