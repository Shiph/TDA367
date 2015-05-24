package edu.chl.blastinthepast.utils;

import com.badlogic.gdx.Gdx;

import java.io.*;

/**
 * Created by MattiasJ on 2015-05-03.
 */
public class HighScoreHandler {

    public static GameData gameData;

    public HighScoreHandler() {
        load();
    }

    public static void init() {
        gameData = new GameData();
        gameData.init();
        save();
    }

    public static void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("highscores.sav"));
            out.writeObject(gameData);
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static void load() {
        try {
            if (!saveFileExists()) {
                init();
                return;
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("highscores.sav"));
            gameData = (GameData) in.readObject();
            in.close();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            Gdx.app.exit();
        } catch(IOException e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static boolean saveFileExists() {
        File f = new File("highscores.sav");
        return f.exists();
    }

}
