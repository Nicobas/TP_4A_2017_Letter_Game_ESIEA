package fr.esiea.foucher.nicolas.dictionary.server.game;

import fr.esiea.foucher.nicolas.dictionary.MessageContainer;
import fr.esiea.foucher.nicolas.dictionary.server.ClientManager;

import java.util.ArrayList;
import java.util.List;

public class Game {
    static int numberOfWordsToWin = 10;

    public List<AbstractPlayer> players;
    public BoardGame boardgame;

    public Game() {
        this.players = new ArrayList<AbstractPlayer>();
        this.boardgame = new BoardGame();
    }

    public AbstractPlayer run() {
        ClientManager.sendBroadcast(MessageContainer.getIntro(this.players.size()));

        // Pour chaque joueurs, on tire une lettre pour désigner l'ordre et on l'ajoute au pot commun
        for (AbstractPlayer player : this.players) {
            player.generateFirstLetter(this.boardgame);
        }

        // Liste des joueurs par ordre de jeu
        List<AbstractPlayer> orderedPlayers = new ArrayList<AbstractPlayer>();

        // On remplit la liste des joueus dans l'ordre
        while (this.players.size() > 0) {
            char c = '{'; //-- En ascii, '{' est le caractère jusre àprès le 'z'
            AbstractPlayer p = null;
            for (AbstractPlayer player : this.players) {
                if (player.getFirstLetter().getChar() < c) {
                    p = player;
                    c = player.getFirstLetter().getChar();
                }
            }

            orderedPlayers.add(p);
            this.players.remove(p);
        }

        // On remplace la liste des joueurs non triés par la liste des joueurs triés
        this.players = orderedPlayers;

        ClientManager.sendBroadcast("L'ordre de jeu est le suivant :");
        for (AbstractPlayer player : this.players) {
            ClientManager.sendBroadcast(player.getName());
        }

        while (true) {
            for (AbstractPlayer player : this.players) {
                player.playRound(this.boardgame);

                if (player.getFoundWords().size() >= numberOfWordsToWin)
                    return player;
            }
        }
    }

    public void addPlayer(AbstractPlayer player) {
        this.players.add(player);
    }
}
