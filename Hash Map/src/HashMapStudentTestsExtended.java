import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * HashMapStudentTests
 *
 * Extension  of the standard tests. This is meant to be more extensive than
 * the original unit tests.
 *
 * @author Jonathan Jemson
 * @author Joshua Songy
 * @version 3.0
 */
public class HashMapStudentTestsExtended {

    private HashMap<MyString, String> directory;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        directory = new HashMap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        directory.add(new MyString("Jonathan"), "TA: 1332");
        directory.add(new MyString("Monica"), "Professor: 2050");
        directory.add(new MyString("David"), "Professor: 1371");
        String prof = directory.add(new MyString("Monica"), "Professor: 1332");
        assertEquals("Professor: 2050", prof);
        directory.add(new MyString("Saikrishna*"), "TA: 1332");
        directory.add(new MyString("BestLang"), "C++");
        String badLanguage = directory.add(new MyString("BestLang"), "Swift");
        assertEquals("Swift is better than C++", "C++", badLanguage);

        assertEquals(5, directory.size());

        MapEntry<MyString, String>[] expected = expectedAddStuffBacking();
        assertArrayEquals(expected, directory.toArray());
    }

    @Test (timeout = TIMEOUT)
    public void testAddWithProbe() {
        directory.add(new MyString("Emily"), "TA: 1332");
        directory.add(new MyString("James"), "TA: 1332 (again)");
        directory.add(new MyString("Siddu"), "TA: 1332 (another one)");

        assertEquals(3, directory.size());

        @SuppressWarnings("unchecked")
        MapEntry<MyString, String>[] expected = new MapEntry[11];

        expected[5] = new MapEntry<>(new MyString("Emily"), "TA: 1332");
        expected[6] = new MapEntry<>(new MyString("James"), "TA: 1332 (again)");
        expected[9] = new MapEntry<>(new MyString("Siddu"), "TA: 1332 (another one)");

        assertArrayEquals(expected, directory.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        addStuff();

        directory.add(new MyString("Albert Morlan*"),
                "TA: 1332"); // The * is for testing purposes
        directory.add(new MyString("Stephen Conway"), "TA: 2050");
        directory.add(new MyString("ABC"), "DEF!");

        @SuppressWarnings("unchecked")
        MapEntry<MyString, String>[] expected = new MapEntry[23];

        expected[3] = new MapEntry<>(new MyString("ABC"), "DEF!");
        expected[5] = new MapEntry<>(new MyString("David"), "Professor: 1371");
        expected[6] = new MapEntry<>(new MyString("Monica"), "Professor: 1332");
        expected[8] = new MapEntry<>(new MyString("Jonathan"), "TA: 1332");
        expected[9] = new MapEntry<>(new MyString("BestLang"), "Swift");
        expected[10] = new MapEntry<>(new MyString("Saikrishna"), "TA: 1332");
        expected[14] = new MapEntry<>(new MyString("Albert Morlan*"),
                "TA: 1332");
        expected[15] = new MapEntry<>(new MyString("Stephen Conway"),
                "TA: 2050");

        assertArrayEquals(expected, directory.toArray());
        assertEquals(8, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        addStuff();

        assertEquals("TA: 1332", directory.remove(new MyString("Jonathan")));

        MapEntry<MyString, String>[] actuals = directory.toArray();
        assertNotNull(actuals[8]);
        assertTrue(actuals[8].isRemoved());
        assertEquals(4, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveEdgeCase() {
        /*
         ***********************************
         * THERE ARE MANY OTHER EDGE CASES *
         *     THIS IS ONLY ONE OF THEM    *
         ***********************************
         */

        // This part is the same as testRemove()
        addStuff();

        assertEquals("TA: 1332", directory.remove(new MyString("Jonathan")));

        MapEntry<MyString, String>[] actuals = directory.toArray();
        assertNotNull(actuals[8]);
        assertTrue(actuals[8].isRemoved());
        assertEquals(4, directory.size());

        // Here's the edge case part
        directory.add(new MyString("Mitchell"), "TA: 1332*");
        actuals = directory.toArray();
        assertNotNull(actuals[8]);
        assertFalse(actuals[8].isRemoved());
        assertEquals(actuals[8], new MapEntry<>(new MyString("Mitchell"),
                "TA: 1332*"));
    }

    /**
     * Test for quadratic probing, as required by the assignment.
     */
    @Test(timeout = TIMEOUT)
    public void testQuadraticPlacement() {
        // Holds the expected array.
        MapEntry<MyString, String>[] expected = new MapEntry[11];
        // Holds actual array.
        MapEntry<MyString, String>[] actuals = null;

        // Add entries, check for consistency.
        // 1
        expected[8] = new MapEntry<>(new MyString("Mitchel1"), "TA: 1332");
        directory.add(new MyString("Mitchel1"), "TA: 1332");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());

        // 2
        expected[9] = new MapEntry<>(new MyString("Mitchel2"), "TA: 1332");
        directory.add(new MyString("Mitchel2"), "TA: 1332");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());

        // 3
        expected[1] = new MapEntry<>(new MyString("Mitchel3"), "TA: 1332");
        directory.add(new MyString("Mitchel3"), "TA: 1332");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());

        // 4
        expected[6] = new MapEntry<>(new MyString("Mitchel4"), "TA: 1332");
        directory.add(new MyString("Mitchel4"), "TA: 1332");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());

        // 5
        expected[2] = new MapEntry<>(new MyString("Mitchel5"), "TA: 1332");
        directory.add(new MyString("Mitchel5"), "TA: 1332");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());

        // 6
        expected[0] = new MapEntry<>(new MyString("Mitchel6"), "TA: 1332");
        directory.add(new MyString("Mitchel6"), "TA: 1332");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());

        // We do not do another test, as it would cause a resize. We factor
        // that out to the next test.
    }

    /**
     * Tests to make sure that when n iterations of quadratic searching is done
     * a resize is performed.
     */
    @Test(timeout = TIMEOUT)
    public void testQuadraticPlacementLoopResize() {
        // Holds the expected array.
        MapEntry<MyString, String>[] expected = new MapEntry[11];
        // Holds actual array.
        MapEntry<MyString, String>[] actuals = null;

        expected[0] = new MapEntry<>(new MyString("Mitchel6"), "TA: 1332");
        expected[1] = new MapEntry<>(new MyString("Mitchel3"), "TA: 1332");
        expected[2] = new MapEntry<>(new MyString("Mitchel5"), "TA: 1332");
        expected[6] = new MapEntry<>(new MyString("Mitchel4"), "TA: 1332");
        expected[8] = new MapEntry<>(new MyString("Mitchel1"), "TA: 1332");
        expected[9] = new MapEntry<>(new MyString("Mitchel2"), "TA: 1332");

        directory.add(new MyString("Mitchel1"), "TA: 1332");
        directory.add(new MyString("Mitchel2"), "TA: 1332");
        directory.add(new MyString("Mitchel3"), "TA: 1332");
        directory.add(new MyString("Mitchel4"), "TA: 1332");
        directory.add(new MyString("Mitchel5"), "TA: 1332");
        directory.add(new MyString("Mitchel6"), "TA: 1332");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());
        assertEquals(11, actuals.length);

        // Before the resize, the arrays should be the same.
        assertArrayEquals(expected, directory.toArray());

        // No spot should be found, this should cause a resize.
        expected[0] = new MapEntry<>(new MyString("Mitchel7"), "TA: 1332");
        directory.add(new MyString("Mitchel7"), "TA: 1332");
        actuals = directory.toArray();

        // After the resize, both arrays should be size 23.
        assertEquals(23, actuals.length);

        // Create the new array to check against.
        boolean[] expectedb = new boolean[23];
        for (int i = 0; i < 23; i++) {
            expectedb[i] = false;
        }
        expectedb[8] = true;
        expectedb[9] = true;
        expectedb[12] = true;
        expectedb[17] = true;
        expectedb[1] = true;
        expectedb[10] = true;
        expectedb[21] = true;

        // Check the array.
        for (int i = 0; i < 23; i++) {
            if (expectedb[i]) {
                assertNotNull(actuals[i]);
            } else {
                assertEquals(null, actuals[i]);
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testDeadSpaceRemoval() {
        // Holds the expected array.
        MapEntry<MyString, String>[] expected = new MapEntry[11];
        // Holds actual array.
        MapEntry<MyString, String>[] actuals = null;

        // Add three entries.
        expected[8] = new MapEntry<>(new MyString("Mitchel1"), "TA: 1332-1");
        expected[9] = new MapEntry<>(new MyString("Mitchel2"), "TA: 1332-2");
        expected[1] = new MapEntry<>(new MyString("Mitchel3"), "TA: 1332-3");

        directory.add(new MyString("Mitchel1"), "TA: 1332-1");
        directory.add(new MyString("Mitchel2"), "TA: 1332-2");
        directory.add(new MyString("Mitchel3"), "TA: 1332-3");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());
        assertEquals(11, actuals.length);

        // First, check removal of the middle entry.
        assertEquals("TA: 1332-2", directory.remove(new MyString("Mitchel2")));
        actuals = directory.toArray();
        assertEquals(true, actuals[9].isRemoved());

        // Next, check to make sure that replacing the third element does not
        // cause the removed space to be used.
        assertEquals("TA: 1332-3", directory.add(new MyString("Mitchel3"), "TA: 1332-4"));
        actuals = directory.toArray();
        assertEquals(true, actuals[9].isRemoved());
        assertEquals("TA: 1332-4", actuals[1].getValue());

        // Next, check that the second element will get replaced if there
        // was not already a key in the map.
        assertEquals(null, directory.add(new MyString("Mitchel4"), "TA: 1332-5"));
        expected[9] = new MapEntry<>(new MyString("Mitchel4"), "TA: 1332-5");
        expected[1] = new MapEntry<>(new MyString("Mitchel3"), "TA: 1332-4");
        actuals = directory.toArray();
        assertArrayEquals(expected, directory.toArray());
        assertEquals(11, actuals.length);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        addStuff();
        assertEquals("TA: 1332", directory.get(new MyString("Saikrishna")));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        directory.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotFound() {
        directory.get(new MyString(""));
    }

    /**
     * Add a baseline of items to the hash map.
     */
    private void addStuff() {
        directory.add(new MyString("Jonathan"), "TA: 1332");
        directory.add(new MyString("Monica"), "Professor: 1332");
        directory.add(new MyString("David"), "Professor: 1371");
        directory.add(new MyString("Saikrishna"), "TA: 1332");
        directory.add(new MyString("BestLang"), "Swift");
    }

    /**
     * Checks to make sure that nodes marked 'removed' have their key and value
     * set to null, to aid the Garbage Collector.
     */
    @Test(timeout = TIMEOUT)
    public void testNotGCFriendly() {
        // Holds actual array.
        MapEntry<MyString, String>[] actuals = null;

        directory.add(new MyString("Mitchel1"), "TA: 1332");
        assertEquals("TA: 1332", directory.remove(new MyString("Mitchel1")));
        actuals = directory.toArray();
        assertEquals(true, actuals[8].isRemoved());
        assertNotNull(actuals[8].getKey());
        assertNotNull(actuals[8].getValue());
    }

    /**
     * Get the expected positions and ordering of the entries in the hash map.
     *
     * @return array with expected entries with the baseline
     */
    private static MapEntry<MyString, String>[] expectedAddStuffBacking() {
        @SuppressWarnings("unchecked")
        MapEntry<MyString, String>[] expected = new MapEntry[11];

        expected[0] = new MapEntry<>(new MyString("Saikrishna*"), "TA: 1332");
        expected[5] = new MapEntry<>(new MyString("David"), "Professor: 1371");
        expected[6] = new MapEntry<>(new MyString("Monica"), "Professor: 1332");
        expected[8] = new MapEntry<>(new MyString("Jonathan"), "TA: 1332");
        expected[9] = new MapEntry<>(new MyString("BestLang"), "Swift");
        return expected;
    }

    private static class MyString {
        private String s;

        /**
         * Create a wrapper object around a String object, for the purposes
         * of controlling the hash code.
         *
         * @param s string to store in this object
         */
        public MyString(String s) {
            this.s = s;
        }

        @Override
        public int hashCode() {
            return s.length();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof MyString) {
                return s.equals(((MyString) o).s);
            }
            if (o instanceof String) {
                return s.equals(o);
            }
            return false;
        }

        @Override
        public String toString() {
            return s;
        }
    }
}