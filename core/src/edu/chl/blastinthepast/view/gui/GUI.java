package edu.chl.blastinthepast.view.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.chl.blastinthepast.model.player.Player;
import edu.chl.blastinthepast.view.ViewConstants;
import edu.chl.blastinthepast.view.assets.GraphicalAssets;
import edu.chl.blastinthepast.view.characterviews.PlayerView;
import edu.chl.blastinthepast.view.weaponviews.WeaponView;

import java.util.ArrayList;

/**
 * Created by MattiasJ on 2015-05-30.
 */
public class GUI {

    private Label ammoLabel;
    private Label.LabelStyle labelStyle;
    private BitmapFont font;
    private ArrayList<Image> heartIcons;
    private Image weaponImage;
    private int opacity = 1;

    public void init(Player player, PlayerView playerView) {

        //Paints out hearts representing the players health.
        heartIcons = new ArrayList<>(player.getHealth());
        for (int i=0; i < player.getHealth(); i++) {
            Texture heartTexture = GraphicalAssets.HEART;
            heartIcons.add(new Image(heartTexture));
        }

        // Sets the font and GUI representation of equipped weapon and ammo count.
        font = new BitmapFont();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        ammoLabel = new Label("ammo", labelStyle);
        weaponImage = new Image(playerView.getWeaponView().getTexture());
    }

    /**
     * Sets the ammo and weapon labels positions retrieving positions from the camera and updates the ammo count.
     * @param camera The given camera in which the update will occur.
     * @param player The given player character from which the weapon information will be retrieved.
     */
    public void updateWeaponGUI(OrthographicCamera camera, Player player) {
        ammoLabel.setPosition(camera.position.x - ViewConstants.CAMERA_WIDTH / 2 + 10, camera.position.y - ViewConstants.CAMERA_HEIGHT / 2 + 10);
        weaponImage.setPosition(ammoLabel.getX(), ammoLabel.getY() + ammoLabel.getHeight());
        ammoLabel.setText(player.getCurrentWeapon().getTotalBullets() + "/" + player.getCurrentWeapon().getbulletsLeftInMagazine());
    }

    /**
     * Updates the heart positions retrieving positions from the camera.
     * @param camera The given camera in which the update will occur.
     */
    public void updateHeartPositions(OrthographicCamera camera) {
        if (!heartIcons.isEmpty()) {
            heartIcons.get(0).setPosition(camera.position.x - ViewConstants.CAMERA_WIDTH / 2 + 15, camera.position.y + ViewConstants.CAMERA_HEIGHT / 2 - 60);
        }
        for (int i=1; i<heartIcons.size(); i++) {
            heartIcons.get(i).setPosition(heartIcons.get(i - 1).getX() + 40, heartIcons.get(i - 1).getY());
        }
    }

    /**
     * If a new weapon is equipped, this method will change the weapon label.
     * @param weaponView The according weapon view from which the texture will be retrieved.
     */
    public void updateGUIWeapon(WeaponView weaponView) {
        weaponImage = new Image(weaponView.getTexture());
    }

    /**
     * Updates the number of hearts displayed depending on the player characters health.
     * @param player The given player character which health will determine the number of hearts.
     */
    public void updateHearts(Player player) {
        if (player.getHealth() < heartIcons.size()) {
            for (int i = 0; i < heartIcons.size() - player.getHealth(); i++) {
                heartIcons.remove(heartIcons.size() - 1);
            }
        } else if (player.getHealth() > heartIcons.size()) {
            for (int i = 0; i < player.getHealth()-heartIcons.size(); i++) {
                heartIcons.add(new Image(GraphicalAssets.HEART));
            }
        }
    }

    /**
     * Sets the image for the crosshair cursor.
     */
    public void setCrosshairCursor() {
        Gdx.input.setCursorImage(GraphicalAssets.CROSSHAIR, GraphicalAssets.CROSSHAIR.getWidth() / 2, GraphicalAssets.CROSSHAIR.getHeight() / 2);
    }

    /**
     * Draws the labels and hearts with the updated positions.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        batch.begin();
        ammoLabel.draw(batch, opacity);
        weaponImage.draw(batch, opacity);
        for (Image image : heartIcons) {
            image.draw(batch, opacity);
            image.setSize(32, 32);
        }
        batch.end();
    }
}