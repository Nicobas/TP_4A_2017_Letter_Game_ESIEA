package fr.esiea.foucher.nicolas.dictionary.server.game;

import org.junit.Test;

import static fr.esiea.foucher.nicolas.dictionary.server.game.Letter.chars;

import static org.junit.Assert.*;

/**
 * Created by Theo on 02/03/2017.
 */
public class LetterTest {

    Letter letter = new Letter();

    @Test
    public void validChar() {
        boolean alphabet;
        if(chars.indexOf(letter.getChar()) >= 0)
            alphabet = true;
        else
            alphabet = false;
        assertTrue(alphabet);
    }

    @Test
    public void getChar() {
        assertTrue(letter.getChar() == (char)letter.getChar());
    }

}