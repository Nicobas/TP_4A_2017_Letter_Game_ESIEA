package fr.esiea.foucher.nicolas.dictionary.server.game;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by Theo on 02/03/2017.
 */
public class BoardGameTest {

    private BoardGame bg = new BoardGame();

    @Test
    public void addLetter() throws Exception {

    }

    @Test
    public void getCommonPotString() throws Exception {
        
    }

    @Test
    public void isWord() throws Exception {
        assertFalse(bg.isWord("fjzeoijeioz"));
        assertTrue(bg.isWord("maison"));
    }

    @Test
    public void isAlreadyFound() throws Exception {

    }

    @Test
    public void isAccepted() throws Exception {

    }

    @Test
    public void getFoundWords() throws Exception {

    }

    @Test
    public void addFoundWord() throws Exception {

    }

    @Test
    public void getDictionary() throws Exception {

    }

    @Test
    public void getCommonPot() throws Exception {

    }

}