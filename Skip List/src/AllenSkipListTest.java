import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.*;

/**
 * Tests may not be comprehensive. Some tests use randomness; run the tests
 * more than once to ensure correctness. Report any errors you find to Github,
 * or the Piazza comment thread for this test.
 *
 * There is one scenario that I'm unable to write a test for. See
 * https://piazza.com/class/i4ppb3mq17h2rh?cid=518.
 *
 * @author Allen Zheng
 * @version 1.4.1
 */
public class AllenSkipListTest {
    public static final int TIMEOUT = 200;

    // Change the seed below. -1 gives a random seed
    private static long seed = -1;

    private SkipListInterface<Integer> skipInt;
    private CoinFlipper coin;

    @Before
    public void setup() {
        if (seed == -1) {
            seed = System.currentTimeMillis() % 1000;
            System.out.printf("Seed is %d\n", seed);
        }
        coin = new CoinFlipper(new Random(seed));
        skipInt = new SkipList<Integer>(coin);
    }

    /* Exception tests */
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void putNull() {
        skipInt.put(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void getNull() {
        skipInt.get(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsNull() {
        skipInt.contains(null);
    }

    /* Empty skiplist tests */
    @Test(timeout = TIMEOUT)
    public void containsEmptyList() {
        assertFalse(skipInt.contains(7));
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getEmptyList() {
        skipInt.get(7);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeEmptyList() {
        skipInt.remove(7);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void firstEmptyList() {
        skipInt.first();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void lastEmptyList() {
        skipInt.last();
    }

    /**
     * Test structure of an empty skiplist. Should be a single phantom node at
     * level 1.
     */
    @Test(timeout = TIMEOUT)
    public void emptyStructure() {
        assertEquals(0, skipInt.size());
        assertSinglePhantom(skipInt.getHead());
    }

    /**
     * Checks that the given skiplist has a single phantom node at level one,
     * in other words, the structure of an empty skiplist.
     * @param head node to test
     */
    private void assertSinglePhantom(Node<?> head) {
        assertNotNull(head);
        assertNull(head.getData());
        assertNull(head.getUp());
        assertNull(head.getDown());
        assertNull(head.getPrev());
        assertNull(head.getNext());
        assertNull(head.getDown());
        assertEquals(1, head.getLevel());
    }

    /**
     * I didn't realize I forgot to include a test for the clear method until I
     * looked at Jay's tests.
     */
    @Test(timeout = TIMEOUT)
    public void clearedStructure() {
        skipInt.put(2);
        skipInt.put(4);
        skipInt.put(6);

        assertEquals(3, skipInt.size());

        skipInt.clear();

        assertEquals(0, skipInt.size());
        assertEquals(0, skipInt.dataSet().size());
        assertSinglePhantom(skipInt.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void singleInsertGetContains() {
        SkipList<TinyMapEntry> skipTM = new SkipList<TinyMapEntry>(coin);
        skipTM.put(new TinyMapEntry(7, "hello"));

        assertEquals(1, skipTM.size());

        TinyMapEntry getResult = skipTM.get(new TinyMapEntry(7, "hello"));
        assertEquals(7, (int) getResult.key);
        assertEquals("hello", getResult.value);

        assertTrue(skipTM.contains(new TinyMapEntry(7, "hello")));
        assertWellFormedSkipList(skipTM.getHead());
    }

    /**
     * Replace the data in a node that with more than one level.
     */
    @Test(timeout = TIMEOUT)
    public void replaceMultiLevel() {
        SkipList<TinyMapEntry> skipTM = new
            SkipList<TinyMapEntry>(new CoinFlipper(new Random(3)));

        skipTM.put(new TinyMapEntry(7, "hello"));

        skipTM.put(new TinyMapEntry(7, "hey"));

        TinyMapEntry newLookup = skipTM.get(new TinyMapEntry(7, "hey"));
        assertEquals(7, (int) newLookup.key);
        assertEquals("hey", newLookup.value);

        /* Expected structure:
         * (null) <7,"hey">
         * (null) <7,"hey">
         * (null) <7,"hey">
         * Apologies for the manual testing below
         */
        Node<TinyMapEntry> x1y1 = skipTM.getHead();
        assertNull(x1y1.getData());
        assertNull(x1y1.getUp());
        assertNotNull(x1y1.getDown());
        assertNull(x1y1.getPrev());
        assertNotNull(x1y1.getNext());

        Node<TinyMapEntry> x1y2 = x1y1.getDown();
        assertNull(x1y2.getData());
        assertNotNull(x1y2.getUp());
        assertNotNull(x1y2.getDown());
        assertNull(x1y2.getPrev());
        assertNotNull(x1y2.getNext());

        Node<TinyMapEntry> x1y3 = x1y2.getDown();
        assertNull(x1y3.getData());
        assertNotNull(x1y3.getUp());
        assertNull(x1y3.getDown());
        assertNull(x1y3.getPrev());
        assertNotNull(x1y3.getNext());

        Node<TinyMapEntry> x2y1 = x1y1.getNext();
        assertTrue(fullTmeEquals(x2y1.getData(), new TinyMapEntry(7, "hey")));
        assertNull(x2y1.getUp());
        assertNotNull(x2y1.getDown());
        assertNotNull(x2y1.getPrev());
        assertNull(x2y1.getNext());

        Node<TinyMapEntry> x2y2 = x1y2.getNext();
        assertTrue(fullTmeEquals(x2y2.getData(), new TinyMapEntry(7, "hey")));
        assertNotNull(x2y2.getUp());
        assertNotNull(x2y2.getDown());
        assertNotNull(x2y2.getPrev());
        assertNull(x2y2.getNext());

        Node<TinyMapEntry> x2y3 = x1y3.getNext();
        assertTrue(fullTmeEquals(x2y3.getData(), new TinyMapEntry(7, "hey")));
        assertNotNull(x2y3.getUp());
        assertNull(x2y3.getDown());
        assertNotNull(x2y3.getPrev());
        assertNull(x2y3.getNext());

        assertWellFormedSkipList(skipTM.getHead());
    }

    private boolean fullTmeEquals(TinyMapEntry a, TinyMapEntry b) {
        return a.key.equals(b.key) && a.value.equals(b.value);
    }

   /* @Test(timeout = TIMEOUT)
    public void removeMultiLevel() {
        SkipList<TinyMapEntry> skipTM = new
            SkipList<TinyMapEntry>(new CoinFlipper(new Random(3)));

        skipTM.put(new TinyMapEntry(7, "hello"));
        assertWellFormedSkipList(skipTM.getHead());

        TinyMapEntry removeResult = skipTM.remove(new TinyMapEntry(7,
                    "hello"));
        assertEquals(7, (int) removeResult.key);
        assertEquals("hello", removeResult.value);
        assertFalse(skipTM.contains(new TinyMapEntry(7, "hello")));

        assertSinglePhantom(skipTM.getHead());
    }*/

    /**
     * Run random insert/remove operations on SkipList, and check everything
     * against Java's SkipList implementation. Tests the put, remove, size,
     * first, last, contains, dataSet, and clear operations. Also test skiplist
     * structure after every iteration. This test takes ~7 seconds on my
     * computer. Decrease the number of trials if you're impatient, or your
     * computer is slow.
     */
   /* @Test(timeout = 15000)
    public void randomTestBattery() {
        Random aux = new Random(seed + 1);
        ConcurrentSkipListMap<Integer, Integer> check = new
            ConcurrentSkipListMap<>();

        *//* Aborted stream implementation, heh *//*
        *//* aux.doubles(500).forEach(random -> { *//*
        *//*     if (random < .55) { *//*
        *//*         list.add *//*

        for (int i = 0; i < 5000; i++) {
            if (aux.nextDouble() < .55) {
                int key = aux.nextInt(1000);
                check.put(key, key);
                skipInt.put(key);
            } else if (check.size() > 0) {
                Integer randomElem = (Integer)
                    check.keySet().toArray()[aux.nextInt(check.size())];
                check.remove(randomElem);
                assertEquals(randomElem, skipInt.remove(randomElem));
            }

            assertEquals(check.size(), skipInt.size());
            if (check.size() > 0) {
                assertEquals(check.firstKey(), skipInt.first());
                assertEquals(check.lastKey(), skipInt.last());
            } else {
                try {
                    skipInt.first();
                    fail("first() suceeded for empty list");
                } catch (NoSuchElementException e) { }
                try {
                    skipInt.last();
                    fail("last() suceeded for empty list");
                } catch (NoSuchElementException e) { }
            }

            assertWellFormedSkipList(skipInt.getHead());

            *//* Search entire address space :) *//*
            for(int j = 0; j < 1000; j++) {
                assertEquals(check.containsKey(j), skipInt.contains(j));
                if (check.containsKey(j)) {
                    assertEquals(check.get(j), skipInt.get(j));
                } else {
                    try {
                        skipInt.get(j);
                        fail("Lookup suceeded for nonexistent element");
                    } catch (NoSuchElementException e) {}
                }
            }

            assertEquals(check.keySet(), skipInt.dataSet());
        }

        skipInt.clear();
        assertEquals(0, skipInt.size());

        *//* Search entire address space again :) *//*
        for(int j = 0; j < 1000; j++) {
            assertEquals(false, skipInt.contains(j));
            try {
                skipInt.get(j);
                fail("Lookup suceeded for cleared skiplist");
            } catch (NoSuchElementException e) {}
        }

        assertSinglePhantom(skipInt.getHead());
        assertEquals(0, skipInt.dataSet().size());
    }*/

    /**
     * Ensure that the skip list has a valid structure. Checks if all the nodes
     * linked by next/prev have the same level, if all up/down nodes have the
     * same data, and checks mutual linkage of all nodes.
     * @param head head node of skiplist
     */
    private static void assertWellFormedSkipList(Node<?> head) {
        assertNull(head.getUp());

        Node<?> levelHead = head;
        Node<?> current = head;
        while (levelHead.getDown() != null) {
            assertNull(levelHead.getData());
            assertTrue(levelHead.getLevel()
                    == levelHead.getDown().getLevel() + 1);
            if (levelHead.getNext() == null) {
                fail("Phantom node in empty level");
            }
            while (current.getNext() != null) {
                assertMutualLinkage(current);
                assertEquals(levelHead.getLevel(), current.getLevel());
                assertNotNull(current.getDown());
                assertEquals(current.getDown().getData(), current.getData());
                current = current.getNext();
            }
            levelHead = levelHead.getDown();
        }

        assertEquals(1, levelHead.getLevel());
        current = levelHead;
        while (current.getNext() != null) {
            assertNull(current.getDown());
            assertMutualLinkage(current);
            current = current.getNext();
        }
    }

    /**
     * Ensure that any nodes that the given node links to also link to it.
     * @param node the node to test
     */
    private static void assertMutualLinkage(Node<?> node) {
        if (node.getUp() != null) {
            assertTrue(node.getUp().getDown() == node);
        }
        if (node.getDown() != null) {
            assertTrue(node.getDown().getUp() == node);
        }
        if (node.getPrev() != null) {
            assertTrue(node.getPrev().getNext() == node);
        }
        if (node.getNext() != null) {
            assertTrue(node.getNext().getPrev() == node);
        }
    }

    /**
     * Special test object where comparison/equality is defined only by key.
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

}
