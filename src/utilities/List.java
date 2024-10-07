package utilities;

import java.util.Iterator;

/**
 * This class represents a list of objects.
 * @author Christian Roadw
 */
public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;
    private static final int NOT_FOUND = -1;
    private static final int GROWTH_INCREMENT = 4;

    public List() {
        this.objects = (E[]) new Object[GROWTH_INCREMENT];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private int find(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.objects[i].equals(e)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        E[] newObjects = (E[]) new Object[this.objects.length + GROWTH_INCREMENT];
        System.arraycopy(this.objects, 0, newObjects, 0, this.size);
        this.objects = newObjects;
    }

    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }

    public void add(E e) {
        if (this.objects.length == this.size) {
            this.grow();
        }
        this.objects[this.size++] = e;
    }

    public void remove(E e) {
        int index = find(e);
        if (index == NOT_FOUND) {
            return;
        }
        for (int i = index; i < this.size - 1; i++) {
            objects[i] = this.objects[i + 1];
        }
        this.objects[--this.size] = null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return getSize();
    }

    public Iterator<E> iterator() {
        return new ListIterator();
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return objects[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        objects[index] = e;
    }

    public int indexOf(E e) {
        return find(e);
    }

    private class ListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return objects[currentIndex++];
        }
    }
}

