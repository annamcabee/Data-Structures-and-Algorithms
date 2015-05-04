import java.util.Set;

/**
 * Interface for a skip list.
 *
 * @version 1.0
 */
public interface SkipListInterface<T extends Comparable<? super T>> {

    /**
     * Finds and returns the first item in the skip list.
     * @throws java.util.NoSuchElementException if the skip list is empty
     * @return the first item in the skip list
     */
    public T first();

    /**
     * Finds and returns the last item in the skip list.
     * @throws java.util.NoSuchElementException if the skip list is empty
     * @return the last element in the skip list
     */
    public T last();

    /**
     * Searches the skip list for a given data.
     * @param data an item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @return true if the data is in the skip list, false otherwise
     */
    public boolean contains(T data);

    /**
     * Adds a new item into the skip list.
     * If there is already data in the list that is equal to the given data,
     * replace the previous data with the new one
     * @param data the item to put into the skip list
     * @throws IllegalArgumentException if any parameter is null
     */
    public void put(T data);


    /**
     * Removes a item from the skip list. Note that if you remove the only
     * item in a level, you should remove the entire level.
     * @param data an item that is equal to some other item in the skip list
     * @throws java.util.NoSuchElementException if the item is not in the skip
     * list.
     * @throws IllegalArgumentException if the parameter is null
     * @return the item removed if it was in the skip list. Do NOT just return
     * what was passed in.
     */
    public T remove(T data);

    /**
     * Finds and returns the data found in the skip list that is equal to the
     * data passed in.
     * @param data a item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @throws java.util.NoSuchElementException if the item is not in skip list
     * @return the data in the skip list. Do NOT just return what was passed in.
     */
    public T get(T data);

    /**
     * Gives the size of the skip list.
     * @return the number of items in the skip list
     */
    public int size();

    /**
     * Clears the list. The size should be zero after this method is called.
     */
    public void clear();

    /**
     * The data of each item of the skip list. If the list is empty, return an
     * empty set. You may use any implementation of Set.
     * @return a set of all the data in the skip list
     */
    public Set<T> dataSet();

    /**
     * Gets the head of the skip list. This is for grading purposes only.
     * You shouldn't use this method.
     * @return the head of the list.
     */
    public Node<T> getHead();

    /**
     * This method will allow you to see what your SkipList looks like.
     * It is meant to help you debug.
     */
    default void printSkipList() {
        System.out.println("**********************");
        System.out.println("SkipList (size = " + size() + ")");
        Node<T> levelCurr = getHead();

        while (levelCurr != null) {
            Node<T> curr = levelCurr;
            int level = levelCurr.getLevel();
            String formattedLevel;

            if (level < 10) {
                formattedLevel = level + "   ";
            } else {
                formattedLevel = level + "  ";
            }

            System.out.print("Level: " + formattedLevel + "   ");
            while (curr != null) {

                System.out.print("(" + curr.getData() + ")");
                curr = curr.getNext();

                if (curr == null) {
                    System.out.println();
                } else {
                    System.out.print(", ");
                }
            }
            levelCurr = levelCurr.getDown();
        }
        System.out.println("**********************");
    }
}
