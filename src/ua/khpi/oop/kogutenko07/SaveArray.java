package ua.khpi.oop.kogutenko07;

import java.util.Iterator;


/**
 * The type Save array.
 *
 * @param <E> the type parameter
 */
public class SaveArray<E extends Object> implements Array<E> {

    private E[] arrayData;
    //private ;

    /**
     * Instantiates a new Save array.
     */
    SaveArray()
    {
        arrayData = (E[]) new Object[0];
    }

    @Override
    public void add(E el) {
        try
        {
                E[] temp = arrayData;
                arrayData = (E[]) new Object[temp.length + 1];
                System.arraycopy(temp, 0 , arrayData, 0, temp.length);
                arrayData[arrayData.length - 1] = el;
        }
        catch (ClassCastException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void clear() {
        while (size() > 1) {
            remove(size()-1);
        }
        arrayData = (E[]) new Object[0];
    }

    @Override
    public boolean remove(int index) {
        //int index = findIndexOfElement(el);
        try {
            if (index == 0 && size() > 1) {
                E[] temp = arrayData;
                arrayData = (E[]) new Object[temp.length - 1];
                System.arraycopy(temp, 1, arrayData, 0, temp.length - 1);
                return true;
            }
            else if (index == 0 && size() == 1) {
                arrayData = (E[]) new Object[0];
                return true;
            }
            else if (index > 0 && size() == 0) {
                return false;
            }
            else if (index > 0 && size() > 0) {
                E[] temp = arrayData;
                arrayData = (E[]) new Object[temp.length - 1];
                System.arraycopy(temp, 0, arrayData, 0, index);
                int amountElemAfterIndex = temp.length - index - 1;
                System.arraycopy(temp, index + 1, arrayData, index, amountElemAfterIndex);
                return true;
            }
            else {
                return false;
            }
        }
        catch(ClassCastException ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public E[] toArray() {
        return null;
    }

    @Override
    public int size() {
        return arrayData.length;
    }

    @Override
    public boolean contains(E elem) {
        if(size() == 0)
        {
            return arrayData[0] == elem;
        }else if(size() > 0)
        {
            for(E el : arrayData)
            {
                if(el == elem) return true;
            }
            return false;
        }
        return false;
    }

    private int sumInteger(int[] arr)
    {
        int sum = 0;
        for(int i = 0;i < arr.length;sum+=arr[i++]);
        return sum;
    }
    @Override
    public boolean containsAll(E[] arr) {
        if(size() == 0 && arr.length == 0)
        {
            return arrayData[0] == arr[0];
        }else if(size() > 0 && arr.length == 0)
        {
            for(E el : arrayData)
            {
                if(el == arr[0]) return true;
            }
            return false;
        }else if(size() > 0 && arr.length > 0)
        {
            int check[] = new int[arr.length];
            int lenCheck = arr.length - 1;
            //for(int i = 0, k = 0; i < lenCheck; check[i] = k, i++);
            try
            {
                for(E el : arr)
                {
                    check[lenCheck--] = contains(el) ? 1 : 0;
                }
                if(sumInteger(check) == arr.length)
                {
                    return true;
                } else {
                    return false;
                }
            }catch(ArrayIndexOutOfBoundsException ex)
            {
                ex.printStackTrace();
            }

        }else if(size() == 0 && arr.length > 0)
        {
            for(E el : arr)
            {
                if(el == arrayData[0]) return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString()
    {
        String out = "size of reserved array is " + size() + "\n Content:\n";
        if(size() == 0)
        {
            out += "Array is empty";
        }else{
            int i = 1;
            for(E el : arrayData)
            {
                out = out + i++ + " : \n" + (String)el + "\n";
            }
        }
        return out;
    }

    public E get(int index){
        try
        {
            if(index < size())
                return arrayData[index - 1];
            else if (index < 0 || index > size()) {
                throw new Exception("Out of range!!!");
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(arrayData);
    }
}
