import java.util.NoSuchElementException;

public class MaxPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private MaxHeap<T> heap;
    // Do not add any more instance variables

    /**
     * Creates a MaxPriorityQueue
     */
    public MaxPriorityQueue() {
        heap = new MaxHeap<>();
    }
    /**
     * Adds an item to the priority queue
     *
     * @exception IllegalArgumentException if the item is null
     *
     * @param item the item to be added
     */
    @Override
    public void enqueue(T item) {
        if (item != null) {
            heap.add(item);
        } else  {
            throw new IllegalArgumentException("Cant enqueue null");
        }
    }
    /**
     * Removes and returns the first item in the priority queue
     *
     * @throws java.util.NoSuchElementException if the priority queue is empty
     * @return the item to be dequeued
     */
    @Override
    public T dequeue() {
        if (!isEmpty()) {
            return heap.remove();
        } else {
            throw new NoSuchElementException("Priority Queue is null");
        }
    }
    /**
     * Finds if the priority queue is empty
     * @return a boolean representing if the priority queue is empty
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
     * Finds the size of the priority queue
     * @return the size of the priority queue
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Clears the list
     */
    @Override
    public void clear() {
        heap.clear();
    }
    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing heap
     */
    @Override
    public MaxHeap<T> getBackingHeap() {
        return heap;
    }
}
