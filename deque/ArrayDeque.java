package deque;

import java.util.Iterator;

/**
 *
 * @author zhijiazhang
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
    }

    /*
    master resize method
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity, int initialStart, int newStart, int sizeAdjustment) {

        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, initialStart, a, newStart, sizeAdjustment);
        items = a;
    }

    @Override
    public void addFirst(T item) {
        this.resize(this.items.length + 1, 0, 1, this.size);
        this.items[0] = item;
        this.size++;
    }

    @Override
    public void addLast(T item) {
        if (this.items.length == 0) {
            this.items = (T[]) new Object[10];
        }
        if (this.items.length == this.size) {
            this.resize(this.size * 2, 0, 0, this.size);
        }
        this.items[size] = item;
        this.size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.items[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        T item = this.items[0];
        this.resize(this.items.length - 1, 1, 0, this.size - 1);
        this.size--;
        if (this.size < this.items.length / 4) {
            this.resize(this.items.length / 2, 0, 0, this.size);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        T item = this.items[this.size - 1];
        this.size--;
        if (this.size < this.items.length / 4) {

            this.resize(this.items.length / 2, 0, 0, this.size);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (this.size == 0 || index >= size) {
            return null;
        }
        return this.items[index];
    }


    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int position;

        ArrayDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size();
        }

        public T next() {
            T returnItem = items[position];
            position += 1;
            return returnItem;
        }
    }


    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof Deque) {
            Deque otherD = (Deque) o;

            if ((this.size() != otherD.size())) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!this.get(i).equals(otherD.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}




