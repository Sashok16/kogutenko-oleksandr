package ua.khpi.oop.kogutenko12;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Helper class link.
 *
 * @param <T> the type parameter
 */
public class HelperClassLink<T> implements Iterable<T> {
    private Node firstElem;
    private Node lastElem;
    private int N;

    /**
     * Instantiates a new Helper class link.
     */
    public HelperClassLink() {
        firstElem = null;
        lastElem = null;
        N = 0;
    }

    /**
     * Instantiates a new Helper class link.
     *
     * @param arr the arr
     */
    public HelperClassLink(T[] arr) {
        for (int i = 0; i < arr.length; i++) this.add(arr[i]);
    }

    //////////////////////////////////////////////////////////
    private class Node {
        private T data;
        private Node next;

        /**
         * Instantiates a new Node.
         *
         * @param data the data
         * @param next the next
         */
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        /**
         * Instantiates a new Node.
         */
        public Node() {
        }

        /**
         * Data of elem t.
         *
         * @return the t
         */
        public T dataOfElem() {
            return data;
        }
    }

    ///////////////////////////////////////////////////////////
    private class LinkedListIterator implements Iterator<T> {
        private Node current = firstElem;

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.data;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
///////////////////////////////////////////////////////////

    /**
     * Get t.
     *
     * @param index the index
     * @return the t
     */
    public T get(int index) {
        if (index < 0 || index >= N) {
            throw new IndexOutOfBoundsException();
        }
        Node result = firstElem;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }

        return result.data;
    }

    /**
     * Add.
     *
     * @param item the item
     */
    public void add(T item) {

        if (item == null) {
            throw new NullPointerException("The first argument for addLast() is null.");
        }
        if (!isEmpty()) {
            Node prev = lastElem;
            lastElem = new Node(item, null);
            prev.next = lastElem;
        } else {
            lastElem = new Node(item, null);
            firstElem = lastElem;
        }
        N++;
    }

    /**
     * Remove boolean.
     *
     * @param index the index
     * @return the boolean
     */
    public boolean remove(int index) {

        if (index < 0 || index > N - 1) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            firstElem = firstElem.next;
            System.out.println("removing is \n" + firstElem.data.toString());
        } else {
            Node node = findNodeBeforeByIndex(index);
            Node tmp = findByIndex(index);
            node.next = tmp.next;
            System.out.println("removing is \n" + tmp.data.toString());
        }
        N--;
        return false;
    }

    /**
     * Remove element boolean.
     *
     * @param element the element
     * @return the boolean
     */
    public boolean removeElement(T element) {

        if (N == 0) {
            return false;
        } else if (N == 1) {
            firstElem = null;
            lastElem = null;
            N = 0;
            return true;
        }

        Node nodeBefore = findNodeBefore(element);

        if (nodeBefore.data == null) {
            firstElem = firstElem.next;
            N--;
            return true;
        } else if (nodeBefore != null) {
            if (lastElem.data == element) {
                nodeBefore.next = null;
                lastElem = nodeBefore;
            } else {
                nodeBefore.next = nodeBefore.next.next;
            }
            N--;
            return true;
        }
        return false;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {

        return N;
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {

        return N == 0;
    }

    public Iterator<T> iterator() {

        return new LinkedListIterator();
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    }

    /**
     * Print list.
     */
    public void printList() {

        String str = "";
        for (T item : this) {
            str += item.toString();
        }
        System.out.println(str);
    }

    private Node findByIndex(int index) {

        if (index < 0 || index > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        int tmpIndex = 0;
        if (firstElem == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return firstElem;
        }

        Node node = firstElem;
        while (node.next != null) {
            node = node.next;
            tmpIndex++;
            if (tmpIndex == index) {
                return node;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    private Node findNodeBefore(T value) {

        if (firstElem.data == value) {
            Node res = new Node(firstElem.data, firstElem.next);
            return res;
        }

        Node node = firstElem;
        while (node.next != null) {
            if (node.next == value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private Node findNodeBeforeByIndex(int index) {

        if (index <= 0 || index > N - 1) {
            return null;
        }

        int count = 0;
        Node node = firstElem;
        while (node.next != null) {
            if (count == index - 1) {
                return node;
            }
            count++;
            node = node.next;
        }
        return null;
    }

    /**
     * Serialization xml.
     */
    public void serializationXML() {

        try {
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(ConsoleFile.MenuFillOut(".xml"))));

            encoder.writeObject(this.size());

            for (T shop : this) {
                encoder.writeObject(shop);
            }

            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serialization bin.
     */
    public void serializationBIN() {
        File file = ConsoleFile.MenuFillOut(".bin");///pathname
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.size());
            System.out.println("size :" + this.size());
            for (T el : this) {
                oos.writeObject(el);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializtion bin.
     */
    public void deserializationBIN() {
        File file = ConsoleFile.MenuFillIn(".bin");///pathname
        try {
            FileInputStream fis = new FileInputStream(file);///pathname
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer count = ois.readInt();
            for (int i = 0; i < count; i++) {
                this.add((T) ois.readObject());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializtion xml.
     */
    public void deserializationXML() {
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(ConsoleFile.MenuFillIn(".xml"))
                    )
            );

            int count = (int) decoder.readObject();

            for (int i = 0; i < count; i++) {
                T shops = (T) decoder.readObject();
                this.add(shops);
            }
            decoder.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * To array t [ ].
     *
     * @return the t [ ]
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[N];
        for (int i = 0; i < N; i++) arr[i] = get(i);
        return arr;
    }

    /**
     * From array.
     *
     * @param array the array
     */
    public void fromArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }
}


