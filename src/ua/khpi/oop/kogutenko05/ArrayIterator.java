package ua.khpi.oop.kogutenko05;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для массива
 *
 * @param <E> the type parameter
 */
public class ArrayIterator<E> implements Iterator<E>/*, Serializable, Array<E>*/{
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
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public E next() {
        return values[index++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove item from array.");
    }
}
/*

    private E[] elements;
    private int index;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    protected int count = 0;

    // конструктор
    public MyArrayListIterator(){
        elements = (E[])new Object[DEFAULT_CAPACITY];
    }

    // добавление элемента
    @Override
    public void add(E value) {
        if (index == elements.length){
            growArray();
        }
        elements[index] = value;
        index++;
        size++;
    }

    // добавление по индексу
    @Override
    public E get(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        return (E) elements[index];
    }

    // замена значения по указанному индексу
    @Override
    public boolean set(int index, E value) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        if (index < size){
            E oldValue = elements(index);
            elements[index] = value;
            return true;
        }
        return false;
    }


    // добавление значения по индексу (происходит сдвиг всего массива)
    @Override
    public boolean add(int index, E value) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
        return true;
    }

    // поиск значения и вывод индекса элемента
    @Override
    public int indexOf(E value) {
        if (value == null){
            for (int i = 0; i < size; i++){
                if (elements[i] == null){
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++){
                if (value.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    // размер массива
    @Override
    public int size() {
        return size;
    }

    // удаление элемента по индексу
    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        E oldValue = elements[index];
        int value = size - index - 1;
        if (value > 0){
            System.arraycopy(elements, index+1, elements, index, value);
        }
        elements[--size] = null;
        return oldValue;
    }

    // итератор
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor;
            @Override
            public boolean hasNext() {
                if (size >= elements.length){
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                int i = cursor;
                if (i >= size){
                    throw new NoSuchElementException();
                }
                E[] elements2 = elements;
                if (i > elements2.length){
                    throw new ConcurrentModificationException();
                }
                cursor = i + 1;
                return (E) elements2[i];
            }
        };
    }

    // увеличение массива
    private void growArray(){
        E[] newArray = (E[])new Integer[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, index - 1);
        elements = newArray;
    }

    // проверка индекса
    private void checkIndex(int index) {
        if (index < 0 || index >= this.index) {
            throw new IllegalArgumentException();
        }
    }

    private E elements(int index) {
        return (E) elements[index];
    }

 */