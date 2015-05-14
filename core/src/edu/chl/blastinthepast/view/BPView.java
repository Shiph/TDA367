package edu.chl.blastinthepast.view;

import com.badlogic.gdx.*;
import edu.chl.blastinthepast.model.level.BPModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BPView extends ApplicationAdapter implements PropertyChangeListener {

	private BPModel model;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	//private GameStateManager gsm;
	//private InputHandler inputHandler;

	public BPView(BPModel model){
		this.model=model;
	}

	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);
		/*
		inputHandler = new InputHandler();
		inputHandler.addListener(this);
		Gdx.input.setInputProcessor(inputHandler);
		gsm = new GameStateManager(model);
		*/
	}

	@Override
	public void render () {
		/*
		inputHandler.checkForInput();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
		*/
	}
	/**
	 * Adds a listener to the PropertyChangeSupport pcs.
	 *
	 * @param pcl - listener to be added.
	 */
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		pcs.firePropertyChange(evt);
	}
/*
	public GameStateManager getGameStateController() {
		return gsm;
	}
*/
}
