/**
 * Created by kaijiehuang on 2/27/15.
 * To test the put() and remove() method of the SkipList
 * Consider possible extreme case like:
 * put method: numOfHeads + 1 > current height of the list (level of the head)
 * remove method: remove the only node in a level and thus the whole level is deleted.
 * remove method: no such element exception
 *
 *
 */
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class KJSkipListTest {
    private SkipListInterface<Integer> list;
    private CoinFlipper randomness;

    @Before
    public void setup() {
        randomness = new CoinFlipper(new Random(10));
        list = new SkipList<Integer>(randomness);
    }

// value of randomness  (h = HEADS, t = TAILS)
//            true   h
//            false  t
//            false  t
//            false  t
//            false  t
//            true   h
//            false  t
//            false  t
//            true   h
//            false  t
//            false  t
//            true   h
//            true   h
//            true   h
//            true   h
//            false  t


    @Test(timeout = 200)
    public void testStrongPut() {
        list.put(5);
        list.put(3);
        list.put(4);


        assertEquals(new Integer(3), list.first());
        assertEquals(new Integer(5), list.last());
        assertEquals(3, list.size());


        list.put(10);
        list.put(2);
        list.put(8);
        list.put(9);

        //    x 2     5   9
        //    x 2 3 4 5 8 9 10

        Node<Integer> curr = list.getHead();
        assertEquals(new Integer(2), curr.getNext().getData());
        assertEquals(new Integer(5), curr.getNext().getNext().getData());
        assertEquals(new Integer(9), curr.getNext().getNext().getNext().getData());
        assertNull(curr.getNext().getDown().getNext().getUp());
        assertEquals(2, curr.getLevel());


        list.put(6);
        list.put(7);
        //    x           7
        //    x           7
        //    x           7
        //    x 2     5   7   9
        //    x 2 3 4 5 6 7 8 9 10

        curr = list.getHead();
        assertEquals(new Integer(7), curr.getNext().getData());
        assertEquals(5, curr.getLevel());
        assertNull(curr.getNext().getUp());
        assertEquals(3,curr.getNext().getDown().getDown().getLevel());
        assertEquals(new Integer(5),curr.getNext().getDown().getDown().getDown().getPrev().getData());
        assertEquals(new Integer(9),curr.getNext().getDown().getDown().getDown().getNext().getData());
        assertEquals(new Integer(7),curr.getNext().getDown().getUp().getData());
        assertEquals(new Integer(2),curr.getDown().getDown().getDown().getNext().getData());

    }


    @Test(timeout = 200)
    public void testStrongRemove() {

        list.put(5);
        list.put(3);
        list.put(4);
        list.put(10);
        list.put(2);
        list.put(8);
        list.put(9);
        list.put(6);
        list.put(7);
        //    x           7
        //    x           7
        //    x           7
        //    x 2     5   7   9
        //    x 2 3 4 5 6 7 8 9 10


        assertEquals(9, list.size());
        assertEquals(new Integer(2), list.remove(2));
        //    x         7
        //    x         7
        //    x         7
        //    x     5   7   9
        //    x 3 4 5 6 7 8 9 10


        list.remove(7);
        //    x     5     9
        //    x 3 4 5 6 8 9 10
        assertEquals(2, list.getHead().getLevel());
        assertNull(list.getHead().getUp());
        assertEquals(new Integer(9), list.getHead().getNext().getNext().getData());

        list.remove(5);
        //    x         9
        //    x 3 4 6 8 9 10
        assertEquals(new Integer(9), list.getHead().getNext().getData());

        list.remove(9);
        //    x 3 4 6 8 10
        assertEquals(1, list.getHead().getLevel());


        list.remove(3);
        list.remove(4);
        list.remove(6);
        list.remove(8);
        list.remove(10);
        // list is empty with a phantom head

        assertEquals(0,list.size());
        assertEquals(1,list.getHead().getLevel());



    }

    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void testRemoveException() {
        list.put(5);
        list.put(3);
        list.put(4);
        list.remove(1);
    }

}