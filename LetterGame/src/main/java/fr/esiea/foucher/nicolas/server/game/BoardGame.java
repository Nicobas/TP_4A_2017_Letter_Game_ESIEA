package fr.esiea.foucher.nicolas.server.game;

import fr.esiea.foucher.nicolas.server.game.dictionary.IDictionary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BoardGame implements IDictionary {
    private List<Letter> commonPot;
    private List<String> dictionary;
    private List<String> foundWords;

    public BoardGame() {
        this.commonPot = new ArrayList<Letter>();
        this.dictionary = new ArrayList<String>();
        this.foundWords = new ArrayList<String>();

        System.out.println(System.getProperty("user.dir"));

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream flux = classloader.getResourceAsStream("dico.txt");
            InputStreamReader reader = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(reader);
            String line;
            while ((line = buff.readLine()) != null) {
                //... retirer les caracteres spéciaux, gerer les - partout
                this.dictionary.add(line);
            }
            buff.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void addLetter(Letter l) {
        this.commonPot.add(l);
    }

    public String getCommonPotString() {
        String s = new String();

        for (Letter l : this.commonPot) {
            s += l + ", ";
        }

        s = s.substring(0, s.length() - 2); // On retire le ", " de la fin

        return s;
    }

    @Override
    public boolean isWord(String word) {
        for (String s : this.dictionary) {
            if (word.equals(s))
                return true;
        }

        return false;
    }

    public boolean isAlreadyFound(String word) {
        for (String s : this.foundWords) {
            if (word.equals(s))
                return true;
        }

        return false;
    }

    public boolean isAccepted(String word) { // Return true si word est constitué des lettres dispo ou d'un "-"
        if (word.replaceAll("-", "").trim().equals("")) // si la chaine est vide on ne contient que des "-", le mot est faux
            return false;

        ArrayList<Letter> tmpPot = new ArrayList<Letter>();
        for (Letter l : this.commonPot)
            tmpPot.add(l);

        for (char c : word.toCharArray()) {
            if (c == '-')
                continue;

            boolean ok = false;
            Letter toRemove = null;
            for (Letter l : tmpPot) {
                if (l.getChar() == c) {
                    ok = true;
                    toRemove = l;
                    break;
                }
            }

            if (!ok)
                return false;

            tmpPot.remove(toRemove);
        }

        return true;
    }

    public List<String> getFoundWords() {
        return foundWords;
    }

    public void addFoundWord(String word) {
        this.foundWords.add(word);
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public List<Letter> getCommonPot() {
        return commonPot;
    }
}
