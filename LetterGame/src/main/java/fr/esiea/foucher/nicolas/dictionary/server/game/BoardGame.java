package fr.esiea.foucher.nicolas.dictionary.server.game;

import java.util.List;

public class BoardGame {
    public List<Letter> commonPot;

    public BoardGame() {

    }

    public void addLetter(Letter l) {
        this.commonPot.add(l);
    }
}
