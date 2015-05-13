package edu.chl.blastinthepast.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chl.blastinthepast.controller.BPController;
import edu.chl.blastinthepast.model.level.BPModel;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Blast in the Past";
		config.width = 800;
		config.height = 480;
		BPModel model = new BPModel();
		BPController controller = BPController.create(model);
		new LwjglApplication(controller, config);
	}

}