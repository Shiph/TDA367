package edu.chl.blastinthepast.view.characterviews;

import edu.chl.blastinthepast.model.enemy.Boss;
import edu.chl.blastinthepast.model.enemy.Pleb;
import edu.chl.blastinthepast.model.player.CharacterI;
import edu.chl.blastinthepast.model.player.Player;

/**
 * Created by MattiasJ on 2015-05-20.
 */
public class CharacterViewFactory {

    public CharacterView getCharacterView(CharacterI characterI) {
        if(characterI == null) {
            return null;
        }
        switch (characterI.getCharacterType()) {
            case PLAYER:
                Player player = (Player) characterI;
                return new PlayerView(player);
            case PLEB:
                Pleb pleb = (Pleb) characterI;
                return new PlebView(pleb);
            case BOSS:
                Boss boss = (Boss) characterI;
                return new BossView(boss);
        }
        return null;
    }

}