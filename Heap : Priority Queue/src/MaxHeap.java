
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] arr;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a MaxHeap.
     */
    public MaxHeap() {
        this.arr = (T[]) (new Comparable[10]);
        size = 0;
    }

    /**
     *
     * @param element of current percolation
     * @param hole index of element
     */

    private void percolateUp(T element, int hole) {
        if (size > 2) {
            if (hole > 1
                    && element.compareTo(arr[hole / 2]) > 0) {
                int next = hole / 2;
                T oldNext = arr[next];
                arr[next] = element;
                arr[hole] = oldNext;
                percolateUp(arr[next], next);
            } else {
                arr[hole] = element;
            }
        } else {
            arr[hole] = element;
            if (arr[hole].compareTo(arr[1]) >= 0) {
                T oldRoot = arr[1];
                arr[hole] = oldRoot;
                arr[1] = element;
            }
        }
    }

    /**
     *
     * @param element of current percolation
     * @param index of element
     */
    private void percolateDown(T element, int index) {
        int hole = index;
        if ((hole * 2) <= size) {
            int child = hole * 2;
            if (child != size + 1) {
                if (arr[child].compareTo(element) > 0) {
                    arr[hole] = arr[child];
                    arr[child] = element;
                    percolateDown(arr[child], child);
                    percolateUp(arr[hole + 1], hole + 1);
                } else if (arr[child].compareTo(element) == 0) {
                    percolateDown(arr[child], child);
                }

            }
        } else if (arr[index + 1] != null
                && (arr[index].compareTo(arr[index + 1]) < 0)) {
            T old = arr[index];
            arr[index] = arr[index + 1];
            arr[index + 1] = old;
            percolateDown(arr[index / 2], index / 2);
        }
    }

    /**
     * resize method, double if resized
     */
    private void resize() {
        T[] newArr = (T[]) (new Comparable[arr.length * 2]);
        for (int i = 1; i < size + 1; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * Adds an item to the heap. If the backing array is full, double its size.
     *
     * @throws IllegalArgumentException if the item is null
     * @param item the item to be added to the heap
     */
    @Override
    public void add(T item) {
        if (item != null) {
            if (arr.length == (size + 1)) {
                resize();
            }
            size++;
            percolateUp(item, size);
        } else {
            throw new java.lang.IllegalArgumentException("Can't add null");
        }
    }
    /**
     * Removes and returns the first item of the heap.
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the item removed
     */
    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Nothing in heap");
        }
        T result = arr[1];
        arr[1] = arr[size--];
        arr[size + 1] = null;
        if (size != 0) {
            percolateDown(arr[1], 1);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        int i = 1;
        while (i != size + 1) {
            arr[i] = null;
            i++;
        }
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        return arr;
    }
}
