package edu.chl.blastinthepast.model.level;

import edu.chl.blastinthepast.model.enemy.Enemy;
import edu.chl.blastinthepast.model.position.Position;
import edu.chl.blastinthepast.model.enemy.*;
import edu.chl.blastinthepast.model.player.CharacterTypeEnum;
import edu.chl.blastinthepast.model.player.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Shif on 2015-05-15.
 */
public class LevelOne extends Level  {

    public LevelOne() {
        super(3200, 3200, "big_grass.tmx");
        player = new Player(new Position(mapWidth/2, mapHeight/2));
        spawnNewEnemies();
    }

    @Override
    public void spawnNewEnemies() {
        enemies = new ArrayList<>();
        Random r = new Random();
        boss = (Boss)enemyFactory.getEnemy(player, CharacterTypeEnum.BOSS, new Position(r.nextFloat() * mapWidth, r.nextFloat() * mapHeight));
        for (int i=0; i<5; i++) {
            Enemy e = enemyFactory.getEnemy(player, CharacterTypeEnum.PLEB, new Position(r.nextFloat() * mapWidth, r.nextFloat() * mapHeight));
            enemies.add(e);
        }
        enemies.add(boss);
    }

    @Override
    public Level getLevel() {
        return Level.ONE;
    }

}
