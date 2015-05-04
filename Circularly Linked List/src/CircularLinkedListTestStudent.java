import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

public class CircularLinkedListTestStudent {

    private CircularLinkedList<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new CircularLinkedList<Integer>();
    }

    @Test(timeout = 200)
    public void testAddToFront() throws Exception {
        for (int i = 0; i < 3; i++) {
            list.addToFront(i);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals("int at index " + i + " not correct.",
                    (int) list.get(i), 2 - i);
        }
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void testAddToBack() throws Exception {
        for (int i = 0; i < 3; i++) {
            list.addToBack(i);
        }

        for (int i = 2; i >= 0; i--) {
            assertEquals("int at index " + i + " not correct.",
                    (int) list.get(i), i);
        }

        list.addToBack(null);
    }

    @Test(timeout = 200)
    public void testAddAtIndex() {
        for (int i = 0; i < 10; i++) {
            list.addAtIndex(0, i);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals("could not add at front correctly."
                            + Arrays.toString(list.toArray()), 9 - i,
                    (int) list.get(i));
        }

        list = new CircularLinkedList<Integer>();

        list.addToFront(5);
        list.addToFront(3);
        list.addToFront(1);

        list.addAtIndex(0, 0);
        list.addAtIndex(2, 2);
        list.addAtIndex(4, 4);
        list.addAtIndex(6, 6);

        for (int i = 0; i < list.size(); i++) {
            assertEquals("could not add at index " + i + " correctly. "
                    + Arrays.toString(list.toArray()), (int) list.get(i), i);
        }
    }

    @Test(timeout = 200)
    public void testGet() {

        //[0, 1, 2, ..., 19]//
        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("Could not get element at index " + i + ".",
                    (int) list.get(i), i);
        }

        //mixing it up a bit//


        assertEquals("Could not get element 9.", (int) list.get(9), 9);
        assertEquals("Could not get element 19.", (int) list.get(19), 19);
        assertEquals("Could not get element 0.", (int) list.get(0), 0);
    }

    @Test(timeout = 200)
    public void testRemoveAtIndex() {

        //test remove each different index//
        for (int i = 0; i < 20; i++) {
            list = new CircularLinkedList<Integer>();
            for (int j = 0; j < 20; j++) {
                list.addToBack(j);
            }

            list.removeAtIndex(i);

            int comparer = 0;
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    comparer++;
                }
                assertEquals("Element incorrect at index " + i + ", run " + j
                                + " " + Arrays.toString(list.toArray()), comparer,
                        (int) list.get(j));
                comparer++;
            }
        }

        //test removal from first index//

        list = new CircularLinkedList<Integer>();

        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("removal returned wrong value",
                    (int) list.removeAtIndex(0), i);

            for (int j = 0; j < list.size(); j++) {
                assertEquals("Element incorrect at index " + i + ", run " + j
                                + " " + Arrays.toString(list.toArray()), j + i + 1,
                        (int) list.get(j));
            }
        }

        //test removal from last index//

        list = new CircularLinkedList<Integer>();

        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("removal returned wrong value", list.size() - 1,
                    (int) list.removeAtIndex(list.size() - 1));

            for (int j = 0; j < list.size(); j++) {
                assertEquals("(test removal from last index) Element "
                                + "incorrect at index " + j + ".",
                        (int) list.get(j), j);
            }
        }

        //miscing it up a bit//

        list = new CircularLinkedList<Integer>();

        list.addToBack(8);
        list.addToBack(0);
        list.addToBack(10);
        list.addToBack(1);
        list.addToBack(15);
        list.addToBack(2);
        list.addToBack(-1);

        list.removeAtIndex(0);
        list.removeAtIndex(list.size() - 1);
        list.removeAtIndex(3);
        list.removeAtIndex(1);

        for (int i = 0; i < list.size(); i++) {
            assertEquals("(misc) Element incorrect at index " + i + ".", i,
                    (int) list.get(i));
        }
    }

    @Test(timeout = 200)
    public void testRemoveFromFront() {
        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("removal returned wrong value",
                    (int) list.removeFromFront(), i);

            for (int j = 1; j < list.size(); j++) {
                assertEquals("Element incorrect at index " + j + ", removal "
                                + i + ". " + Arrays.toString(list.toArray()), j + 1 + i,
                        (int) list.get(j));
            }
        }

        assertNull("removeFromFront() did not return null on an empty list",
                new CircularLinkedList().removeFromFront());
    }

    @Test(timeout = 200)
    public void testRemoveFromBack() {
        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("removal returned wrong value",
                    (int) list.removeFromBack(), 19 - i);

            for (int j = 0; j < list.size(); j++) {
                assertEquals("Element incorrect at index " + j
                        + ".", (int) list.get(j), j);
            }
        }

        assertNull("removeFromBack() did not return null on an empty list",
                new CircularLinkedList().removeFromFront());
    }

    @Test(timeout = 200)
    public void testToArray() {
        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
        }

        Object[] testArray = new Object[20];

        for (int i = 0; i < 20; i++) {
            testArray[i] = i;
        }

        org.junit.Assert.assertArrayEquals("arrays not equal", list.toArray(),
                testArray);
    }

    @Test(timeout = 200)
    public void testIsEmpty() {
        assertTrue("isEmpty() incorrect before anything added to it",
                list.isEmpty());
        assertNull("head not null when empty.", list.getHead());

        list.addToBack(0);
        assertFalse("isEmpty() incorrect after one element added to back.",
                list.isEmpty());

        list.removeFromBack();
        assertTrue("isEmpty incorrect after elements removed from back.",
                list.isEmpty());
        assertNull("head not null when empty.", list.getHead());

        list.addToFront(0);
        assertFalse("isEmpty() incorrect after one element added to front.",
                list.isEmpty());

        list.removeFromFront();
        assertTrue("isEmpty() incorrect after elements removed from front.",
                list.isEmpty());
        assertNull("head not null when empty.", list.getHead());

        list.addAtIndex(0, 0);
        assertFalse("isEmpty() incorrect after one element added at index (0).",
                list.isEmpty());

        list.removeAtIndex(0);
        assertTrue("isEmpty() incorrect after elements removed at index (0)",
                list.isEmpty());
        assertNull("head not null when empty.", list.getHead());
    }

    @Test(timeout = 200)
    public void testSize() {
        assertEquals("size incorrect before anything addded.", list.size(), 0);
        assertNull("head not null when empty.", list.getHead());

        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
            assertEquals("size incorrect when " + i + "th element added.",
                    list.size(), i + 1);
        }

        for (int i = 0; i < list.size(); i++) {
            list.removeFromFront();
            assertEquals("size incorrect when " + i + "th element removed.",
                    list.size(), 19 - i);
        }
    }

    @Test(timeout = 200)
    public void testClear() {

        //testing for breakage in edge case//
        list.clear();

        for (int i = 0; i < 20; i++) {
            list.addToBack(i);
        }

        list.clear();

        assertTrue("list not cleared", list.isEmpty());
    }

    /**
     * TESTING EXCEPTIONS
     */

    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testIndexAdd1() {
        list.addAtIndex(-1, 0);
    }

    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testIndexAdd2() {
        list.addAtIndex(1, 0);
    }

    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testIndexAdd3() {
        list.addToBack(0);
        list.addAtIndex(2, 0);
    }

    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testIndexAdd4() {
        list.addToBack(0);
        list.removeFromBack();
        list.addAtIndex(1, 0);
    }

    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testIndexAdd5() {
        list.removeFromBack();
        list.removeFromBack();
        list.addAtIndex(0, 0);
        list.addAtIndex(-1, 0);
    }

    @Test(timeout = 200, expected = java.lang.IllegalArgumentException.class)
    public void testNullAdd1() {
        list.addToBack(null);
    }

    @Test(timeout = 200, expected = java.lang.IllegalArgumentException.class)
    public void testNullAdd2() {
        list.addToFront(null);
    }

    @Test(timeout = 200, expected = java.lang.IllegalArgumentException.class)
    public void testNullAdd3() {
        list.addAtIndex(0, null);
    }

    @Test(timeout = 200, expected = java.lang.IndexOutOfBoundsException.class)
    public void testGet1() {
        list.get(0);
    }

    @Test(timeout = 200, expected = java.lang.IndexOutOfBoundsException.class)
    public void testGet2() {
        list.removeFromBack();
        list.removeFromBack();
        list.get(-1);
    }

    @Test(timeout = 200, expected = java.lang.IndexOutOfBoundsException.class)
    public void testGet3() {
        list.addToBack(0);
        list.removeFromBack();
        list.get(0);
    }

    @Test(timeout = 200, expected = java.lang.IndexOutOfBoundsException.class)
    public void removeTest1() {
        list.removeFromBack();
        list.removeFromBack();
        list.removeAtIndex(-1);
    }

    @Test(timeout = 200, expected = java.lang.IndexOutOfBoundsException.class)
    public void removeTest2() {
        list.addToBack(0);
        list.removeFromBack();
        list.removeAtIndex(0);
    }

}