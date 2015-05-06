package edu.chl.blastinthepast.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by jonas on 2015-05-06.
 */
public class SoundAssets {
    public static final Sound WOW_SOUND= Gdx.audio.newSound(Gdx.files.internal("wow.mp3"));

    public static final Music SANIC_THEME =  Gdx.audio.newMusic(Gdx.files.internal("sanic.mp3"));
}
