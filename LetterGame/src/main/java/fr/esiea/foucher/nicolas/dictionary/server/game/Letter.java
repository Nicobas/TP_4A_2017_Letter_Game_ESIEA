package fr.esiea.foucher.nicolas.dictionary.server.game;

public class Letter {
    static String chars = "abcdefghijklmnopqrstuvwxyz";

    private char letter;

    public Letter() {
        int i = (int)Math.floor(Math.random() * 26);
        this.letter = chars.charAt(i);
    }

    public char getLetter() {
        return letter;
    }
}
