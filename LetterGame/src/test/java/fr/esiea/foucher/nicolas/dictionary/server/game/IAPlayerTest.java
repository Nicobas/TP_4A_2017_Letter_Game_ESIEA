package fr.esiea.foucher.nicolas.dictionary.server.game;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Theo on 02/03/2017.
 */
public class IAPlayerTest {

    @Test(expected = Exception.class)
    public void difficultyTestSuperior() throws Exception {
        IAPlayer player = new IAPlayer(1.5f);
    }

    @Test(expected = Exception.class)
    public void difficultyTestInferior() throws Exception {
        IAPlayer player = new IAPlayer(0.01f);
    }

}