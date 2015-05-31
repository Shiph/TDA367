package edu.chl.blastinthepast.tests;

import edu.chl.blastinthepast.model.BPModel;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.projectiles.ProjectileInterface;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Created by Shif on 2015-05-30.
 */
public class BPModelTest {

    private BPModel model;
    private MockLevel level;

    @Before
    public void before() {
        level = new MockLevel();
        model = new BPModel(level);
    }

    @Test
    public void testAddProjectile() throws Exception {
        ArrayList<ProjectileInterface> projectiles = model.getProjectiles();
        model.addProjectile(new MockProjectile());
        assertTrue(model.getProjectiles().size() != 0);
    }

    @Test
    public void testPause() throws Exception {
        model.pause();
        assertTrue(model.isPaused());
    }

    @Test
    public void testUnPause() throws Exception {
        model.unPause();
        assertTrue(!model.isPaused());
    }

    @Test
    public void testNewCharacter() throws Exception {
        ArrayList<CharacterI> characterIs = model.getCharacterIs();
        int oldSize = characterIs.size();
        MockEnemy e = new MockEnemy();
        MockPCL pcl = new MockPCL();
        model.addListener(pcl);
        model.newCharacter(e);
        characterIs = model.getCharacterIs();
        int newSize = characterIs.size();
        assertTrue(oldSize != newSize);
        assertTrue(pcl.eventName.equals("New Character"));
    }

}