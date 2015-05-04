public interface PriorityQueueInterface<T extends Comparable<? super T>> {
    /**
     * Adds an item to the priority queue
     *
     * @exception IllegalArgumentException if the item is null
     *
     * @param item the item to be added
     */
    public void enqueue(T item);

    /**
     * Removes and returns the first item in the priority queue
     *
     * @throws java.util.NoSuchElementException if the priority queue is empty
     * @return the item to be dequeued
     */
    public T dequeue();

    /**
     * Finds if the priority queue is empty
     * @return a boolean representing if the priority queue is empty
     */
    public boolean isEmpty();

    /**
     * Finds the size of the priority queue
     * @return the size of the priority queue
     */
    public int size();

    /**
     * Clears the list
     */
    public void clear();

    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing heap
     */
    public MaxHeap<T> getBackingHeap();
}
