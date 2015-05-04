import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;





public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries.
     */
    public HashMap() {
        this.table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }
    /**
     * rehashes the hash map, new table size = 2*oldsize + 1
     *
     */
    private void rehash() {
        int oldCapacity = table.length;
        MapEntry[] oldTable = table;
        int newCapacity = oldCapacity * 2 + 1;
        MapEntry[] newTable = new MapEntry[newCapacity];
        table = newTable;
        for (int i = 0; i < oldCapacity; i++) {
            if (oldTable[i] != null) {
                MapEntry<K, V> curr = oldTable[i];
                int index = (curr.getKey().hashCode()) % newCapacity;
                int collisions = 0;
                while (newTable[index] != null) {
                    collisions++;
                    index = (oldTable[i].getKey().hashCode())
                            + collisions * collisions;
                    if (index >= newCapacity) {
                        index = index % newCapacity;
                    }
                }
                if (index >= newCapacity) {
                    index = index % newCapacity;
                }
                newTable[index] = oldTable[i];
            }
        }
        table = newTable;
    }
    /**
     * Adds the given key-value pair to the HashMap.
     * If the key is already in the HashMap, then replace the value with  this
     * value.
     *
     * Check to see if the backing array needs to be regrown BEFORE
     * adding. For example, If my HashMap has a backing array of size 5, and 3
     * elements in it, I should regrow at the start of the next add operation.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * Should run in O(1) with a good hash function, O(n) otherwise.
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map.  If it was in the
     * map, return the old value associated with it.
     */
    @Override
    public V add(K key, V value) {
        if (key != null && value != null) {
            MapEntry<K, V> kv = new MapEntry<>(key, value);
            int len = table.length;
            int hashIndex = key.hashCode() % len;
            double val =  (size + 2) / (double) len;
            if ((val < MAX_LOAD_FACTOR || size == 0)) {
                if (!contains(key)) {
                    size++;
                    if (table[hashIndex] != null) {
                        MapEntry<K, V> old = table[hashIndex];
                        int collisions = 0;
                        if (old.getKey().equals(kv.getKey())
                                || old.isRemoved()) {
                            if (!old.isRemoved()) {
                                size--;
                            }
                            table[hashIndex] = kv;
                            table[hashIndex].setRemoved(false);
                            if (old.isRemoved()) {
                                return null;
                            }
                            return old.getValue();
                        }
                        while (table[hashIndex] != null) {
                            collisions++;
                            old = table[hashIndex];
                            if ((old != null && old.getKey().equals(key))
                                    || old.isRemoved()) {
                                size--;
                                table[hashIndex] = kv;
                                table[hashIndex].setRemoved(false);
                                if (old.isRemoved()) {
                                    return null;
                                }
                                return old.getValue();
                            }
                            hashIndex = key.hashCode()
                                    + collisions * collisions;
                            if (hashIndex > len) {
                                hashIndex = hashIndex % len;
                            }
                        }
                        if (table[hashIndex] == null
                                || table[hashIndex].isRemoved()) {
                            table[hashIndex] = kv;
                            table[hashIndex].setRemoved(false);

                            return null;
                        }
                        if (old != null && old.getKey().equals(kv.getKey())) {
                            size--;
                            table[hashIndex] = kv;
                            table[hashIndex].setRemoved(false);
                            return old.getValue();
                        }
                        table[hashIndex] = kv;
                        table[hashIndex].setRemoved(false);
                        return old.getValue();
                    } else {
                        table[hashIndex] = kv;
                        table[hashIndex].setRemoved(false);
                        return null;
                    }
                } else {
                    int keyHash = Math.abs(key.hashCode() % table.length);
                    if (table[keyHash] != null) {
                        if (!table[keyHash].getKey().equals(key)) {
                            int collisions = 0;
                            while (table[keyHash] != null) {
                                collisions++;
                                keyHash = key.hashCode()
                                        + collisions * collisions;
                                if (keyHash >= table.length) {
                                    keyHash = keyHash % table.length;
                                }
                                if (table[keyHash].getKey().equals(key)) {
                                    V oldVal = table[keyHash].getValue();
                                    table[keyHash] = kv;
                                    return oldVal;
                                }
                            }
                        } else {
                            V oldVal = table[keyHash].getValue();
                            table[keyHash] = kv;
                            return oldVal;
                        }
                    }
                }
            } else {
                rehash();
                add(key, value);
            }
        } else {
            throw new IllegalArgumentException("Cant add null");
        }
        return null;
    }
    /**
     * Removes the value associated with the key from the map. Think through
     * what needs to happen at the appropriate index in the array.
     *
     * Should run in O(1) with a good hash function, O(n) otherwise.
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key does not exist
     * @return the value previously associated with the key
     */
    @Override
    public V remove(K key) {
        if (key != null) {
            if (contains(key)) {
                int hashKey = key.hashCode() % table.length;
                int collisions = 0;
                while (table[hashKey] != null
                            && !table[hashKey].getKey().equals(key)) {
                    collisions++;
                    hashKey = key.hashCode() + collisions * collisions;
                    if (hashKey > table.length) {
                        hashKey = hashKey % table.length;
                    }
                }
                V val = table[hashKey].getValue();
                table[hashKey].setRemoved(true);
                size--;
                return val;
            } else {
                throw new java.util.NoSuchElementException("Not in Hash Map");
            }
        } else {
            throw new IllegalArgumentException("Can't get Null");
        }
    }
    /**
     * Gets the value associated with the given key.
     * Should run in O(1) with a good hash function, O(n) otherwise
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the value associated with the given key
     */
    @Override
    public V get(K key) {
        if (key != null) {
            if (contains(key)) {
                int hashKey = key.hashCode() % table.length;
                int collisions = 0;
                while (table[hashKey] != null
                            && !table[hashKey].getKey().equals(key)) {
                    collisions++;
                    hashKey = key.hashCode() + collisions * collisions;
                    if (hashKey >= table.length) {
                        hashKey = hashKey % table.length;
                    }
                }
                V val = table[hashKey].getValue();
                return val;
            } else {
                throw new java.util.NoSuchElementException("Not in Hash Map");
            }
        } else {
            throw new IllegalArgumentException("Can't get Null");
        }
    }
    /**
     * Returns whether or not the key is in the map.
     * Should run in O(1) with a good hash function, O(n) otherwise
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    @Override
    public boolean contains(K key) {
        if (key != null) {
            int keyHash = Math.abs(key.hashCode() % table.length);
            if (table[keyHash] != null) {
                if (!table[keyHash].getKey().equals(key)) {
                    int collisions = 0;
                    while (table[keyHash] != null) {
                        collisions++;
                        keyHash = key.hashCode() + collisions * collisions;
                        if (keyHash >= table.length) {
                            keyHash = keyHash % table.length;
                        }
                        if (table[keyHash] != null
                                    && table[keyHash].getKey().equals(key)) {
                            if (!table[keyHash].isRemoved()) {
                                return true;
                            }
                        }
                    }
                    return false;
                } else if (table[keyHash].isRemoved()) {
                    return false;
                }
                return true;
            }
            return false;
        } else {
            throw new IllegalArgumentException("Null");
        }
    }
    /**
     * Clears the table and resets it to the default size.
     * Should be O(1)
     */
    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }
    /**
     * Returns the number of elements in the map.
     * Should be O(1)
     * @return number of elements in the HashMap
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * DO NOT USE THIS METHOD IN YOUR CODE.  IT IS FOR TESTING ONLY
     * @return the backing array of the data structure, not a copy.  INCLUDE
     * EMPTY SPACES.
     */
    @Override
    public MapEntry<K, V>[] toArray() {
        return table;
    }
    /**
     * Returns a Set view of the keys contained in this map.
     * Use {@code java.util.HashSet}.
     * Should be O(n)
     *
     * @return set of keys in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (MapEntry<K, V> k : table) {
            if (k != null && !k.isRemoved()) {
                set.add(k.getKey());
            }
        }
        return set;
    }
    /**
     * Returns a List view of the values contained in this map.
     * Use any class that implements the List interface
     * This includes {@code java.util.ArrayList} and
     * {@code java.util.LinkedList}.
     * Should be O(n)
     *
     * @return list of values in this map
     */
    @Override
    public List<V> values() {
        List<V> list = new ArrayList<>();
        for (MapEntry<K, V> k : table) {
            if (k != null && !k.isRemoved()) {
                list.add(k.getValue());
            }
        }
        return list;
    }

}
