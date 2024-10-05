package utilities;

import mypackage.Appointment;

import java.util.Iterator;

/**
 * This class represents a list of objects.
 * @author Christian Roa, Renil Khristi
 */
public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size; // number of appointments in the array
    private static final int NOT_FOUND = -1; // A constant used to define something that was not found
    private static final int GROWTH_INCREMENT = 4;


    public List() {
        this.objects = new E[GROWTH_INCREMENT];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private int find(E e) {
        for (int i = 0; i < this.size; i++) {
            if (this.objects[i].equals(objects)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        E[] newAppointments = new E[this.objects.length + GROWTH_INCREMENT];
        for (int i = 0; i < this.size; i++) {
            newAppointments[i] = this.objects[i];
        }
        this.objects = newAppointments;
    }

    public boolean contains(E e) {
        int appointmentCheck = find(objects);
        return appointmentCheck != NOT_FOUND;
    }

    public void add(E e) {
        if (this.objects.length == this.size) {
            this.grow();
        }
        this.objects[this.size++] = appointment;
    }

    public void remove(E e) {
        int index = find(appointment);
        if (index == NOT_FOUND) {
            return;
        }
        for (int i = index; i < this.size - 1; i++) {
            objects[i] = this.objects[i + 1];
        }
        this.objects[--this.size] = null;
    }

    public boolean isEmpty(){

    }

    public int size() {}

    public Iterator<E> iterator(){}

    public E get(int index) {}

    public void set(int index, E e) {}

    public int indexOf(E e) {}

    private class ListIterator implements Iterator<E> {
        public boolean hasNext() {}
        public E next() {}
    }

    }
}
