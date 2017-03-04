package fr.esiea.foucher.nicolas.server.game;

import fr.esiea.foucher.nicolas.server.game.dictionary.IDictionary;
import fr.esiea.foucher.nicolas.server.game.dictionary.StringOperation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardGame implements IDictionary {
    private List<Letter> commonPot;
    private List<String> dictionary;
    private List<String> foundWords;
    private List<Letter> usedLetters;
    private List<String> usedWords;

    public BoardGame() {
        this.commonPot = new ArrayList<Letter>();
        this.dictionary = new ArrayList<String>();
        this.foundWords = new ArrayList<String>();
        this.usedLetters = new ArrayList<Letter>();
        this.usedWords = new ArrayList<String>();

        //System.out.println(System.getProperty("user.dir"));

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream flux = classloader.getResourceAsStream("dico.txt");
            InputStreamReader reader = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(reader);
            String line;
            while ((line = buff.readLine()) != null) {
                line = StringOperation.sansAccents(line).toLowerCase();
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

    // Probleme si les mots se chevauchent
    // Ex : on a déjà trouvé "ab" et "be", on dispose d'un a et on veut faire "abe", la methode va retirer "ab" et il nous manquera un e.
    // Cependant c'est correct car on utilise le a et le be
    public boolean isAccepted(String word) { //... voir comment renvoyé les mots/lettres utilisés, pour les suppr/afficher
        String w = new String(word);
        this.usedWords = new ArrayList<String>();
        this.usedLetters = new ArrayList<Letter>();

        w = w.replaceAll("-", "").trim();

        if (w.equals("")) // si la chaine est vide on ne contient que des "-", le mot est faux
            return false;

        ArrayList<String> orderedList = new ArrayList<String>();
        while (this.foundWords.size() > 0) {
            int l = 0;
            String tmp = null;
            for (String s : this.foundWords) {
                if (s.length() > l) {
                    tmp = s;
                    l = s.length();
                }
            }

            orderedList.add(tmp);
            this.foundWords.remove(tmp);
        }

        this.foundWords = orderedList;

        for (String s : this.foundWords) {
            String s2 = s.replaceAll("-", "").trim();
            if (w.contains(s2) && !usedWords.contains(s)) {
                usedWords.add(s);
                w = w.replaceFirst(s2, "");
            }
        }

        for (char c : w.toCharArray()) {
            boolean ok = false;
            for (Letter l : this.commonPot) {
                if (l.getChar() == c && !usedLetters.contains(l)) {
                    usedLetters.add(l);
                    ok = true;
                    break;
                }
            }

            if (!ok)
                return false;
        }

        return true;
    }

    public boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;

        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);

        return Arrays.equals(c1, c2);
    }

    public boolean isFoundWordsAnagram(String word) {
        for (String s : this.foundWords) {
            if (isAnagram(s, word))
                return true;
        }

        return false;
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

    public List<Letter> getUsedLetters() {
        return usedLetters;
    }

    public List<String> getUsedWords() {
        return usedWords;
    }
}
