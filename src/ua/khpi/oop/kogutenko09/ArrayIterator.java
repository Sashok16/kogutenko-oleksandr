package ua.khpi.oop.kogutenko09;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Итератор для массива
 *
 * @param <E> the type parameter
 */
public class ArrayIterator<E> implements Iterator<E>, Serializable/* Array<E>*/{
    private int index = 0;
    /**
     * The Values.
     */
    E[] values;

    /**
     * Instantiates a new Array iterator.
     *
     * @param values the values
     */
    ArrayIterator(E[] values)
    {
        this.values = values;
    }

    @Override
    public boolean hasNext()
    {
        return index < values.length;
    }

    @Override
    public E next()
    {
        return values[index++];
    }

    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Cannot remove item from array.");
    }
}
