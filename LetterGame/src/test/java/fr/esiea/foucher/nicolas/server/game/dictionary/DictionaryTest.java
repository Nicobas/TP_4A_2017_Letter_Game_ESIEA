package fr.esiea.foucher.nicolas.server.game.dictionary;

import fr.esiea.foucher.nicolas.server.game.BoardGame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test sample for Dictionary.
 */
public class DictionaryTest {

    private IDictionary dictionary;

    @Before
    public void setup() {
        this.dictionary = new BoardGame();
    }

    @Test
    public void testIsWord() {
        assertTrue(dictionary.isWord("maman"));
        assertFalse(dictionary.isWord("namam"));
    }
}
