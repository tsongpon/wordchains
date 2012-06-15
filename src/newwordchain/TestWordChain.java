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
        wordChains.findWordChain("BINGO", "FEELS");
        //wordChains.findWordChain("JAVA", "RUBY");
        //wordChains.findWordChain("CAT", "DOG");
        //wordChains.findWordChain("CAT", "SIT");
    }
}
