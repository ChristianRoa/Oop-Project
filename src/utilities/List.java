package utilities;

/**
 * This class represents a list of objects.
 * @author Christian Roa
 */
public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;

    @SuppressWarnings("unchecked")
    public List() {
        this.objects = (E[]) new Object[4]; // default capacity of 4
        this.size = 0;
    }

    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        E[] newObjects = (E[]) new Object[objects.length * 2];
        System.arraycopy(objects, 0, newObjects, 0, objects.length);
        objects = newObjects;
    }

    public boolean contains(E e) {
        return find(e) != -1;
    }

    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size++] = e;
    }

    public void remove(E e) {
        int index = find(e);
        if (index != -1) {
            objects[index] = objects[--size];
            objects[size] = null;
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
        if (index >= 0 && index < size) {
            return objects[index];
        }
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }

    public void set(int index, E e) {
        if (index >= 0 && index < size) {
            objects[index] = e;
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
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
            if (hasNext()) {
                return objects[currentIndex++];
            }
            throw new NoSuchElementException();
        }
    }
}

