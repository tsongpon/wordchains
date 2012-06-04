package wordchains;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TestWordChains {

    private WordChains wordChains;

    @Before
    public void initClass() {
        wordChains = new WordChains();
    }

    @Test
    public void testFindChainSuccess() {
        System.out.println(wordChains.findChains("CODE", "RUBY"));
        System.out.println(wordChains.findChains("DOG","CAT"));
        System.out.println(wordChains.findChains("SACK","SACS"));
    }

    @Test
    public void testFindChainNoChain() {
        System.out.println(wordChains.findChains("DIT","PUP"));
    }
}
