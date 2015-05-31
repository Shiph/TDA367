package edu.chl.blastinthepast.view.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by jonas on 2015-05-06.
 */
public class SoundAssets {
    public static final Music MENU_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
    public static final Music GAME_MUSIC =  Gdx.audio.newMusic(Gdx.files.internal("contra.mp3"));
    public static final Sound AK47_SOUND = Gdx.audio.newSound(Gdx.files.internal("ak47.mp3"));
    public static final Sound MAGNUM_SOUND = Gdx.audio.newSound(Gdx.files.internal("magnum.mp3"));
}
