package ru.otus.hw02.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DiyArrayList<T> implements List<T> {

    private final int MIN_CAPACITY = 10;

    private int listCount;
    private T[] elements;

    public DiyArrayList() {
        init();
    }

    private boolean ensureCapacity(int indexToPut) {
        return elements.length > indexToPut;
    }

    private boolean isIndexInBounds(int index) {
        return index < listCount;
    }

    private void addOneElement(T el) {
        elements[listCount] = el;
        listCount++;
    }

    private void grow() {
        int oldCapacity = elements.length;
        int newCapacity = (int)(oldCapacity * 1.5);
        if (newCapacity < MIN_CAPACITY) {
            newCapacity = MIN_CAPACITY;
        }

        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void init() {
        listCount = 0;
        elements = (T[]) new Object[MIN_CAPACITY];
    }


    @Override
    public void clear() {
        init();
    }

    @Override
    public boolean add(T t) {
        if (!ensureCapacity(listCount)) {
            grow();
        }
        addOneElement(t);

        return true;
    }

    @Override
    public T get(int index) {
        if (!isIndexInBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (!isIndexInBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
        T oldEl = get(index);
        elements[index] = element;

        return oldEl;
    }

    @Override
    public boolean isEmpty() {
        return listCount == 0;
    }

    @Override
    public int size() {
        return listCount;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, listCount);
    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return isIndexInBounds(currentIndex);
            }

            @Override
            public T next() {
                var res = get(currentIndex);
                currentIndex++;
                return res;
            }
        };
    }


    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<>() {
            int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex != size();
            }

            @Override
            public T next() {
                currentIndex++;
                var res = get(currentIndex);
                return res;
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                DiyArrayList.this.set(currentIndex, t);
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
