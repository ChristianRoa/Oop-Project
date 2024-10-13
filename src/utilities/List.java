package utilities;
import java.util.Iterator;

public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;
    private static final int untrue = -1;

    @SuppressWarnings("unchecked")
    public List() {
        objects = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        @SuppressWarnings("unchecked")
        E[] newObjects = (E[]) new Object[objects.length * 2];
        System.arraycopy(objects, 0, newObjects, 0, size);
        objects = newObjects;
    }

    public boolean contains(E e) {
        return find(e) != untrue;
    }

    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size++] = e;
    }

    public void remove(E e) {
        int index = find(e);
        if (index != untrue) {
            System.arraycopy(objects, index + 1, objects, index, size - index - 1);
            size--;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
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

        public boolean hasNext() {
            return currentIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements in the iterator.");
            }
            return objects[currentIndex++];
        }
    }
}