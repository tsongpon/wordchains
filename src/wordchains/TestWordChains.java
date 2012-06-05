package wordchains;

import junit.framework.Assert;
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
        System.out.println(wordChains.findChains("DIT", "AIL"));
        System.out.println(wordChains.findChains("DOG", "CAT"));
        System.out.println(wordChains.findChains("CAT", "DOG"));
        System.out.println(wordChains.findChains("SACK", "SACS"));

        Assert.assertEquals("DIT-AIT-AIL", wordChains.findChains("DIT", "AIL"));
        Assert.assertEquals("DOG-COG-COT-CAT", wordChains.findChains("DOG", "CAT"));
        Assert.assertEquals("CAT-COT-COG-DOG", wordChains.findChains("CAT", "DOG"));
        Assert.assertEquals("SACK-SACS", wordChains.findChains("SACK", "SACS"));
        Assert.assertEquals("SACS-SACK", wordChains.findChains("SACS", "SACK"));
    }

    @Test
    public void testFindChainFail() {
        Assert.assertEquals("No chains", wordChains.findChains("SACS", "SAME"));
    }

}
