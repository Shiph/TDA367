package edu.chl.blastinthepast.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chl.blastinthepast.controller.BPController;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.utils.Constants;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Blast in the Past";
		config.width = Constants.CAMERA_WIDTH;
		config.height = Constants.CAMERA_HEIGHT;
		BPController controller = BPController.createController();
		new LwjglApplication(controller, config);
	}

}