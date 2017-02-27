package fr.esiea.foucher.nicolas.dictionary.server.game;

import java.util.List;

public class Game {
    public List<IPlayer> players;

    public Game(List<IPlayer> players) {
        this.players = players;
    }
}
