package newwordchain;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TestWordChain {
    private WordChain wordChains;

    @Before
    public void initClass() {
        wordChains = new WordChain();
    }

    @Test
    public void testFindChain() {
        //wordChains.findWordChain("ILLER", "GENRO");
        //wordChains.findWordChain("ROES", "UNAU");
       // wordChains.findWordChain("CAT", "DOG");
        wordChains.findWordChain("JAVA", "RUBY");
        //wordChains.findWordChain("BINGO", "RUDDY");
        //wordChains.findWordChain("CODE", "RUBY");
        //wordChains.findWordChain("AA", "DE");
    }
}
