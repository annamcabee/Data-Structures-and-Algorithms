import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collection;



/**
 * Creates an AVL Tree
 *
 * @author Anna McAbee
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public AVL() {
        root = new AVLNode<>(null);
        size = 0;
    }

    /**
     * Initializes the AVL with the data in the collection. The data
     * should be added in the same order it is in the collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        this();
        for (T x : data) {
            add(x);
        }
    }

    /**
     * rotate left helper method
     * @param node that needs rotation
     * @return the node that will take its spot
     */
    private AVLNode<T> rotateWithLeft(AVLNode<T> node) {
        AVLNode<T> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
        balanceHeightUpdate(leftChild);
        balanceHeightUpdate(node);
        return leftChild;
    }

    /**
     * rotate with right helper method
     * @param node that needs rotation
     * @return new node
     */
    private AVLNode<T> rotateWithRight(AVLNode<T> node) {
        AVLNode<T> p = node.getRight();
        node.setRight(p.getLeft());
        p.setLeft(node);
        balanceHeightUpdate(p);
        balanceHeightUpdate(node);
        return p;
    }

    /**
     * double rotate with left helper method
     * @param c the current node
     * @return the new node in old's position
     */
    private AVLNode<T> doubleRotateWithLeft(AVLNode<T> c) {
        AVLNode<T> tmp;
        c.setLeft(rotateWithRight(c.getLeft()));
        tmp = rotateWithLeft(c);
        balanceHeightUpdate(c);
        balanceHeightUpdate(tmp);
        return tmp;
    }

    /**
     * double rotate with right helper method
     * @param c the current node
     * @return node in c's old spot
     */
    private AVLNode<T> doubleRotateWithRight(AVLNode<T> c) {
        AVLNode<T> tmp;
        c.setRight(rotateWithLeft(c.getRight()));
        tmp = rotateWithRight(c);
        balanceHeightUpdate(c);
        balanceHeightUpdate(tmp);
        return tmp;
    }
    /**
     * Add the data as a leaf in the AVL.  Should traverse the tree to find the
     * appropriate location. If the data being added already exists in the tree,
     * do nothing.
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    @Override
    public void add(T data) {
        if (data != null) {
            size++;
            if (root.getData() == null) {
                root.setData(data);
                root.setHeight(0);
            } else {
                root = add(data, root);
            }
        } else {
            throw new IllegalArgumentException("Cant add null");
        }
    }

    /**
     * helper method that updates balance factor and height
     * @param n the node to update
     */
    private void balanceHeightUpdate(AVLNode<T> n) {
        if (n.getLeft() != null && n.getRight() != null) {
            n.setHeight(Math.max(n.getLeft().getHeight(),
                    n.getRight().getHeight()) + 1);
            n.setBalanceFactor(n.getLeft().getHeight()
                    - n.getRight().getHeight());
        } else if (n.getLeft() != null && n.getRight() == null) {
            n.setHeight(n.getLeft().getHeight() + 1);
            n.setBalanceFactor(n.getLeft().getHeight() + 1);
        } else if (n.getLeft() == null && n.getRight() != null) {
            n.setHeight(n.getRight().getHeight() + 1);
            n.setBalanceFactor(-n.getRight().getHeight() - 1);
        } else {
            n.setBalanceFactor(0);
            n.setHeight(0);
        }
    }

    /**
     * add helper method
     * @param x the data
     * @param t t
     * @return the node added
     */
    private AVLNode<T> add(T x, AVLNode<T> t) {
        if (t != null) {
            if (x != null && x.compareTo(t.getData()) == 0) {
                size--;
                return t;
            } else if (x == null || x.compareTo(t.getData()) < 0) {
                if (t.getLeft() == null) {
                    AVLNode<T> newNode = new AVLNode<T>(x);
                    newNode.setHeight(0);
                    t.setLeft(newNode);
                    t.setBalanceFactor(1);
                    t.setHeight(1);
                } else {
                    t.setLeft(add(x, t.getLeft()));
                }
                balanceHeightUpdate(t);
                if (t.getBalanceFactor() > 1) {
                    if (x.compareTo(t.getLeft().getData()) < 0) {
                        t = rotateWithLeft(t);
                    } else {
                        t = doubleRotateWithLeft(t);
                    }
                    balanceHeightUpdate(t);
                }
            } else if (x != null || x.compareTo(t.getData()) > 0) {
                if (t.getRight() == null) {
                    AVLNode<T> newNode = new AVLNode<>(x);
                    newNode.setHeight(0);
                    t.setRight(newNode);
                } else {
                    t.setRight(add(x, t.getRight()));
                }
                balanceHeightUpdate(t);
                if (t.getBalanceFactor() < -1) {
                    if (x.compareTo(t.getRight().getData()) > 0) {
                        t = rotateWithRight(t);
                    } else {
                        t = doubleRotateWithRight(t);
                    }
                    balanceHeightUpdate(t);
                }
            }
           // balanceHeightUpdate(t);
            return t;
        } else {
            return new AVLNode<T>(null);
        }
    }

    /**
     * private remove helper node
     * @param n curr node
     * @param key data we are looking to delete
     * @param dummy our return node
     * @return the node
     */
    private AVLNode<T> delete(AVLNode<T> n, T key, AVLNode<T> dummy) {
        if (n == null) {
            return null;
        }
        if (key.compareTo(n.getData()) < 0) {
            n.setLeft(delete(n.getLeft(), key, dummy));
            balanceHeightUpdate(n);
            return balance(n);
        } else if (key.compareTo(n.getData()) > 0) {
            n.setRight(delete(n.getRight(), key, dummy));
            balanceHeightUpdate(n);
            return balance(n);
        } else {
            dummy.setData(key);
            if (n.getLeft() == null && n.getRight() == null) {
                return null;
            } else if (n.getLeft() == null) {
                return n.getRight();
            } else if (n.getRight() == null) {
                return n.getLeft();
            }
            AVLNode<T> leftMax = findMax(n.getLeft());
            T leftMaxData = leftMax.getData();
            delete(n.getLeft(), leftMaxData, dummy);
            n.setData(leftMaxData);
            balanceHeightUpdate(n);
            return balance(n);
        }
    }

    /**
     * helper method to find predecssors
     * @param t the the curr node
     * @return the predecessor
     */
    private AVLNode<T> findMax(AVLNode<T> t) {
        if (t == null) {
            return t;
        }
        while (t.getRight() != null) {
            t = t.getRight();
        }
        return t;
    }

    /**
     * balance helper method
     * @param t curr node
     * @return balanced node
     */
    private AVLNode<T> balance(AVLNode<T> t) {
        if (t == null) {
            return t;
        }
        if (height(t.getLeft()) - height(t.getRight()) > 1) {
            if (t.getLeft() != null && height(t.getLeft().getLeft())
                    >= height(t.getLeft().getRight())) {
                t = rotateWithLeft(t);
            } else {
                t = doubleRotateWithLeft(t);
            }
        } else {
            if (height(t.getRight()) - height(t.getLeft()) > 1) {
                if (height(t.getRight().getRight())
                        >= height(t.getRight().getLeft())) {
                    t = rotateWithRight(t);
                } else {
                    t = doubleRotateWithRight(t);
                }
            }
        }
        balanceHeightUpdate(t);
        if (t.getLeft() != null) {
            balanceHeightUpdate(t.getLeft());
        }
        if (t.getRight() != null) {
            balanceHeightUpdate(t.getRight());
        }
        return t;
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
     * assignment, use the predecessor.
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the AVL
     * @param data data to remove from the tree
     * @return the data removed from the tree.
     */
    @Override
    public T remove(T data) {
        if (data != null) {
            AVLNode<T> dummy = new AVLNode<>(null);
            root = delete(root, data, dummy);
            if (dummy.getData() != null) {
                size--;
                return dummy.getData();
            } else {
                throw new NoSuchElementException("Not in AVL");
            }
        } else {
            throw new IllegalArgumentException("Null argument");
        }
    }
    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use .equals or ==?).
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not in the AVL
     * @param data data to get in the AVL tree
     * @return the data in the tree equal to the parameter.
     */
    @Override
    public T get(T data) {
        if (data != null) {
            T getData = lookup(root, data);
            if (getData != null) {
                return getData;
            } else {
                throw new NoSuchElementException("Not in AVL");
            }
        } else {
            throw new IllegalArgumentException("Null Argument");
        }
    }
    /**
     * helper method for get
     * @param n the root of the tree
     * @param key the key of the data to be retrieved
     * @return data corresponding to key
     */
    private T lookup(AVLNode<T> n, T key) {
        if (n == null) {
            return null;
        }
        if (n.getData().equals(key)) {
            return n.getData();
        }
        if (key.compareTo(n.getData()) < 0) {
            return lookup(n.getLeft(), key);
        }
        return lookup(n.getRight(), key);
    }
    /**
     * Returns whether or not the parameter is contained within the tree.
     * @throws IllegalArgumentException if key is null
     * @param data data to find in the AVL tree
     * @return whether or not the parameter is contained within the tree
     */
    @Override
    public boolean contains(T data) {
        if (data != null) {
            return contains(data, root);
        } else {
            return false;
        }
    }

    /**
     * contains helper method
     * @param key we are looking for
     * @param n the current node
     * @return boolean of whether key is in AVL
     */
    private boolean contains(T key, AVLNode<T> n) {
        if (n == null) {
            return false;
        }
        if (n.getData() != null && n.getData().equals(key)) {
            return true;
        }
        if (n.getData() != null && key.compareTo(n.getData()) < 0) {
            return contains(key, n.getLeft());
        } else {
            return contains(key, n.getRight());
        }
    }
    /**
     * Get the number of elements in the tree.
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
    private void preorder(AVLNode<T> n, ArrayList<T> list) {
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
    private void postorder(AVLNode<T> n, ArrayList<T> list) {
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
    private void inorder(AVLNode<T> n, ArrayList<T> list) {
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
    public List<List<T>> levelOrder(AVLNode<T> root) {
        List<List<T>> r = new ArrayList<List<T>>();
        ArrayDeque<AVLNode<T>> q = new ArrayDeque<AVLNode<T>>();
        if (root == null) {
            return r;
        }
        q.offer(root);
        while (!q.isEmpty()) {
            int len = q.size();
            List<T> level = new ArrayList<T>();
            for (int i = 0; i < len; i++) {
                AVLNode<T> n = q.poll();
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
     * Clear the tree.
     */
    @Override
    public void clear() {
        root = new AVLNode<T>(null);
        size = 0;
    }

    /**
     * helper method of height
     * @param node to get height of
     * @return height of tree
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }
    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as max(left.height, right.height) + 1. A leaf node has
     * a height of 0.
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    @Override
    public int height() {
        return height(root);
    }
    /**
     * Calculate the depth of the node containing the data.
     * The depth of the node is defined as (parent node's depth)+1. The depth of
     * the root node is 1.
     * @throws IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree.
     * @param data data to calculate the depth of
     * @return the depth of the node containing the data if it is in the tree
     */
    @Override
    public int depth(T data) {
        return 0;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        return root;
    }

}
