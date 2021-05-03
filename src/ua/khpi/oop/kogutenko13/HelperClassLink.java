package ua.khpi.oop.kogutenko13;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Helper class link.
 *
 * @param <T> the type parameter
 */
public class HelperClassLink<T> implements Iterable<T> {
    private Node firstElem;
    private Node lastElem;
    private volatile int N;

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
    public synchronized T get(int index) {
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
    public synchronized void add(T item) {

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
    public synchronized boolean remove(int index) {

        if (index < 0 || index > N - 1) {
            throw new IllegalArgumentException("OUT OF RANGE - " + index);
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
    public synchronized void printList() {
        String str = "\n";
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

    public synchronized void serialization(int idS, File file) {
        switch (idS){
            case 1 :
                serializationXML(file);
                break;
            case 2 :
                serializationBIN(file);
                break;
            case 3 :
                serializationTXT(file);
                break;

        }
    }

    private synchronized void serializationTXT(File file) {
        try (PrintWriter pw = new PrintWriter(file.getAbsolutePath())) {
            System.out.println("size :" + size());
            for (T el : this) {
                pw.write(el.toString());
                System.out.print(el.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serialization xml.
     */
    private synchronized void serializationXML(File file) {
        try {
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(file)));

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
    private synchronized void serializationBIN(File file) {
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


    public void deserialization(int idD, File file){
        switch (idD){
            case 1 :
                deserializationXML(file);
                break;
            case 2 :
                deserializationBIN(file);
                break;
            case 3 :
                deserializationTXT(file);
                break;
        }
    }

    /**
     * Deserializtion bin.
     */
    private synchronized void deserializationBIN(File file) {
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
    private synchronized void deserializationXML(File file) {
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(file)
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

    private synchronized void deserializationTXT(File file) {
        try {
            //file.length();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            String line, id = null,
                    name = null,
                    unit = null,
                    count = null,
                    price = null,
                    date = null,
                    description = null;
            Pattern p;
            Matcher m;
            while ((line = br.readLine()) != null) {
                if (line.contains("id:")) {
                    //regex
                    p = Pattern.compile("[0-9^\\s]+");
                    id = line.substring(4, line.indexOf(" | name:")).trim();
                    m = p.matcher(id);
                    if (!m.matches()) {
                        id = "0";
                    }
                }
                if (line.contains("name:")) {
                    //regex
                    p = Pattern.compile("^[\\w^\\s]{3,15}$");
                    name = line.substring(line.indexOf("name: ") + 6, line.indexOf(" | unit:")).trim();
                    m = p.matcher(name);
                    if (!m.matches()) {
                        name = "prod";
                    }
                }
                if (line.contains("unit:")) {
                    //regex
                    p = Pattern.compile("kg|l|kg/l");
                    unit = line.substring(line.indexOf("unit:") + 6, line.indexOf(" | count: ")).trim();
                    m = p.matcher(unit);
                    if (!m.matches()) {
                        unit = "obj";
                    }
                }
                if (line.contains("count:")) {
                    //regex
                    p = Pattern.compile("[0-9[^\\n\\t\\f\\r]]+");
                    count = line.substring(line.indexOf("count:") + 7, line.indexOf(" | price: ")).trim();
                    m = p.matcher(count);
                    if (!m.matches()) {
                        count = "0";
                    }
                }
                if (line.contains("price")) {
                    //regex
                    p = Pattern.compile("[0-9[^\\n\\t\\f\\r]]+");
                    price = line.substring(line.indexOf("price:") + 7, line.indexOf(" | date: ")).trim();
                    m = p.matcher(price);
                    if (!m.matches()) {
                        price = "0";
                    }
                }
                if (line.contains("date:")) {
                    //regex
                    p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                    date = line.substring(line.indexOf("date:") + 6, line.indexOf(" | description: "));
                    m = p.matcher(date);
                    if (!m.matches()) {
                        date = "01/01/2021";
                    }
                }
                if (line.contains("description:")) {
                    //regex
                    description = line.substring(line.indexOf("description:") + 13, line.length() - 1);
                }

                Shops shop = new Shops();
                shop.setId(Integer.parseInt(id));
                shop.setCount(Integer.parseInt(count));
                shop.setName(name);
                shop.setDate(date);
                shop.setUnit(unit);
                shop.setPrice(Integer.parseInt(price));
                shop.setDescription(description);
                this.add((T)shop);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized Shops[] toArray() throws ClassCastException {
        Shops[] arr = new Shops[N];
        for (int i = 0; i < N; i++) arr[i] = (Shops) get(i);
        return arr;
    }

    /**
     * From array.
     *
     * @param array the array
     */
    public synchronized HelperClassLink<T> fromArray(T[] array) {
        System.out.println("start from array");
        HelperClassLink<T> list = new HelperClassLink<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    public synchronized HelperClassLink<Shops> sort(HelperClassLink<Shops> list) {
        System.out.println("list before:\n");
        System.out.println("\n------------------------------------\n");
        list.printList();
        System.out.println("\n------------------------------------\n");
        Shops[] shops = list.toArray();
        Integer field;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter field sorted (1 - name; 2 - price; 3 - date\n>>>");
            Pattern p = Pattern.compile("[123]");
            field = scanner.nextInt();
            Matcher m = p.matcher(field.toString());
            if (m.matches()) {
                break;
            } else {
                System.out.println("Enter info correctly!!!");
            }
        }
        bubbleSort(shops, field);
        return new HelperClassLink<>(shops);
    }

    public synchronized Shops[] bubbleSort(Shops[] array, int field) {
        System.out.println("start sort");
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < array.length; i++) {
                if (compare(array[i], array[i - 1], field)) {
                    swap(array, i, i - 1);
                    sorted = false;
                }
            }
        }
        return array;
    }

    private boolean compare(Shops a, Shops b, int field) {
        switch (field) {
            case 1:
                return a.getName().compareTo(b.getName()) >= 0;
            case 2:
                return a.getPrice() < b.getPrice();
            case 3:
                return (a.getDate().getYear() > b.getDate().getYear())
                        || (a.getDate().getYear() == b.getDate().getYear() && a.getDate().getMonth() > b.getDate().getMonth())
                        || (a.getDate().getYear() == b.getDate().getYear() && a.getDate().getMonth() == b.getDate().getMonth()
                        && a.getDate().getDay() > b.getDate().getDay());

        }
        return false;
    }

    private void swap(Shops[] array, int ind1, int ind2) {
        Shops tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    /**
     * Find fresh string.
     *
     * @return the string
     */
    public synchronized String findFresh(HelperClassLink<Shops> fromList) {
        System.out.println("Start freshening");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new Date();
        System.out.println("                                                                             Today: " + dateFormat.format(date));
        String dateStr = dateFormat.format(date);
        String[] dateArr = dateStr.split("/");
        int currYear = Integer.parseInt(dateArr[0]), currMon = Integer.parseInt(dateArr[1]), currDay = Integer.parseInt(dateArr[2]);
        String str = "";
        HelperClassLink<Shops> list = fromList;
        for (Shops shop : list) {
            int prodY = shop.getDate().getYear();
            int prodM = shop.getDate().getMonth();
            int prodD = shop.getDate().getDay();
            if (prodY == currYear) { // if year prod == curr year
                if (prodM == currMon) {
                    if (prodD >= currDay) {
                        str += shop.toString();
                    }
                } else if (currMon - prodM == 1 && prodD >= currDay) {
                    str += shop.toString();
                }
            } else if (prodY == currYear - 1 && prodM == 12 && prodD >= currDay) {
                str += shop.toString();
            }
        }
        return str;
    }
}


