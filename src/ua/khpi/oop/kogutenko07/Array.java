package ua.khpi.oop.kogutenko07;


import java.util.Iterator;

/**
 * The interface Array.
 *
 * @param <E> the type parameter
 */
public interface Array<E> extends Iterable<E>{

    /**
     * повертае елемент за заданим індексом.
     *
     * @param index the index
     * @return e
     * @throws Exception the exception
     */
    E get(int index) throws Exception;

    /**
     * повертає вміст контейнера у вигляді рядка;
     *
     * @return
     */
    String toString();

    /**
     * додає вказаний елемент до кінця контейнеру;
     *
     * @param el the el
     */
    void add(E el);

    /**
     * видаляє всі елементи з контейнеру;
     */
    void clear();

    /**
     * видаляє перший випадок вказаного елемента з контейнера;
     *
     * @param index the el
     * @return
     */
    boolean remove(int index);

    /**
     * повертає масив, що містить всі елементи у контейнері;
     *
     * @return the e [ ]
     */
    E[] toArray();

    /**
     * повертає кількість елементів у контейнері;
     *
     * @return the int
     */
    int size();

    /**
     * повертає true, якщо контейнер містить вказаний елемент;
     *
     * @param el the el
     * @return the boolean
     */
    boolean contains(E el);

    /**
     * повертає true, якщо контейнер містить всі елементи з зазначеного у параметрах;
     *
     * @param el the el
     * @return the boolean
     */
    boolean containsAll(E[] el);

    /**
     * повертає ітератор відповідно до Interface Iterable.
     * @return
     */
    public Iterator<E> iterator();
}