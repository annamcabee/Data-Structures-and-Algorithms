import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * HashMapStudentTests
 *
 * These tests are NOT exhaustive.
 * You should definitely write your own.
 *
 * @author Jonathan Jemson
 * @version 1.0
 */
public class HashMapStudentTests {

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
