package edu.chl.blastinthepast.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chl.blastinthepast.controller.GameController;
import edu.chl.blastinthepast.model.GameModel;
import edu.chl.blastinthepast.view.BlastInThePast;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Blast in the Past";
		config.width = 800;
		config.height = 480;
		GameModel model = new GameModel();
		BlastInThePast view = new BlastInThePast();
		GameController controller = GameController.create(model, view);
		new LwjglApplication(view, config);
	}

}
