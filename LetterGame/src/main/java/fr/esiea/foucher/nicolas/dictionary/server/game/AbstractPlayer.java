package fr.esiea.foucher.nicolas.dictionary.server.game;

import fr.esiea.foucher.nicolas.dictionary.server.ClientManager;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {
    private List<String> foundWords;
    private Letter firstLetter;

    public AbstractPlayer() {
        this.foundWords = new ArrayList<String>();
    }

    public abstract String getName();

    public abstract String findWord(BoardGame bg);

    public abstract void startRound();

    public abstract void endRound();

    public void playRound(BoardGame bg) {
        this.startRound();

        this.generateRandomLetter(bg);
        this.generateRandomLetter(bg);
        String word;

        while ((word = this.findWord(bg)) != null) {
            bg.addFoundWord(word);
            this.addFoundWord(word);
            this.generateRandomLetter(bg);
        }

        this.endRound();
    }

    public Letter generateRandomLetter(BoardGame bg) {
        Letter l = new Letter();
        bg.addLetter(l);
        return l;
    }

    public Letter generateFirstLetter(BoardGame bg) {
        Letter l = this.generateRandomLetter(bg);
        this.firstLetter = l;
        return l;
    }

    public List<String> getFoundWords() {
        return foundWords;
    }

    public void addFoundWord(String word) {
        this.foundWords.add(word);
    }

    public Letter getFirstLetter() {
        return firstLetter;
    }
}