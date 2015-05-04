import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Daniel Puleri on 11/16/2014.
 */
public class StringSearchEdgeTests {
    private StringSearchingInterface stringSearcher;
    private String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @Before
    public void setup() {
        stringSearcher = new StringSearching();
    }

    /*-------------TEST ALL ILLEGAL ARGS-------------*/
    /*
     * BOYER MOORE
     * If the pattern is null or of length 0, throw an IllegalArgumentException.
     * If the text is null, throw an IllegalArgumentException.
     * If the text is of length 0, return an empty list.
     */
    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testBoyerMooreIllegalArgs() {
        stringSearcher.boyerMoore(null, "blahblah");
    }

    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testBoyerMooreIllegalArgs2() {
        stringSearcher.boyerMoore("", "blahblah");
    }

    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testBoyerMooreIllegalArgs3() {
        stringSearcher.boyerMoore("blahblah", null);
    }

    /*
     * BUILD LAST TABLE
     * If pattern is null throw an IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testBuildLAstTableIllegalArgs() {
        stringSearcher.buildLastTable(null);
    }

    /*
     * GENERATE HASH
     * If current is null throw an IllegalArgumentException.
     * If length is negative or greater than the length of current, throw an
     * IllegalArgumentException.
     */
    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testGenerateHashIllegalArgs() {
        stringSearcher.generateHash(null, Integer.MAX_VALUE);
    }

    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testGenerateHashIllegalArgs2() {
        stringSearcher.generateHash("sdfsdf", -32432);
    }

    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testGenerateHashIllegalArgs3() {
        stringSearcher.generateHash("sdfsdf", 7);
    }

    /*
     * RABIN KARP
     * If the pattern is null or of length 0, throw an IllegalArgumentException.
     * If the text is null, throw an IllegalArgumentException.
     * If the text is of length 0, return an empty list.
     */
    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testRabinKarpIllegalArgs() {
        stringSearcher.rabinKarp(null, "sdf");
    }

    @Test(expected=IllegalArgumentException.class, timeout = 200)
     public void testRabinKarpIllegalArgs2() {
        stringSearcher.rabinKarp("", "sdf");
    }

    @Test(expected=IllegalArgumentException.class, timeout = 200)
    public void testRabinKarpIllegalArgs3() {
        stringSearcher.rabinKarp("fdgfd", null);
    }


    /*-------------Begin other tests-------------*/

    /*If the text is of length 0, return an empty list.*/
    @Test(timeout = 200)
    public void testBoyerMooreLenZero() {
        List<Integer> betterBeEmpty = stringSearcher.boyerMoore("DFSGsdfg", "");
        assertEquals("Should return list of length 0", 0, betterBeEmpty.size());
    }

    /*If pattern is longer than text return list of length zero; see piazza @949*/
    @Test(timeout = 200)
    public void testBoyerMooreLenZero2() {
        List<Integer> betterBeEmpty = stringSearcher.boyerMoore("DFSGsdfg", "5");
        assertEquals("Should return list of length 0", 0, betterBeEmpty.size());
    }

    @Test(timeout = 200)
    public void testBoyerMooreLenOne() {
        List<Integer> betterNotBeEmpty = stringSearcher.boyerMoore("a", "aaaaaaaaaa");
        assertEquals("Should be the same length as text", 10, betterNotBeEmpty.size());
    }

    @Test(timeout = 200)
    public void testBoyerMooreLenOne2() {
        List<Integer> betterBeEmpty = stringSearcher.boyerMoore("a", "zzzzzzzzzzzzz");
        assertEquals("Should be empty since it does not appear in text", 0, betterBeEmpty.size());
    }

    @Test(timeout = 200)
    public void testBoyerMooreOverlapping() {
        List<Integer> betterNotBeEmpty = stringSearcher.boyerMoore("aa", "aaaaa");
        assertEquals("Does not work with patterns that overlap", 4, betterNotBeEmpty.size());
    }

    @Test(timeout = 200)
    public void testBoyerMooreOther() {
        Integer[] blah = {9, 299, 442};
        List<Integer> expected = new ArrayList<>();
        for (Integer i : blah) {
            expected.add(i);
        }
        List<Integer> betterNotBeEmpty = stringSearcher.boyerMoore("um", loremIpsum);
        assertEquals("Does not work with larger string?", expected, betterNotBeEmpty);
    }



    //now for Rabin Karp
    /*If the text is of length 0, return an empty list.*/
    @Test(timeout = 200)
    public void testRapinKarpLenZero() {
        List<Integer> betterBeEmpty = stringSearcher.rabinKarp("DFSGsdfg", "");
        assertEquals("Should return list of length 0", 0, betterBeEmpty.size());
    }

    /*If pattern is longer than text return list of length zero; see piazza @949*/
    @Test(timeout = 200)
    public void testRapinKarpLenZero2() {
        List<Integer> betterBeEmpty = stringSearcher.rabinKarp("DFSGsdfg", "2");
        assertEquals("Should return list of length 0", 0, betterBeEmpty.size());
    }

    @Test(timeout = 200)
    public void testRabinKarpLenOne() {
        List<Integer> betterNotBeEmpty = stringSearcher.rabinKarp("a", "aaaaaaaaaa");
        assertEquals("Should be the same length as text", 10, betterNotBeEmpty.size());
    }

    @Test(timeout = 200)
    public void tesRabinKarpLenOne2() {
        List<Integer> betterBeEmpty = stringSearcher.rabinKarp("a", "zzzzzzzzzzzzz");
        assertEquals("Should be empty since it does not appear in text", 0, betterBeEmpty.size());
    }

    @Test(timeout = 200)
    public void testRabinKarpOverlapping() {
        List<Integer> betterNotBeEmpty = stringSearcher.rabinKarp("aa", "aaaaa");
        assertEquals("Does not work with patterns that overlap", 4, betterNotBeEmpty.size());
    }

    @Test(timeout = 200)
    public void testRabinKarpOther() {
        Integer[] blah = {9, 299, 442};
        List<Integer> expected = new ArrayList<>();
        for (Integer i : blah) {
            expected.add(i);
        }
        List<Integer> betterNotBeEmpty = stringSearcher.rabinKarp("um", loremIpsum);
        assertEquals("Does not work with larger string?", expected, betterNotBeEmpty);
    }
}