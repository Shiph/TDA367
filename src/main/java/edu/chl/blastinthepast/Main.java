package edu.chl.blastinthepast;

import edu.chl.blastinthepast.controller.ProjectController;
import edu.chl.blastinthepast.model.Project;
import edu.chl.blastinthepast.view.ProjectView;
import javax.swing.SwingUtilities;
import edu.chl.blastinthepast.jgame.*;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main {
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
                    final Project project = new Project();
                    final ProjectView projectView = new ProjectView(project);
                    
                    ProjectController.create(project, projectView);
                    projectView.setVisible(true);
                });
	}
}
