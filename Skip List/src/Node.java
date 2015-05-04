/**
 *
 * A container for key-value pairs for the skip list.
 * @param <T> the data stored in the node
 *
 * @version 1.0
 */
public class Node<T extends Comparable<? super T>> {
    private T data;
    private int level;
    private Node<T> prev;
    private Node<T> next;
    private Node<T> up;
    private Node<T> down;

    /**
     * Constructs a skip list node for storing key-value pairs.
     * @param data the data to store
     * @param level the level of the node
     */
    public Node(T data, int level) {
        this(data, level, null, null, null, null);
    }

    /**
     * Constructs a skip list node for storing key-value pairs with the
     * specified neighbors.
     * @param data the data to store
     * @param level the level of the node
     * @param prev the prev node from this node
     * @param next the next node from this node
     * @param up the node above this node
     * @param down the node below this node
     */
    public Node(T data, int level,
                Node<T> prev, Node<T> next, Node<T> up, Node<T> down) {
        this.data = data;
        this.level = level;
        this.prev = prev;
        this.next = next;
        this.up = up;
        this.down = down;
    }


    /**
     * Gets the data of the node.
     * @return the data stored in the node
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the level of the node.
     * @return the level of the node
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the next node.
     * @return the previous node at this level
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Gets the next node.
     * @return the next node at this level
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Gets the node above this node.
     * @return the node one level above this node
     */
    public Node<T> getUp() {
        return up;
    }

    /**
     * Gets the node below this node.
     * @return the node one level below this node
     */
    public Node<T> getDown() {
        return down;
    }


    /**
     * Set the data of this node.
     * @param data the data the node will store
     * @return the old data that was replaced
     */
    public T setData(T data) {
        T old = this.data;
        this.data = data;
        return old;
    }

    /**
     * Set the level of this node.
     * @param level the level of the node
     * @return the old level that was replaced
     */
    public int setLevel(int level) {
        int old = this.level;
        this.level = level;
        return old;
    }


    /**
     * Set the next node from this node.
     * @param prev the node to point to
     * @return the old node that was replaced, null otherwise
     */
    public Node<T> setPrev(Node<T> prev) {
        Node<T> old = this.prev;
        this.prev = prev;
        return old;
    }

    /**
     * Set the next node from this node.
     * @param next the node to point to
     * @return the old node that was replaced, null otherwise
     */
    public Node<T> setNext(Node<T> next) {
        Node<T> old = this.next;
        this.next = next;
        return old;
    }

    /**
     * Set the node above this node.
     * @param up the node to point to
     * @return the old node that was replaced, null otherwise
     */
    public Node<T> setUp(Node<T> up) {
        Node<T> old = this.up;
        this.up = up;
        return old;
    }

    /**
     * Set the node below this node.
     * @param down the node to point to
     * @return the old node that was replaced, null otherwise
     */
    public Node<T> setDown(Node<T> down) {
        Node<T> old = this.down;
        this.down = down;
        return old;
    }
}
