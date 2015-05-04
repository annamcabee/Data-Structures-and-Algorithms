import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.ArrayDeque;




public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the BST with the data in the collection. The data in the BST
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        for (T d : data) {
            if (d != null) {
                add(d);
            } else {
                throw new IllegalArgumentException("Can't add null");
            }
        }
    }
    /**
     * Add the data as a leaf in the BST.  Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added).
     * Should have a running time of O(log n) for a balanced, and a worst case
     * of O(n).
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    @Override
    public void add(T data) {
        if (data != null) {
            boolean added = false;
            if (root != null) {
                int oldSize = size;
                size++;
                BSTNode<T> temp = new BSTNode<T>(data);
                BSTNode<T> current = root;
                while (current != null && !added) {
                    if (current.getData().compareTo(data) < 0) {
                        if (current.getRight() !=  null) {
                            current = current.getRight();
                        } else {
                            current.setRight(temp);
                            added = true;
                        }
                    } else if (current.getData().compareTo(data) > 0) {
                        if (current.getLeft() !=  null) {
                            current = current.getLeft();
                        } else {
                            current.setLeft(temp);
                            added = true;
                        }
                    } else if (current.getData().compareTo(data) == 0) {
                        current = null;
                    }
                }
                if (!added) {
                    size--;
                }
            } else if (size == 0) {
                root = new BSTNode<>(data);
                size++;
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     *
     * recursive helper method to find max node
      * @param parent of node
     * @param current node
     * @return LinkedList
     */
    private LinkedList<BSTNode<T>>
        maxNode(BSTNode<T> parent, BSTNode<T> current) {
        LinkedList<BSTNode<T>> result = new LinkedList<BSTNode<T>>();
        result.add(parent);
        if (current.getRight() == null) {
            result.add(current);
            return result;
        }
        return maxNode(current, current.getRight());
    }

    /**
     *
     * @param parent of current node
     * @param current current node we are searching for
     * @param data we are searching for
     * @return LinkedList of BSTNodes that helps remove method
     */
    private LinkedList<BSTNode<T>>
        removeFinder(BSTNode<T> parent, BSTNode<T> current,
                                             T data) {
        LinkedList<BSTNode<T>> result = new LinkedList<BSTNode<T>>();
        result.add(parent);
        if (data == null && current.getData() == null) {
            result.add(current);
            return result;
        } else if (data.equals(current.getData())) {
            result.add(current);
            return result;
        } else {
            if (current.getData() != null
                    & data.compareTo(current.getData()) > 0) {
                if (current.getRight() != null) {
                    return removeFinder(current, current.getRight(), data);
                }
                return null;
            } else {
                if (current.getLeft() != null) {
                    return removeFinder(current, current.getLeft(), data);
                }
                return null;
            }
        }
    }

    /**
     * Helper method for removal of the root
     * @return T the removed root
     */
    private T removeRoot() {
        size--;
        if (size == 0 || size == -1) {
            T rootData = root.getData();
            root = null;
            size = 0;
            return rootData;
        } else if (root.getLeft() == null || root.getRight() == null) {
            T rootData = root.getData();
            if (root.getRight() != null) {
                BSTNode<T> replacer = root.getRight();
                BSTNode<T> rightOfR = replacer.getRight();
                BSTNode<T> leftOfR = replacer.getLeft();
                root.setData(replacer.getData());
                root.setRight(rightOfR);
                root.setLeft(leftOfR);
            } else {
                BSTNode<T> replacer = root.getLeft();
                BSTNode<T> rightOfR = replacer.getRight();
                BSTNode<T> leftOfR = replacer.getLeft();
                root.setData(replacer.getData());
                root.setRight(rightOfR);
                root.setLeft(leftOfR);
            }
            return rootData;
        } else {
            BSTNode<T> succ = root.getRight();
            while (succ.getLeft() != null) {
                succ = succ.getLeft();
            }
            BSTNode<T> oldRight = succ.getRight();
            root.setData(succ.getData());
            root.setRight(oldRight);
            succ.setData(null);
            T rootData = root.getData();
            return rootData;
        }
    }
    /**
     * Removes the data from the tree.  There are 3 cases to consider:
     * 1: the data is a leaf.  In this case, simply remove it
     * 2: the data has one child.  In this case, simply replace it with its
     * child, then remove the child.
     * 3: the data has 2 children.  There are generally two approaches:
     * replacing the data with either the next smallest element in the tree
     * (commonly called the predecessor), or replacing it with the next
     * largest element in the tree (commonly called the successor). For this
     * assignment, use the successor.
     * Should have a running time of O(log n) for a balanced, and a worst case
     * of O(n)
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found.
     * @param data Data to remove from the tree.
     * @return the data removed from the tree.  Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null. ");
        }
        if (!contains(data)) {
            throw new NoSuchElementException("Element not in tree");
        }
        if (root.getData() == data) {
            return removeRoot();
        }
        LinkedList<BSTNode<T>> found = removeFinder(root, root, data);
        BSTNode<T> parent = found.getFirst();
        BSTNode<T> toRemove = found.getLast();
        BSTNode<T> current = found.getLast();
        T result;
        size--;
        if (toRemove.getRight() == null && toRemove.getLeft() == null) {
            if (parent.getLeft() == toRemove) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            return data;
        } else if (toRemove.getLeft() == null || toRemove.getRight() == null) {
            result = toRemove.getData();
            if (toRemove.getRight() != null) {
                BSTNode<T> successor = toRemove.getRight();
                BSTNode<T> oldRight = toRemove.getRight();
                while (successor.getLeft() != null) {
                    successor = successor.getLeft();
                }
                toRemove.setData(successor.getData());
                successor.setData(null);
                if (successor != oldRight) {
                    toRemove.setRight(oldRight);
                } else {
                    toRemove.setRight(null);
                }
            } else {
                BSTNode<T> successor = toRemove.getRight();
                BSTNode<T> oldLeft = toRemove.getLeft();
                toRemove.setData(oldLeft.getData());
                toRemove.setLeft(oldLeft.getLeft());
                oldLeft.setData(null);
            }
            return result;
        } else if (toRemove.getLeft() != null && toRemove.getRight() != null) {
            result = toRemove.getData();
            BSTNode<T> successor = toRemove.getRight();
            BSTNode<T> oldRight = toRemove.getRight();
            BSTNode<T> oldLeft = toRemove.getLeft();
            BSTNode<T> parentOfSucc = successor;
            while (successor.getLeft().getLeft() != null) {
                parentOfSucc = parentOfSucc.getLeft();
            }
            successor = parentOfSucc.getLeft();
            toRemove.setData(successor.getData());
            if (successor.getRight() != null) {
                parentOfSucc.setLeft(successor.getRight());
                successor.setData(successor.getRight().getData());
            } else {
                successor.setData(null);
                parentOfSucc.setLeft(null);
            }
            toRemove.setRight(oldRight);
            toRemove.setLeft(oldLeft);
            return result;
        }
        return null;
    }
    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use .equals or == ?).
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n)
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found.
     * @param data Data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.
     */
    @Override
    public T get(T data) {
        if (data != null) {
            if (contains(data)) {
                BSTNode<T> current = root;
                T currData = null;
                while (current != null) {
                    if (current.getData().compareTo(data) < 0) {
                        current = current.getRight();
                    } else if (current.getData().compareTo(data) > 0) {
                        current = current.getLeft();
                    } else if (current.getData().compareTo(data) == 0) {
                        currData = current.getData();
                        current = null;
                    }
                }
                return currData;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * Returns whether or not the parameter is contained within the tree.
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n)
     * @throws IllegalArgumentException if the data is null
     * @param data Data to search for in the tree.
     * @return whether or not the parameter is contained within the tree
     */
    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        BSTNode<T> curr = root;
        boolean keepGoing = false;
        boolean found = false;
        while (curr != null && !keepGoing) {
            if (curr.getData().compareTo(data) < 0) {
                if (curr.getRight() == null) {
                    return false;
                }
                curr = curr.getRight();
            } else if (curr.getData().compareTo(data) > 0) {
                if (curr.getLeft() == null) {
                    return false;
                }
                curr = curr.getLeft();
            } else if (curr.getData().compareTo(data) == 0) {
                return true;
            }
        }
        return found;
    }
    /**
     * Should run in O(1)
     * @return the number of elements in the tree
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * helper method for preorder traversals
     * @param n the current node
     * @param list list we will later return
     */
    private void preorder(BSTNode<T> n, ArrayList<T> list) {
        if (n != null) {
            list.add(n.getData());
            preorder(n.getLeft(), list);
            preorder(n.getRight(), list);
        }
    }
    /**
     * Should run in O(n)
     * @return a preorder traversal of the tree
     */
    @Override
    public List<T> preorder() {
        ArrayList<T> list = new ArrayList<T>();
        preorder(root, list);
        return list;
    }
    /**
     * helper method for postorder traversals
     * @param n the current node
     * @param list list we will later return
     */
    private void postorder(BSTNode<T> n, ArrayList<T> list) {
        if (n != null) {
            postorder(n.getLeft(), list);
            postorder(n.getRight(), list);
            list.add(n.getData());
        }
    }
    /**
     * Should run in O(n)
     * @return a postorder traversal of the tree
     */
    @Override
    public List<T> postorder() {
        ArrayList<T> list = new ArrayList<T>();
        postorder(root, list);
        return list;
    }

    /**
     * helper method for inorder traversals
     * @param n the current node
     * @param list list we will later return
     */
    private void inorder(BSTNode<T> n, ArrayList<T> list) {
        if (n != null) {
            inorder(n.getLeft(), list);
            list.add(n.getData());
            inorder(n.getRight(), list);
        }
    }
    /**
     * Should run in O(n)
     * @return a inorder traversal of the tree
     */
    @Override
    public List<T> inorder() {
        ArrayList<T> list = new ArrayList<T>();
        inorder(root, list);
        return list;
    }
    /**
     * helper method for levelorder traversals
     * @param root the root
     * @return list of lists that are each level
     */
    public List<List<T>> levelOrder(BSTNode<T> root) {
        List<List<T>> r = new ArrayList<List<T>>();
        ArrayDeque<BSTNode<T>> q = new ArrayDeque<BSTNode<T>>();
        if (root == null) {
            return r;
        }
        q.offer(root);
        while (!q.isEmpty()) {
            int len = q.size();
            List<T> level = new ArrayList<T>();
            for (int i = 0; i < len; i++) {
                BSTNode<T> n = q.poll();
                level.add(n.getData());
                if (n.getLeft() != null) {
                    q.offer(n.getLeft());
                }
                if (n.getRight() != null) {
                    q.offer(n.getRight());
                }
            }
            r.add(level);
        }
        return r;
    }

    /**
     * Should run in O(n)
     * @return a level order traversal of the tree
     */
    @Override
    public List<T> levelorder() {
        List<List<T>> list = levelOrder(root);
        List<T> levelOrder = new ArrayList<>();
        for (List outList : list) {
            for (Object data : outList) {
                levelOrder.add((T) data);
            }
        }
        return levelOrder;
    }
    /**
     * Clear the tree.  Should be O(1)
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Recursive helper method for height
     * @param n current node
     * @return the height
     */
    private int height(BSTNode<T> n) {
        if (n == null) {
            return -1;
        } else {
            return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
        }
    }
    /**
    * Calculate and return the height of the root of the tree.  A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
    * node has a height of 0.
     * Should be calculated in O(n)
    * @return the height of the root of the tree, -1 if the tree is empty
    */
    @Override
    public int height() {
        return height(root);
    }
    /**
     * Calculate the depth of the node containing the data.
     * The depth of the node is defined as {@code depth(parent node) + 1}. The
     * depth of the root node is 1.
     * Should be calculated in O(n)
     * @throws IllegalArgumentException if data is null
     * @param data data to look for
     * @return the depth of the node containing the data if it is in the tree,
     *      -1 otherwise
     */
    @Override
    public int depth(T data) {
        int d = 1;
        BSTNode<T> current = root;
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (contains(data)) {
            while (current != null) {
                if (current.getData().compareTo(data) < 0) {
                    current = current.getRight();
                    d++;
                } else if (current.getData().compareTo(data) > 0) {
                    current = current.getLeft();
                    d++;
                } else {
                    return d;
                }
            }
        }
        return -1;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }
}
