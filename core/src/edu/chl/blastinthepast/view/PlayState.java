package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.blastinthepast.GameState;
import edu.chl.blastinthepast.controller.GameStateController;
import edu.chl.blastinthepast.model.Player;

/**
 * Created by Shif on 23/04/15.
 */
public class PlayState extends GameState {

    private Player player;
    private SpriteBatch batch;
    //private InputHandler inputHandler;

    public PlayState(GameStateController gsc) {
        super(gsc);
    }

    @Override
    public void init() {
        System.out.println("inside init playstate");
        player = new Player();
        batch = new SpriteBatch();
        //inputHandler = new InputHandler();
        //Gdx.input.setInputProcessor(inputHandler);
        //System.out.println(Gdx.input.getInputProcessor());
    }

    @Override
    public void update(float dt) {
        //handleInput();
        //player.update(dt);
    }

    @Override
    public void draw() {
        player.draw(batch);
    }

    @Override
    public void handleInput() {
        //System.out.println("inside handle input");
        /*
        player.setWest(inputHandler.west);
        player.setEast(inputHandler.east);
        player.setNorth(inputHandler.north);
        player.setSouth(inputHandler.south);

        System.out.println(inputHandler.west);
        System.out.println(inputHandler.east);
        System.out.println(inputHandler.north);
        System.out.println(inputHandler.south);
        */
    }

    @Override
    public void dispose() {

    }

    public Player getPlayer() {
        return player;
    }

}
