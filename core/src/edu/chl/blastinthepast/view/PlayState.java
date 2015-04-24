package edu.chl.blastinthepast.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.GameState;
import edu.chl.blastinthepast.Player;

/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState {

    private Player player;
    private SpriteBatch batch;
    //private InputHandler inputHandler;

    public PlayState(GameStateManager gsc) {
        super(gsc);
    }

    @Override
    public void init() {
        player = new Player();
        batch = new SpriteBatch();
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void draw() {
        player.draw(batch);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void dispose() {

    }

    public Player getPlayer() {
        return player;
    }

}
