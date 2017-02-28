package fr.esiea.foucher.nicolas.dictionary.server.game;

import fr.esiea.foucher.nicolas.dictionary.MessageContainer;
import fr.esiea.foucher.nicolas.dictionary.server.ClientManager;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<AbstractPlayer> players;
    public BoardGame boardgame;

    public Game() {
        this.players = new ArrayList<AbstractPlayer>();
        this.boardgame = new BoardGame();
    }

    public AbstractPlayer run() {
        ClientManager.sendBroadcast(MessageContainer.getIntro(this.getPlayers().size()));

        // Tirer une lettre chacun pour définir l'ordre, les mettre ds le pot commun

        // Chaque tour :
            // le joueur tire 2 lettres
            // Il peut trouver des mots, si c'est le cas il pioche une lettre
                // TQ il trouve ddes mots
            // TQ le joueur à trouvé - de 10 mots

        // retourner le joueur gagnant
        return null;
    }

    public List<AbstractPlayer> getPlayers() {
        return players;
    }

    public void adddPlayer(AbstractPlayer player) {
        this.players.add(player);
    }
}
