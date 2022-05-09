package com.example.fastfoodpos_rev3;

import java.util.Collection;
import java.util.LinkedList;

public class SomethingSet<E> implements Collection<E> {
    // Define the default hash table size.
    private final static int DEFAULT_INITIAL_CAPACITY = 20;

    // Define the maximum hash table size.
    private final static int MAXIMUM_CAPACITY = 1 << 60;

    // Current hash table capacity.
    private int capacity;

    // Define default load factor
    private final static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;


    private float loadFactorThreshold;


    private int size = 0;


    private LinkedList<E>[] table;

    /**
     * Set with the default capacity and load factor
     */
    public SomethingSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Sets initial capacity and default load factor
     */
    public SomethingSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Set with the specified initial capacity and load factor
     */
    public SomethingSet(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }

    @Override
    /** Remove elements from this set */
    public void clear() {
        size = 0;
        removeElements();
    }

    @Override

    public boolean contains(Object e) {
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            return bucket.contains(e);
        }

        return false;
    }

    @Override
    /** Add an element to the set.  Returns false if duplicate */
    public boolean add(E e) {
        if (contains(e)) // Duplicate element not stored
            return false;

        if (size + 1 > capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");

            rehash();
        }

        int bucketIndex = hash(e.hashCode());

        // Create a linked list for the bucket if not created
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<E>();
        }

        // Add e to hashTable[index]
        table[bucketIndex].add(e);

        size++;

        return true;
    }

    @Override
    /** Remove the element from the set */
    public boolean remove(Object e) {
        if (!contains(e))
            return false;

        int bucketIndex = hash(e.hashCode());

        // Creates a linked list for the bucket
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            bucket.remove(e);
        }

        size--;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    /** Return true if the set contains no elements */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /** Return the number of elements in the set */
    public int size() {
        return size;
    }

    @Override
    /** Return an iterator for the elements in set */
    public java.util.Iterator<E> iterator() {
        return new OrderHashSetIterator(this);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    private class OrderHashSetIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list;
        private int current = 0; // Point to the current element in list
        private SomethingSet<E> set;

        /**
         * Create a list from the set
         */
        public OrderHashSetIterator(SomethingSet<E> set) {
            this.set = set;
            list = setToList();
        }

        @Override
        /** Next element for traversing? */
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        /** Get current element and move cursor to the next */
        public E next() {
            return list.get(current++);
        }

    }

    /**
     * Hash function
     */
    private int hash(int hashCode) {
        return hashCode & (capacity - 1);
    }

    /**
     * Return a power of 2 for initialCapacity
     */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

    /**
     * Remove all e from each bucket
     */
    private void removeElements() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    /**
     * Rehash the set
     */
    private void rehash() {
        java.util.ArrayList<E> list = setToList(); // Copy to a list
        capacity <<= 1; // Double capacity
        table = new LinkedList[capacity]; // Create a new hash table
        size = 0; // Reset size

        for (E element : list) {
            add(element); // Add from the old table to the new table
        }
    }

    /**
     * Copy elements in the hash set to an array list
     */
    private java.util.ArrayList<E> setToList() {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (E e : table[i]) {
                    list.add(e);
                }
            }
        }

        return list;
    }

    @Override
    public String toString() {
        java.util.ArrayList<E> list = setToList();
        StringBuilder builder = new StringBuilder("[");

        // Add the elements except the last one to the string builder
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i) + ", ");
        }

        // Add the last element in the list to the string builder
        if (list.size() == 0)
            builder.append("]");
        else
            builder.append(list.get(list.size() - 1) + "]");

        return builder.toString();
    }
}