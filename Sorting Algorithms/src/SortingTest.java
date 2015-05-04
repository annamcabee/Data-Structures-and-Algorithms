import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SortingTest {
    // These tests cover:
    // * Empty inputs
    // * Inputs with one value
    // * A "typical" unsorted array
    // * Proper stability (when applicable)

    private Sorting sorter;
    private Comparator<KVPair> comparator;

    @Before
    public void setUp() throws Exception {
        sorter = new Sorting();
        comparator = new Comparator<KVPair>() {
            @Override
            public int compare(KVPair o1, KVPair o2) {
                return o1.compareTo(o2);
            }
        };
    }

    private KVPair[] makeArray() {
        // KVPair is sorted by ints. The
        return new KVPair[] {
            new KVPair(4, "a"),
            new KVPair(1, "a"),
            new KVPair(2, "a"),
            new KVPair(0, "a"),
            new KVPair(6, "a"),
            new KVPair(4, "b"),
            new KVPair(3, "a"),
            new KVPair(4, "c"),
            new KVPair(1, "b"),
            new KVPair(4, "d"),
            new KVPair(5, "a"),
            new KVPair(6, "b"),
            new KVPair(4, "e"),
        };
    }

    private <T extends Comparable<? super T>> void assertUnstableSorted(
            T[] arr) {
        // Some sort aren't stable so we can't check against Arrays.sort().
        for (int i = 1; i < arr.length; i++) {
            assertTrue(arr[i - 1] + " must be <= " + arr[i], arr[i - 1]
                    .compareTo(arr[i]) <= 0);
        }
    }

    private int[] makeIntArray() {
        int size = 10000;
        // Change this seed to try different arrays on each test.
        Random r = new Random(1337);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            if (r.nextBoolean()) {
                arr[i] = r.nextInt();
            } else {
                arr[i] = r.nextInt() * -1;
            }
        }

        return arr;
    }

    @Test(timeout = 200)
    public void testBubblesortEmpty() {
        KVPair[] arr = new KVPair[0];

        sorter.bubblesort(arr, comparator);
        assertEquals(arr.length, 0);
    }

    @Test(timeout = 200)
    public void testInsertionSortEmpty() {
        KVPair[] arr = new KVPair[0];

        sorter.insertionsort(arr, comparator);
        assertEquals(arr.length, 0);
    }


    @Test(timeout = 200)
    public void testMergeSortEmpty() {
        KVPair[] arr = new KVPair[0];

        sorter.mergesort(arr, comparator);
        assertEquals(arr.length, 0);
    }

    @Test(timeout = 200)
    public void testQuickSortEmpty() {
        KVPair[] arr = new KVPair[0];

        sorter.quicksort(arr, comparator,new Random());
        assertEquals(arr.length, 0);
    }

    @Test(timeout = 200)
    public void testRadixSortEmpty() {
        int[] arr = new int[0];

        sorter.radixsort(arr);
        assertEquals(arr.length, 0);
    }

    @Test(timeout = 200)
    public void testBubblesortOne() {
        KVPair value = new KVPair(0, "a");
        KVPair[] arr = new KVPair[] {value};

        sorter.bubblesort(arr, comparator);
        assertEquals(arr.length, 1);
        assertEquals(value.toString(), arr[0], value);
    }

    @Test(timeout = 200)
    public void testInsertionSortOne() {
        KVPair value = new KVPair(0, "a");
        KVPair[] arr = new KVPair[] {value};

        sorter.insertionsort(arr, comparator);
        assertEquals(arr.length, 1);
        assertEquals(String.valueOf(value), arr[0], value);
    }



    @Test(timeout = 200)
    public void testMergeSortOne() {
        KVPair value = new KVPair(0, "a");
        KVPair[] arr = new KVPair[] {value};

        sorter.mergesort(arr, comparator);
        assertEquals(arr.length, 1);
        assertEquals(String.valueOf(value), arr[0], value);
    }

    @Test(timeout = 200)
    public void testQuickSortOne() {
        KVPair value = new KVPair(0, "a");
        KVPair[] arr = new KVPair[] {value};

        sorter.quicksort(arr, comparator, new Random());
        assertEquals(arr.length, 1);
        assertEquals(String.valueOf(value), arr[0], value);
    }

    @Test(timeout = 200)
    public void testRadixSortOne() {
        int value = 0;
        int[] arr = new int[] {value};
        sorter.radixsort(arr);
        assertEquals(arr.length, 1);
        assertEquals(String.valueOf(value), arr[0], value);
    }

    @Test(timeout = 200)
    public void testBubblesort() {
        KVPair[] arr = makeArray();
        KVPair[] sorted = makeArray();
        Arrays.sort(sorted);
        SortingForTest s = new SortingForTest();
        //s.bubblesort(arr, comparator);
        assertArrayEquals(Arrays.toString(arr), sorted, arr);
    }

    @Test(timeout = 200)
    public void testInsertionsort() {
        KVPair[] arr = makeArray();
        KVPair[] sorted = makeArray();
        Arrays.sort(sorted);
        SortingForTest s = new SortingForTest();
       // s.insertionsort(arr, comparator);
        assertArrayEquals(Arrays.toString(arr), sorted, arr);
    }


    @Test(timeout = 200)
    public void testQuicksort() {
        KVPair[] arr = makeArray();
        KVPair[] sorted = makeArray();
        Arrays.sort(sorted);
        sorter.quicksort(arr, comparator, new Random());
        assertUnstableSorted(arr);
    }

    @Test(timeout = 200)
    public void testMergesort() {
        KVPair[] arr = makeArray();
        KVPair[] sorted = makeArray();
        Arrays.sort(sorted);
        SortingForTest s = new SortingForTest();
     //   s.mergesort(arr, comparator);
        assertArrayEquals(Arrays.toString(arr), sorted, arr);
    }

    @Test(timeout = 200)
    public void testSelection() {
        KVPair[] arr = makeArray();
        KVPair[] sorted = makeArray();
        Arrays.sort(sorted);
        SortingForTest s = new SortingForTest();
     //   s.selectionsort(arr, comparator);
        assertUnstableSorted(arr);
    }


    private class KVPair implements Comparable<KVPair> {
        private final int key;
        private final String value;

        public KVPair(int key, String value) {
            this.key = key;
            if (value == null) {
                throw new IllegalArgumentException(
                        "Bad tester! No null values.");
            }
            this.value = value;
        }

        @Override
        public int compareTo(KVPair o) {
            return key - o.key;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof KVPair) {
                KVPair otherPair = (KVPair) o;
                return key == otherPair.key && value.equals(otherPair.value);
            }
            return false;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }
}
