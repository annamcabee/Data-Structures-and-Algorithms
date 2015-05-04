import java.util.NoSuchElementException;

/**
 * ArrayQueue
 * Implementation of a queue using
 * an array as the backing structure
 *
 * @author Anna McAbee
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;
    private int front;
    private int back;

    /**
     * Construct an ArrayQueue with an
     * initial capacity of INITIAL_CAPACITY
     *
     * Use Constructor Chaining
     */
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayQueue with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayQueue(int initialCapacity) {
        size = 0;
        backing = (T[]) new Object[initialCapacity];
        front = 0;
        back = 0;

    }

    /**
     * Add a node with the given data
     * to the back of your queue.
     *
     * For array-backed implementations,
     * regrow the backing array if it is full.
     *
     * Must be O(1) (amortized for array-backed)
     *
     * @param data The data to add.
     * @throws IllegalArgumentException if data is null
     */
    @Override
    public void enqueue(T data) {
        if (data != null) {
            if (back == backing.length || back < front) {
                if (size == backing.length) {
                    T[] newBacking = (T[]) new Object[backing.length * 2];
                    for (int i = 0; i <= size; i++) {
                        if (front < size) {
                            newBacking[i] = backing[front];
                            front++;
                        } else {
                            front = 0;
                            newBacking[i] = backing[front++];
                        }
                    }
                    newBacking[size] = data;
                    backing = newBacking;
                    back++;
                    size++;
                    front = 0;
                } else if (front != 0) {
                    if (backing[0] == null) {
                        backing[0] = data;
                        back = 0;
                        size++;
                    } else if (backing[back + 1] == null) {
                        backing[back + 1] = data;
                        int newBack = back + 1;
                        back = newBack;
                        size++;
                    }
                }
            } else {
                backing[back++] = data;
                size++;
            }
        } else {
            throw new
                    IllegalArgumentException("Can't enqueue null on the queue");
        }
    }

    /**
     * Dequeue from the front of your queue.
     *
     * For array-backed implementations:
     * - you do not need to shrink the backing array.
     * - you must put null in the dequeued element's spot.
     *
     * Must be O(1)
     *
     * @return The data from the front of the queue.
     * @throws java.util.NoSuchElementException if the queue is empty
     */

    @Override
    public T dequeue() {
        if (!isEmpty()) {
            if (front < back || front == back) {
                T headData = backing[front];
                backing[front] = null;
                front++;
                size--;
                return headData;
            } else {
                front = 0;
                T headData = backing[front];
                backing[front] = null;
                front++;
                size--;
                return headData;
            }
        } else {
            throw new NoSuchElementException("The queue is empty");
        }
    }
    /**
     * Return the size of the queue.
     * Must be O(1)
     *
     * @return number of items in the queue
     */

    @Override
    public int size() {
        return size;
    }
    /**
     * Return true if empty. False otherwise.
     * Must be O(1)
     *
     * @return boolean representing whether the queue is empty
     */

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the backing array for your queue.
     * This is for grading purposes only. DO NOT EDIT THIS METHOD.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        return backing;
    }

}
