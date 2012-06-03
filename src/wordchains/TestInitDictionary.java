package wordchains;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TestInitDictionary {

    private InitDictionary initDictionary;

    @Before
    public void initClass() {
        initDictionary = new InitDictionary();
    }

    @Test
    public void testReadDictionaryFile() {
        initDictionary.findChains("CAT","DOG");
        //initDictionary.findChains("CODE","RUBY");
        //initDictionary.findChains("SACK","SACS");
    }
}
