import java.util.HashSet;
import java.util.Set;

public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    // Do not add any additional instance variables
    private CoinFlipper coinFlipper;
    private int size;
    private Node<T> head;

    /**
     * Constructs a SkipList object that stores data in ascending order.
     * When an item is inserted, the flipper is called until it returns a tails.
     * If, for an item, the flipper returns n heads, the corresponding node has
     * n + 1 levels.
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        size = 0;
        head = new Node<>(null, 1);
    }
    /**
     * Finds and returns the first item in the skip list.
     * @throws java.util.NoSuchElementException if the skip list is empty
     * @return the first item in the skip list
     */
    @Override
    public T first() {
        if (size != 0) {
            Node<T> current = head;
            while (current.getLevel() > 1) {
                current = current.getDown();
            }
            return current.getNext().getData();
        } else {
            throw new
                    java.util.NoSuchElementException("Skip list is empty");
        }
    }
    /**
     * Finds and returns the last item in the skip list.
     * @throws java.util.NoSuchElementException if the skip list is empty
     * @return the last element in the skip list
     */
    @Override
    public T last() {
        if (size != 0) {
            Node<T> current = head;
            while (current.getLevel() != 1) {
                current = current.getDown();
            }
            while (current.getNext() != null) {
                current = current.getNext();
            }
            return current.getData();
        } else {
            throw new java.util.NoSuchElementException("Skip list is empty");
        }
    }

    /**
     * gets random level from coin flipper
     * @return int level
     */
    private int level() {
        int l = 0;
        CoinFlipper.Coin c = coinFlipper.flipCoin();
        while (c.equals(CoinFlipper.Coin.HEADS)) {
            l++;
            c = coinFlipper.flipCoin();
        }
        return l;
    }
    /**
     *  private helper method that adds a number of levels to head
     * @param level how many levels to add
     */
    private void addLevelsToHead(int level) {
        for (int i = head.getLevel() + 1; i < level + 2; i++) {
            Node<T> newNode = new Node<>(null, i);
            newNode.setDown(head);
            head.setUp(newNode);
            head = newNode;
        }
        head.setLevel(level + 1);
    }
    /**
     * Adds a new item into the skip list.
     * If there is already data in the list that is equal to the given data,
     * replace the previous data with the new one
     * @param data the item to put into the skip list
     * @throws IllegalArgumentException if any parameter is null
     */
    @Override
    public void put(T data) {
        if (data != null) {
            int level = level();
            size++;
            Node<T> nodeJustAdded = null;
            if (level + 1 > head.getLevel()) {
                addLevelsToHead(level);
            }
            Node<T> curr = head;
            for (int i = level + 1; i > 0; i--) {
                if (curr == null) {
                    curr = head;
                }
                while (curr.getLevel() > i) {
                    curr = curr.getDown();
                }
                while (curr.getNext() != null
                        && data.compareTo(curr.getNext().getData()) > 0) {
                    curr = curr.getNext();
                }
                if (curr.getNext() != null
                        && curr.getNext().getData().equals(data)) {
                    if (curr.getNext().getUp() != null
                            && curr.getNext().getUp().getLevel() > level + 1) {
                        curr.getNext().getUp().setData(data);
                    }
                    curr.getNext().setData(data);
                    if (i == 1) {
                        size--;
                        return;
                    }
                } else if (curr.getNext() != null) {
                    Node<T> left = curr;
                    Node<T> right = curr.getNext();
                    Node<T> newNode = new Node<>(data, i);
                    right.setPrev(newNode);
                    newNode.setNext(right);
                    left.setNext(newNode);
                    newNode.setPrev(left);
                    nodeJustAdded = newNode;
                } else if (curr.getData() == null && curr.getNext() == null) {
                    Node<T> newNode = new Node<>(data, i);
                    curr.setNext(newNode);
                    newNode.setPrev(curr);
                    nodeJustAdded = newNode;
                } else if (curr.getData() == null) {
                    Node<T> oldRight = curr.getNext();
                    Node<T> newNode = new Node<>(data, i);
                    curr.setNext(newNode);
                    newNode.setPrev(curr);
                    oldRight.setPrev(newNode);
                    newNode.setNext(oldRight);
                    nodeJustAdded = newNode;
                } else if (curr.getNext() == null) {
                    Node<T> newNode = new Node<>(data, i);
                    curr.setNext(newNode);
                    newNode.setPrev(curr);
                    nodeJustAdded = newNode;
                }
                if (i < (level + 1) && i > 1 && nodeJustAdded != null) {
                    Node<T> oldCurr = curr;
                    if (curr.getUp() != null) {
                        curr = curr.getUp();
                    } else {
                        while (curr.getUp() == null) {
                            curr = curr.getPrev();
                        }
                        curr = curr.getUp();
                    }
                    while (curr.getNext() != null
                            && data.compareTo(
                            curr.getNext().getData()) > 0) {
                        curr = curr.getNext();
                    }
                    if (curr.getNext() != null) {
                        curr.getNext().setDown(nodeJustAdded);
                        nodeJustAdded.setUp(curr.getNext());
                    }
                    curr = oldCurr;
                } else if (i == 1 && level > 0) {
                    if (curr.getUp() != null
                            && curr.getUp().getNext().getData().equals(data)) {
                        nodeJustAdded.setUp(curr.getUp().getNext());
                        curr.getUp().getNext().setDown(nodeJustAdded);
                    } else {
                        while (curr.getUp() == null) {
                            curr = curr.getPrev();
                        }
                        curr = curr.getUp();
                        curr.getNext().setDown(nodeJustAdded);
                        nodeJustAdded.setUp(curr.getNext());
                    }
                }
                if (curr.getDown() != null) {
                    curr = curr.getDown();
                } else {
                    curr = head;
                }
                printSkipList();
            }
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }


    /**
     * helper method that moves the head down
     */
    private void moveHeadDown() {
        head = head.getDown();
        head.setUp(null);
    }
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
    @Override
    public T remove(T data) {
        if (data != null) {
            if (contains(data)) {
                T val = null;
                Node<T> current = head;
                boolean alreadyMoved = false;
                boolean nodeMoved = false;
                while (current.getNext() != null) {
                    if (head.getNext() == null) {
                        moveHeadDown();
                    }
                    if (current.getNext() == null
                            || current.getNext()
                            .getData().compareTo(data) >= 0) {
                        if (current.getNext() != null
                                && current.getNext().getData().equals(data)) {
                            val = current.getNext().getData();
                            if (current.getNext().getLevel() > 0) {
                                Node<T> oldLeftColumn = current;
                                Node<T> removalColumn = current.getNext();
                                if (oldLeftColumn.getData() == null
                                        && (current.getNext().getNext()
                                        == null || current.getNext()
                                        .getNext().getData() == null)) {
                                    oldLeftColumn.setNext(null);
                                    if (removalColumn.getDown() != null) {
                                        removalColumn.getDown().setUp(null);
                                        moveHeadDown();
                                        alreadyMoved = true;
                                    }
                                } else if (oldLeftColumn.getData() == null) {
                                    Node<T> oldRight
                                            = current.getNext().getNext();
                                    current.setNext(oldRight);
                                    oldRight.setPrev(null);
                                } else if (current.getNext().getNext()
                                        == null) {
                                    oldLeftColumn.setNext(null);
                                } else {
                                    Node<T> oldRightColumn
                                            = current.getNext().getNext();
                                    oldRightColumn.setPrev(oldLeftColumn);
                                    oldLeftColumn.setNext(oldRightColumn);
                                }
                                if (current.getDown() == null) {
                                    size--;
                                    if (head.getLevel() != 1
                                            && head.getDown().getNext()
                                            == null && !alreadyMoved) {
                                        moveHeadDown();
                                        alreadyMoved = true;
                                    }
                                    return val;
                                }
                                current = current.getDown();
                                nodeMoved = true;
                            }
                        }
                    }
                    if (current.getNext().getNext() != null
                            && !current.getNext()
                            .getData().equals(data)) {
                        current = current.getNext();
                    } else if (current.getDown() != null && !nodeMoved) {
                        current = current.getDown();
                    }
                }
                size--;
                if (head.getDown().getNext() == null
                        && !alreadyMoved) {
                    moveHeadDown();
                }
                return val;
            } else {
                throw new
                        java.util.NoSuchElementException("Not in list");
            }
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }
    /**
     * Searches the skip list for a given data.
     * @param data an item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @return true if the data is in the skip list, false otherwise
     */
    @Override
    public boolean contains(T data) {
        if (data != null) {
            Node<T> current = head;
            while ((current != null) && (current.getDown() != null
                    || current.getNext() != null)) {
                if (current.getData() != null
                        && current.getData().equals(data)) {
                    return true;
                }
                while (current.getNext() != null
                        && current.getNext().getData().compareTo(data) <= 0) {
                    if (current.getNext().getData().compareTo(data) == 0
                            || (current.getNext().getData().equals(data))) {
                        return true;
                    }
                    if (current.getNext() != null
                            && current.getNext().getLevel() == 1
                            && current.getNext()
                                .getData().compareTo(data) > 0) {
                        return false;
                    }
                    current = current.getNext();
                }
                if (current.getData() != null
                        && current.getData().compareTo(data) > 0) {
                    current = current.getPrev();
                }
                if (current.getNext() != null
                        && current.getNext().getData() != null
                        && current.getNext().getData().equals(data)) {
                    return true;
                }
                current = current.getDown();
            }
            return false;
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }
    /**
     * Finds and returns the data found in the skip list that is equal to the
     * data passed in.
     * @param data a item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @throws java.util.NoSuchElementException if the item is not in skip list
     * @return the data in the skip list. Do NOT just return what was passed in.
     */
    @Override
    public T get(T data) {
        if (data !=  null) {
            if (contains(data)) {
                Node<T> current = head;
                while (current.getNext() != null
                        && (current.getNext().getData().compareTo(data) <= 0)) {
                    current = current.getNext();
                }
                if (current.getDown() != null) {
                    current = current.getDown();
                }
                if (current.getData().equals(data)) {
                    return current.getData();
                }
                return null;
            } else {
                throw new
                        java.util.NoSuchElementException("Not in list");
            }
        } else  {
            throw new IllegalArgumentException("Null argument");
        }
    }
    /**
     * Gives the size of the skip list.
     * @return the number of items in the skip list
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Clears the list. The size should be zero after this method is called.
     */
    @Override
    public void clear() {
        head = new Node<>(null, 1);
        size = 0;
    }
    /**
     * The data of each item of the skip list. If the list is empty, return an
     * empty set. You may use any implementation of Set.
     * @return a set of all the data in the skip list
     */
    @Override
    public Set<T> dataSet() {
        Set<T> set = new HashSet<>();
        if (size != 0) {
            Node<T> curr = head;
            while (curr.getDown() != null) {
                curr = curr.getDown();
            }
            while (curr.getNext() != null) {
                curr = curr.getNext();
                set.add(curr.getData());
            }

        }
        return set;
    }
    /**
     * Gets the head of the skip list. This is for grading purposes only.
     * You shouldn't use this method.
     * @return the head of the list.
     */
    @Override
    public Node<T> getHead() {
        return head;
    }

}
