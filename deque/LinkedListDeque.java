package deque;

import java.util.Iterator;


public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {

        T item;

        Node next;

        Node prev;

        Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }


    private int size;


    private Node sentinel;


    public LinkedListDeque() {
        this.sentinel = new Node((T) "sentinel", null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {

        Node newNode = new Node(item, null, null);

        //holds the front node which is the next node after sentinel
        Node temp = this.sentinel.next;

        //case where list is empty
        if (this.sentinel.next == this.sentinel && this.sentinel.prev == this.sentinel) {

            //sentinel prev and next both point to new node
            this.sentinel.prev = newNode;
            this.sentinel.next = newNode;
            //new node prev and next both point to sentinel
            newNode.next = this.sentinel;
            newNode.prev = this.sentinel;
        } else {
            //sentinel.next points to new node
            this.sentinel.next = newNode;
            //newNode.prev points to this.sentinel
            newNode.prev = this.sentinel;
            //newNode.next points to the previous first node
            newNode.next = temp;
            //previous first node prev now points ot the new nod
            temp.prev = newNode;
        }
        this.size++;

    }

    @Override
    public void addLast(T item) {

        //creates new node to add
        Node newNode = new Node(item, null, null);

        //holds the last node which is the "previous" node of sentinel
        Node temp = this.sentinel.prev;

        //case where list is empty
        if (this.sentinel.next == this.sentinel && this.sentinel.prev == this.sentinel) {

            //sentinel prev and next both point to new node
            this.sentinel.prev = newNode;
            this.sentinel.next = newNode;
            //new node prev and next both point to sentinel
            newNode.next = this.sentinel;
            newNode.prev = this.sentinel;
        } else {
            //sentinel node prev now points at the new node, making it the last node
            this.sentinel.prev = newNode;
            //new node next points at the sentinel node
            newNode.next = this.sentinel;
            //temp next (which was the old last node) points at the new node
            temp.next = newNode;
            //new node prev points at temp
            newNode.prev = temp;
        }
        this.size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {

        //start at front and print -->
        Node temp = this.sentinel.next;

        while (temp != this.sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {

        Node temp = this.sentinel.next;

        //if sentinel next is pointing to itself, that means it is empty
        if (temp == this.sentinel) {
            return null;
        } else {
            //this breaks the connection of the first node
            this.sentinel.next = temp.next;
            temp.next.prev = this.sentinel;

            //first node set prev and next to null
            temp.next = null;
            temp.prev = null;
        }
        this.size--;
        return temp.item;
    }


    @Override
    public T removeLast() {

        //holds the last node
        Node temp = this.sentinel.prev;

        //if sentinel next is pointing to itself, that means it is empty
        if (temp == this.sentinel) {
            return null;
        } else {
            //sentinel node prev now points to node before the last node
            this.sentinel.prev = temp.prev;
            //previous node next points to the sentinel node
            temp.prev.next = this.sentinel;

            //last node prev and next points to null
            temp.prev = null;
            temp.next = null;
        }
        this.size--;
        return temp.item;
    }


    @Override
    public T get(int index) {

        int counter = 0;
        Node temp = this.sentinel.next;
        while (temp != this.sentinel) {
            if (counter == index) {
                return temp.item;
            }
            counter++;
            temp = temp.next;
        }
        return null;
    }


    public T getRecursive(int index) {

        if (index < 0 || index >= this.size) {

            return null;
        }

        //implements recursive helper
        return this.getRecursiveHelper(0, index, this.sentinel.next);
    }

    private T getRecursiveHelper(int counter, int index, Node n) {

        //BC
        if (index == counter) {
            return n.item;
        }

        return getRecursiveHelper(counter + 1, index, n.next);
    }


    //ALSO need to implement these 2 special methods

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int position;

        LinkedListDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size();
        }

        public T next() {
            T returnItem = get(position);
            position += 1;
            return returnItem;
        }
    }

    @Override
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



