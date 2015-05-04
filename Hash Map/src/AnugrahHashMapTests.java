import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * HashMapTests
 *
 * @author Anugrah Vijay
 * @version 1.0
 */
public class AnugrahHashMapTests {

    private HashMap<Integer, String> map = new HashMap<>();

    private void makeMap() {
        map.add(1, "a");
        map.add(2, "b");
        map.add(3, "c");
        map.add(4, "d");
        map.add(5, "e");
    }

    @Test (timeout = 200)
    public void testBasicAdd() {
        // check starting size
        assertEquals((Object) 0, map.size());

        // Add elements
        assertNull(map.add(1, "a"));
        assertEquals((Object) 1, map.size());

        assertNull(map.add(2, "b"));
        assertEquals((Object) 2, map.size());

        assertNull(map.add(3, "c"));
        assertEquals((Object) 3, map.size());

        assertNull(map.add(4, "d"));
        assertEquals((Object) 4, map.size());

        assertNull(map.add(5, "e"));
        assertEquals((Object) 5, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(2, "b");
        expected[3] = new MapEntry<>(3, "c");
        expected[4] = new MapEntry<>(4, "d");
        expected[5] = new MapEntry<>(5, "e");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200)
    public void testAddProbing() {
        // adding multiple items with same hashIndex

        // Add elements
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "a"));
        assertNull(map.add(23, "a"));
        assertNull(map.add(34, "a"));

        // check size
        assertEquals((Object) 4, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(12, "a");
        expected[5] = new MapEntry<>(23, "a");
        expected[10] = new MapEntry<>(34, "a");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200)
    public void testAddDuplicateKey() {
        // setup
        makeMap();

        // add duplicate
        assertEquals("c", map.add(3, "cc"));

        // shouldn't update size
        assertEquals((Object) 5, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(2, "b");
        expected[3] = new MapEntry<>(3, "cc");
        expected[4] = new MapEntry<>(4, "d");
        expected[5] = new MapEntry<>(5, "e");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testAddNull() {
        // setup
        makeMap();
        map.add(null, null);
    }

    @Test (timeout = 200)
    public void testAddResize() {
        // setup
        makeMap();
        assertNull(map.add(6, "f"));
        assertNull(map.add(7, "g"));
        assertNull(map.add(12, "h"));
        assertNull(map.add(14, "i"));

        // check size
        assertEquals((Object) 9, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[23];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(2, "b");
        expected[3] = new MapEntry<>(3, "c");
        expected[4] = new MapEntry<>(4, "d");
        expected[5] = new MapEntry<>(5, "e");
        expected[6] = new MapEntry<>(6, "f");
        expected[7] = new MapEntry<>(7, "g");
        expected[12] = new MapEntry<>(12, "h");
        expected[14] = new MapEntry<>(14, "i");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200)
    public void testAddUpdate() {
        // add 3 elements with same hashIndex
        // remove first one
        // try to update last added one

        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));
        assertEquals("a", map.remove(1));

        // update key 34 and check size
        assertEquals("d", map.add(34, "e"));
        assertEquals((Object) 3, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(12, "b");
        expected[5] = new MapEntry<>(23, "c");
        expected[10] = new MapEntry<>(34, "e");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200)
    public void testAddFirstHashKeyRemoved() {
        // setup
        makeMap();
        assertEquals("a", map.remove(1));
        assertNull(map.add(1, "aa"));

        // check size
        assertEquals((Object) 5, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "aa");
        expected[2] = new MapEntry<>(2, "b");
        expected[3] = new MapEntry<>(3, "c");
        expected[4] = new MapEntry<>(4, "d");
        expected[5] = new MapEntry<>(5, "e");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200)
    public void testAddFirstHashKey() {
        // setup
        makeMap();
        assertEquals("b", map.add(2, "bb"));

        // check size
        assertEquals((Object) 5, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(2, "bb");
        expected[3] = new MapEntry<>(3, "c");
        expected[4] = new MapEntry<>(4, "d");
        expected[5] = new MapEntry<>(5, "e");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200)
    public void testAddRemove1() {
        // add 4 elements with same hashIndex
        // remove first one, add another element with the same hashIndex
        // should go to best index

        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));
        assertEquals("a", map.remove(1));

        // add item with hashIndex 1
        assertNull(map.add(45, "aa"));
        assertEquals((Object) 4, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(45, "aa");
        expected[2] = new MapEntry<>(12, "b");
        expected[5] = new MapEntry<>(23, "c");
        expected[10] = new MapEntry<>(34, "d");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        // setup
        makeMap();
        map.remove(null);
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        map.remove(4);
    }

    @Test (timeout = 200)
    public void testBasicRemove() {
        // setup
        makeMap();

        // remove all
        assertEquals("a", map.remove(1));
        assertEquals("b", map.remove(2));
        assertEquals("c", map.remove(3));
        assertEquals("d", map.remove(4));
        assertEquals("e", map.remove(5));

        // check size
        assertEquals((Object) 0, map.size());
    }

    @Test (timeout = 200)
    public void testRemoveProbing() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));

        // check size
        assertEquals((Object) 4, map.size());

        // remove some
        assertEquals("a", map.remove(1));
        assertEquals("d", map.remove(34));

        // check size
        assertEquals((Object) 2, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(12, "b");
        expected[5] = new MapEntry<>(23, "c");
        expected[10] = new MapEntry<>(34, "d");
        assertArrayEquals(expected, map.toArray());

        // check contains
        assertFalse(map.contains(1));
        assertTrue(map.contains(12));
        assertTrue(map.contains(23));
        assertFalse(map.contains(34));
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testRemoveNotPresent() {
        // setup
        makeMap();
        map.remove(-1);
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testRemovedIsRemoved() {
        // setup
        makeMap();
        assertEquals("a", map.remove(1));
        assertEquals("a", map.remove(1));
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testRemovedIsRemovedProbing() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));

        assertEquals("d", map.remove(34));
        assertEquals("d", map.remove(34));
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testGetNull() {
        // setup
        makeMap();
        map.get(null);
    }

    @Test (timeout = 200)
    public void testGetNormal() {
        // setup
        makeMap();

        // check get
        assertEquals("a", map.get(1));
        assertEquals("b", map.get(2));
        assertEquals("c", map.get(3));
        assertEquals("d", map.get(4));
        assertEquals("e", map.get(5));
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testGetNoSuchElement() {
        // setup
        makeMap();

        // check contains
        map.get(-1);
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testGetIsRemoved() {
        // setup
        makeMap();
        assertEquals("a", map.get(1));
        assertEquals("a", map.remove(1));

        // check get
        assertEquals("b", map.get(2));
        assertEquals("c", map.get(3));
        assertEquals("d", map.get(4));
        assertEquals("e", map.get(5));
        assertEquals("a", map.get(1));
    }

    @Test (timeout = 200)
    public void testGetProbing() {
        // Add elements
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "a"));
        assertNull(map.add(23, "a"));
        assertNull(map.add(34, "a"));

        // check contains
        assertEquals("a", map.get(1));
        assertEquals("a", map.get(12));
        assertEquals("a", map.get(23));
        assertEquals("a", map.get(34));
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testGetUpdate1() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));

        // remove some elements
        assertEquals("a", map.remove(1));
        assertEquals("d", map.remove(34));

        // shoudn't be able to get removed elements
        map.get(1);
        map.get(34);
    }

    @Test (timeout = 200)
    public void testGetUpdate2() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));

        // remove first elements
        assertEquals("a", map.remove(1));

        // check get for 34 - 4
        assertEquals("d", map.get(34));

        // update key 34
        assertEquals("d", map.add(34, "e"));

        // check get for new value and the others as well
        assertEquals("b", map.get(12));
        assertEquals("c", map.get(23));
        assertEquals("e", map.get(34));

    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        // setup
        makeMap();
        map.contains(null);
    }

    @Test (timeout = 200)
    public void testContainsNormal() {
        // setup
        makeMap();

        // check contains
        assertTrue(map.contains(1));
        assertTrue(map.contains(2));
        assertTrue(map.contains(3));
        assertTrue(map.contains(4));
        assertTrue(map.contains(5));
    }

    @Test (timeout = 200)
    public void testContainsFalse() {
        // setup
        makeMap();

        // check contains
        assertFalse(map.contains(-1));
        assertFalse(map.contains(100));
        assertFalse(map.contains(0));
        assertFalse(map.contains(6));
        assertFalse(map.contains(12));
    }

    @Test (timeout = 200)
    public void testContainsIsRemoved() {
        // setup
        makeMap();
        assertEquals("a", map.remove(1));

        // check contains
        assertFalse(map.contains(1));
        assertTrue(map.contains(2));
        assertTrue(map.contains(3));
        assertTrue(map.contains(4));
        assertTrue(map.contains(5));
    }

    @Test (timeout = 200)
    public void testContainsProbing() {
        // Add elements
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "a"));
        assertNull(map.add(23, "a"));
        assertNull(map.add(34, "a"));

        // check contains
        assertTrue(map.contains(1));
        assertTrue(map.contains(12));
        assertTrue(map.contains(23));
        assertTrue(map.contains(34));
    }

    @Test (timeout = 200)
    public void testContainsUpdate() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));
        assertEquals("a", map.remove(1));
        assertEquals("d", map.remove(34));

        // check contains
        assertFalse(map.contains(1));
        assertTrue(map.contains(12));
        assertTrue(map.contains(23));
        assertFalse(map.contains(34));
    }

    @Test (timeout = 200)
    public void testClear() {
        // setup
        makeMap();
        map.clear();
        assertEquals((Object) 0, map.size());

        map.add(1, "a");
        map.add(2, "b");
        map.add(12, "c");
        assertEquals((Object) 3, map.size());

        // check structure
        MapEntry<Integer, String>[] expected = new MapEntry[11];
        expected[1] = new MapEntry<>(1, "a");
        expected[2] = new MapEntry<>(2, "b");
        expected[5] = new MapEntry<>(12, "c");
        assertArrayEquals(expected, map.toArray());
    }

    @Test (timeout = 200)
    public void testKeySetEmpty() {
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, map.keySet());
    }

    @Test (timeout = 200)
    public void testKeySetNormal() {
        // setup
        makeMap();

        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        assertEquals(expected, map.keySet());
    }

    @Test (timeout = 200)
    public void testKetSetProbing() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "a"));
        assertNull(map.add(23, "a"));
        assertNull(map.add(34, "a"));

        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        expected.add(12);
        expected.add(23);
        expected.add(34);
        assertEquals(expected, map.keySet());
    }

    @Test (timeout = 200)
    public void testKeySetRemoved() {
        // setup
        makeMap();

        // remove some
        assertEquals("a", map.remove(1));
        assertEquals("e", map.remove(5));

        Set<Integer> expected = new HashSet<>();
        expected.add(2);
        expected.add(3);
        expected.add(4);
        assertEquals(expected, map.keySet());
    }

    @Test (timeout = 200)
    public void testKeySetRemovedProbing() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "a"));
        assertNull(map.add(23, "a"));
        assertNull(map.add(34, "a"));

        // remove some
        assertEquals("a", map.remove(1));
        assertEquals("a", map.remove(34));

        Set<Integer> expected = new HashSet<>();
        expected.add(12);
        expected.add(23);
        assertEquals(expected, map.keySet());
    }

    @Test (timeout = 200)
    public void testValuesEmpty() {
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(expected, map.values());
    }

    @Test (timeout = 200)
    public void testValuesNormal() {
        // setup
        makeMap();

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        expected.add("d");
        expected.add("e");
        assertEquals(expected, map.values());
    }

    @Test (timeout = 200)
    public void testValuesProbing() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        expected.add("d");
        assertEquals(expected, map.values());
    }

    @Test (timeout = 200)
    public void testValuesRemoved() {
        // setup
        makeMap();

        // remove some
        assertEquals("a", map.remove(1));
        assertEquals("e", map.remove(5));

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("b");
        expected.add("c");
        expected.add("d");
        assertEquals(expected, map.values());
    }

    @Test (timeout = 200)
    public void testValuesRemovedProbing() {
        // setup
        assertNull(map.add(1, "a"));
        assertNull(map.add(12, "b"));
        assertNull(map.add(23, "c"));
        assertNull(map.add(34, "d"));

        // remove some
        assertEquals("a", map.remove(1));
        assertEquals("d", map.remove(34));

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("b");
        expected.add("c");
        assertEquals(expected, map.values());
    }
}