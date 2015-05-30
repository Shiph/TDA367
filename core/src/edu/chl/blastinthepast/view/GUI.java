package edu.chl.blastinthepast.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import edu.chl.blastinthepast.model.player.Player;
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

    public void updateWeaponGUI(OrthographicCamera camera, Player player) {
        ammoLabel.setPosition(camera.position.x - ViewConstants.CAMERA_WIDTH / 2 + 10, camera.position.y - ViewConstants.CAMERA_HEIGHT / 2 + 10);
        weaponImage.setPosition(ammoLabel.getX(), ammoLabel.getY() + ammoLabel.getHeight());
        ammoLabel.setText(player.getCurrentWeapon().getTotalBullets() + "/" + player.getCurrentWeapon().getbulletsLeftInMagazine());
    }

    public void updateHeartPositions(OrthographicCamera camera) {
        if (!heartIcons.isEmpty()) {
            heartIcons.get(0).setPosition(camera.position.x - ViewConstants.CAMERA_WIDTH / 2 + 15, camera.position.y + ViewConstants.CAMERA_HEIGHT / 2 - 60);
        }
        for (int i=1; i<heartIcons.size(); i++) {
            heartIcons.get(i).setPosition(heartIcons.get(i - 1).getX() + 40, heartIcons.get(i - 1).getY());
        }
    }

    public void updateGUIWeapon(WeaponView weaponView) {
        weaponImage = new Image(weaponView.getTexture());
    }

    public void updateHearts(Player player) {
        if (player.getHealth() < heartIcons.size()) {
            for (int i = 0; i < heartIcons.size() - player.getHealth(); i++) {
                heartIcons.remove(heartIcons.size() - 1);
            }
        } else if (player.getHealth() > heartIcons.size()) {
            for (int i=0; i < player.getHealth()-heartIcons.size(); i++) {
                heartIcons.add(new Image(GraphicalAssets.HEART));
            }
        }
    }

    public void setCrosshairCursor() {
        Gdx.input.setCursorImage(GraphicalAssets.CROSSHAIR, GraphicalAssets.CROSSHAIR.getWidth() / 2, GraphicalAssets.CROSSHAIR.getHeight() / 2);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        ammoLabel.draw(batch, 1);
        weaponImage.draw(batch, 1);
        for (Image image : heartIcons) {
            image.draw(batch, 1);
            image.setSize(32, 32);
        }
        batch.end();
    }
}