package fr.esiea.foucher.nicolas.dictionary.server.game;

public class Letter {
    static String chars = "abcdefghijklmnopqrstuvwxyz";

    private char letter;

    public Letter() {
        int i = (int) Math.floor(Math.random() * 26);
        this.letter = chars.charAt(i);
    }

    public char getChar() {
        return letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter1 = (Letter) o;

        return letter == letter1.letter;
    }

    @Override
    public String toString() {
        return this.getChar() + "";
    }

}
