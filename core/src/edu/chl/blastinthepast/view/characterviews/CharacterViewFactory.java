package edu.chl.blastinthepast.view.characterviews;

import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Pleb;
import edu.chl.blastinthepast.model.player.Character;
import edu.chl.blastinthepast.model.player.Player;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class CharacterViewFactory {

    public CharacterView getCharacterView(Character character) {
        if(character == null) {
            return null;
        }
        switch (character.getCharacterType()) {
            case PLAYER:
                Player player = (Player) character;
                return new PlayerView(player);
            case PLEB:
                Pleb pleb = (Pleb) character;
                return new PlebView(pleb);
            case BOSS:
                Boss boss = (Boss) character;
                return new BossView(boss);
        }
        return null;
    }

}