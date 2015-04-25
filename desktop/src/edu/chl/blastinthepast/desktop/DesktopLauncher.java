package edu.chl.blastinthepast.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chl.blastinthepast.controller.BPController;
import edu.chl.blastinthepast.controller.InputHandler;
import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.view.BPView;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Blast in the Past";
		config.width = 800;
		config.height = 480;
		BPModel model = new BPModel();
		BPView view = new BPView(model);
		BPController controller = BPController.create(model, view);
		new LwjglApplication(view, config);
	}

}
