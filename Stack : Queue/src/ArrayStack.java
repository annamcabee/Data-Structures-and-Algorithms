import java.util.NoSuchElementException;

/**
 * ArrayStack
 * Implementation of a stack using
 * an array as a backing structure
 *
 * @author Anna McAbee
 * @version 1.0
 */
public class ArrayStack<T> implements StackADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;

    /**
     * Construct an ArrayStack with
     * an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayStack with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayStack(int initialCapacity) {
        size = 0;
        this.backing = (T[]) new Object[initialCapacity];
    }

    /**
     * Push a new node onto the stack with the given data.
     *
     * For array-backed implementations,
     * regrow the backing array if it is full.
     *
     * Must be O(1) (amortized for array-backed)
     *
     * @param data The data to push.
     * @throws IllegalArgumentException if data is null.
     */
    @Override
    public void push(T data) {
        if (data != null) {
            if (size == backing.length) {
                T[] newBacking = (T[]) new Object[backing.length * 2];
                for (int i = 0; i < size; i++) {
                    newBacking[i] = backing[i];
                }
                newBacking[size++] = data;
                backing = newBacking;
            } else {
                backing[size] = data;
                size++;
            }
        } else {
            throw new IllegalArgumentException("Can't push null onto stack");
        }
    }

    /**
     * Pop from the stack.
     *
     * For array-backed implementations:
     * - you do not need to shrink the backing array
     * - you must put null in the popped element's slot.
     *
     * Must be O(1)
     *
     * @return Data from the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty.
     */
    @Override
    public T pop() {
        if (!isEmpty()) {
            T data = backing[size - 1];
            backing[size - 1] = null;
            size--;
            return data;
        } else {
            throw new NoSuchElementException("The stack is empty");
        }
    }

    /**
     * Return the size of the stack.
     * Must be O(1)
     *
     * @return number of items in the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return true if empty. False otherwise.
     * Must be O(1)
     *
     * @return boolean representing whether the list is empty
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
