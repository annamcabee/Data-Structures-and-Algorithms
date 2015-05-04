

/**
 * CircularLinkedList implementation
 * @author Anna McAbee
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Constructer for the Circular Linked List class
     *
     * Sets head to null and size to 0
     *
     */

    public CircularLinkedList() {
        head = null;
        size = 0;
    }

    /**
    * Adds the element to the index specified.
    * Adding to indices 0 and size should be O(1), all other adds are O(n)
    *
    * Throw {@code java.lang.IndexOutOfBoundsException} if index is negative or
    * index > size.
    * Throw {@code java.lang.IllegalArgumentException} if data is null
    *
    * @param index The index where you want the new element.
    * @param data Any object of type T.
    */

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size()) {
            throw new java.lang.IndexOutOfBoundsException();
        } else if (data == null) {
            throw new java.lang.IllegalArgumentException();
        } else if (index == 0) {
            this.addToFront(data);
        } else if (index == size()) {
            this.addToBack(data);
        } else {
            LinkedListNode<T> current = head;
            for (int i = 1; i <= size; i++)  {
                if (i == index) {
                    LinkedListNode<T> newNode = new
                        LinkedListNode<>(data, current.getPrevious(), current);
                    LinkedListNode<T> nextNode = current.getNext();
                    current.setNext(newNode);
                    newNode.setPrevious(current);
                    newNode.setNext(nextNode);
                    nextNode.setPrevious(newNode);
                }
                current = current.getNext();
            }
            size++;
        }
    }

    /**
    * Returns the element at the given index.
    * This method must be O(1) for index 0 and index (size-1).
    * O(n) is expected for all other indices.
    *
    * Throw java.lang.IndexOutOfBoundsException if index < 0 or index >= size
    *
    *
    * @param index The index of the element
    * @return The object stored at that index.
    */

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new java.lang.IndexOutOfBoundsException();
        } else if (index == 0) {
            return head.getData();
        } else if (index == (size() - 1)) {
            return head.getPrevious().getData();
        } else {
            int currentIndex = 0;
            LinkedListNode<T> current = head;
            while (currentIndex != index) {
                current = current.getNext();
                currentIndex++;
            }
            return current.getData();
        }
    }

    /**
    * Removes and returns the element at index.
    * This method should be O(1) for index 0 and (size-1), and O(n) in all
    * other cases.
    *
    * Throw {@code java.lang.IndexOutOfBoundsException} if index < 0 or
    * index >= size.
    *
    * @param index The index of the element
    * @return The object that was formerly at that index.
    */

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new java.lang.IndexOutOfBoundsException();
        } else if (index == 0) {
            return this.removeFromFront();
        } else if (index == (size() - 1)) {
            return this.removeFromBack();
        } else {
            size--;
            LinkedListNode<T> current = head.getNext();
            T currentData = null;
            for (int i = 1; i <= size(); i++) {
                if (i == index) {
                    currentData = current.getData();
                    LinkedListNode<T> previousNode = current.getPrevious();
                    LinkedListNode<T> nextNode = current.getNext();
                    previousNode.setNext(nextNode);
                    nextNode.setPrevious(previousNode);
                }
                current = current.getNext();
            }
            return currentData;
        }
    }

    /**
    * Add a new node to the front of your linked list
    * that holds the given data. Make sure to update head.
    *
    * Must be O(1)
    *
    * Throw {@code java.lang.IllegalArgumentException} if data is null
    *
    * @param data The data that the new node should hold.
    */

    @Override
    public void addToFront(T data) {
        if (data != null) {
            size++;
            if (head == null) {
                head = new LinkedListNode<T>(data);
                head.setNext(head);
                head.setPrevious(head);
            } else {
                head = new LinkedListNode<T>(data, head.getPrevious(), head);
                head.getNext().setPrevious(head);
                head.getPrevious().setNext(head);
            }
        } else {
            throw new java.lang.IllegalArgumentException();
        }
    }

    /**
    * Add a new node to the back of your linked list
    * that holds the given data. Make sure to update tail.
    *
    * Must be O(1)
    *
    * Throw {@code java.lang.IllegalArgumentException} if data is null
    *
    * @param data The data that the new node should hold.
    */

    @Override
    public void addToBack(T data) {
        if (data != null) {
            size++;
            if (head != null) {
                LinkedListNode<T> newNode = new LinkedListNode<T>(data);
                newNode.setPrevious(head.getPrevious());
                head.getPrevious().setNext(newNode);
                head.setPrevious(newNode);
                newNode.setNext(head);
            } else {
                head = new LinkedListNode<>(data);
                head.setPrevious(head);
                head.setNext(head);
            }
        } else {
            throw new java.lang.IllegalArgumentException();
        }
    }

    /**
    * Remove the front node from the list and return the
    * data from it.
    *
    * Must be O(1)
    *
    * @return The data from the front node or null.
    */
    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        } else if (size() == 1) {
            size--;
            T headData = head.getData();
            head = null;
            return headData;
        } else {
            size--;
            T dataFront = head.getData();
            LinkedListNode<T> newHead = head.getNext();
            head.getPrevious().setNext(newHead);
            newHead.setPrevious(head.getPrevious());
            head = newHead;
            return dataFront;
        }
    }


    /**
    * Remove the back node from the list and return the
    * data from it.
    *
    * Must be O(1)
    *
    * @return The data from the last node or null.
    */
    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        } else if (size() == 1) {
            size--;
            T headData = head.getData();
            head = null;
            return headData;
        } else {
            size--;
            LinkedListNode<T> lastNode = head.getPrevious();
            LinkedListNode<T> nextToLastNode = lastNode.getPrevious();
            head.setPrevious(nextToLastNode);
            return lastNode.getData();
        }
    }

    /**
    * Return the linked list represented as an array of objects.
    *
    * @return A copy of the linked list data as an array.
    */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        while (i < size()) {
            array[i] = this.get(i++);
        }
        return array;
    }

    /**
    * Return a boolean value representing whether or not
    * the list is empty.
    *
    * Must be O(1)
    *
    * @return True if empty. False otherwise.
    */

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
    * Return the size of the list as an integer.
    *
    * Must be O(1)
    *
    * @return The size of the list.
    */
    @Override
    public int size() {
        return size;
    }

    /**
    * Clear the list.
    *
    * Must be O(1)
    */
    @Override
    public void clear() {
        head = null;
    }

    /**
    * Reference to the head node of the linked list.
    * Normally, you would not do this, but we need it
    * for grading your work.
    *
    * @return Node representing the head of the linked list
    */
    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }


}
