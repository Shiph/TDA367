package edu.chl.blastinthepast.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chl.blastinthepast.controller.BPController;
import edu.chl.blastinthepast.view.ViewConstants;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Blast in the Past";
		config.width = ViewConstants.CAMERA_WIDTH;
		config.height = ViewConstants.CAMERA_HEIGHT;
		BPController controller = BPController.createController();
		new LwjglApplication(controller, config);
	}

}