import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.TreeSet;

/**
 * @author Jay Kamat
 * @version 0.3
 *
 * Licensed under the GNU GPLv3
 *
 * Based upon Allen's Skiplist Tests!
 *
 * This test provides ABSOLUTELY NO GUARANTEES that things work, and
 * is meant to be used WITH rather than instead of other tests on piazza.
 */
public class JaySkipListTest {

    // My processor might as well be a cat in a box
    public static final int TIMEOUT = 999999999;

    // -1 means random
    private static long seed = -1;

    private SkipListInterface<Integer> skipList;
    private CoinFlipper coin;
    private Random r;
    // bigger is better!
    private static final int TEST_ITERATIONS = 100;

    @Before
    public void setup() {
        if (seed == -1) {
            seed = System.currentTimeMillis() % 1000;
            System.out.printf("Seed is %d\n", seed);
        }
        coin = new CoinFlipper(new Random(seed));
        skipList = new SkipList<Integer>(coin);

        r = new Random();
    }


    @Test(timeout = 2000)
    public void testSizeOnRemove() {
        skipList = new SkipList<>(coin);

        skipList.put(4);
        skipList.put(8);
        skipList.put(2);
        skipList.put(2);
        skipList.put(2);
        skipList.put(2);
        skipList.put(4);
        skipList.put(6);

        skipList.remove(4);
        skipList.remove(8);
        skipList.remove(2);
        skipList.remove(6);

        assertEquals(0, skipList.size());

        // This should work regardless of your set implementation, but if it
        // fails, switch 'TreeSet' to your set implementation!
        assertEquals(new TreeSet<Integer>(), skipList.dataSet());

        Node<Integer> head = skipList.getHead();

        assertNull(head.getUp());
        assertNull(head.getDown());
        assertNull(head.getPrev());
        assertNull(head.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        skipList = new SkipList<>(coin);

        skipList.put(4);
        skipList.put(8);
        skipList.put(2);
        skipList.put(2);
        skipList.put(2);
        skipList.put(2);
        skipList.put(4);
        skipList.put(6);

        skipList.clear();

        assertEquals(0, skipList.size());

        // This should work regardless of your set implementation, but if it
        // fails, switch 'TreeSet' to your set implementation!
        assertEquals(new TreeSet<Integer>(), skipList.dataSet());

        Node<Integer> head = skipList.getHead();
        assertNull(head.getUp());
        assertNull(head.getDown());
        assertNull(head.getPrev());
        assertNull(head.getNext());
        assertEquals(1, head.getLevel());
    }


    /**
     * For some reason, this was a problem for me for about 2 hours
     */
    @Test(timeout = TIMEOUT)
    public void testAggressiveReplace() {
        // this test runs list.put(<an object that is equal to all others>) repeatedly, then a single
        // remove to see if the entire list is cleared

        SkipList<StupidEqualsObject> skipList2;

        for (int i = 1; i < TEST_ITERATIONS; i++) {
            StupidEqualsObject last = new StupidEqualsObject(r.nextInt(Integer.MAX_VALUE));
            skipList2 = new SkipList<>(new CoinFlipper());


            System.out.println("\n\n");

            for (int j = 0; j < i; j++) {
                // All these values are 'equal' but have different true values
                skipList2.put(new StupidEqualsObject(r.nextInt(Integer.MAX_VALUE)));
            }
            skipList2.put(last);

            TreeSet<StupidEqualsObject> test = new TreeSet<>();
            test.add(new StupidEqualsObject(r.nextInt(Integer.MAX_VALUE)));
            // should have only a single data entry in the set
            assertEquals(1, skipList2.size());
            assertEquals(test, skipList2.dataSet());
            assertTrue(skipList2.contains(last));

            Node<StupidEqualsObject> head = skipList2.getHead();
            head = head.getNext();

            while (head != null) {
                assertTrue(last.getReal() == head.getData().getReal());
                head = head.getDown();
            }


            skipList2.remove(new StupidEqualsObject(r.nextInt(Integer.MAX_VALUE)));
            assertEquals(0, skipList2.size());

            // This should work regardless of your set implementation, but if it
            // fails, switch 'TreeSet' to your set implementation!
            assertEquals(new TreeSet<StupidEqualsObject>(), skipList2.dataSet());

            head = skipList2.getHead();

            assertNull(head.getUp());
            assertNull(head.getDown());
            assertNull(head.getPrev());
            assertNull(head.getNext());
        }
    }


    /**
     * The first part is stolen from Allen, I wanted to check if size wasn't
     * getting increased if replacing too!
     */
    @Test(timeout = TIMEOUT)
    public void testSizeOnReplace() {

        SkipList<TinyMapEntry> skipTM = new
                SkipList<>(new CoinFlipper(new Random(3)));


        skipTM.put(new TinyMapEntry(7, "hello"));


        skipTM.put(new TinyMapEntry(7, "hey"));

        TinyMapEntry newLookup = skipTM.get(new TinyMapEntry(7, "hey"));
        assertEquals(7, (int) newLookup.key);
        assertEquals("hey", newLookup.value);

        assertEquals(1, skipTM.size());

    }


    /**
     * Special test object where comparison/equality is defined only by key.
     *
     * @author Allen Zheng
     */
    private static class TinyMapEntry implements Comparable<TinyMapEntry> {
        public Integer key;
        public String value;

        public TinyMapEntry(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(TinyMapEntry other) {
            return key.compareTo(other.key);
        }

        @Override
        public boolean equals(Object other) {
            if (other == null || !(other instanceof TinyMapEntry))
                return false;
            return key.equals(((TinyMapEntry) other).key);
        }

        @Override
        public String toString() {
            return String.format("<%s,%s>", key.toString(), value.toString());
        }
    }


    /**
     * Special test object where comparison/equality is defined only by key.
     *
     * @author Allen Zheng
     */
    private static class StupidEqualsObject implements Comparable<StupidEqualsObject> {
        public Integer key;

        public StupidEqualsObject(Integer key) {
            this.key = key;
        }

        public int compareTo(StupidEqualsObject other) {
            return 0;
        }

        @Override
        public boolean equals(Object other) {
            return true;
        }

        public int getReal() {
            return key;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
}