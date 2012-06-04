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
        System.out.println(wordChains.findChains("DIT","AIL"));
/*        System.out.println(wordChains.findChains("DOG","CAT"));
        System.out.println(wordChains.findChains("CAT","DOG"));
        System.out.println(wordChains.findChains("SACK","SACS"));*/
    }

}
