package fr.esiea.foucher.nicolas.dictionary.server.game;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {
    protected List<String> foundWords;

    public AbstractPlayer() {
        this.foundWords = new ArrayList<String>();
    }

    abstract void playRound(BoardGame bg);
    abstract String getName();

    Letter getLetter(BoardGame bg) {
        Letter l = new Letter();
        bg.addLetter(l);
        return l;
    }
}
