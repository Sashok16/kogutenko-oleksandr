package ua.khpi.oop.kogutenko07;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The type Helper class with console.
 */
public class HelperClass extends Object{

    private Array<Shop> save = new SaveArray<>();


    /**
     * Instantiates a new Helper class with console.
     */
    public HelperClass() { }

    /**
     * Gets info of helper object.
     *
     * @return the info of helper object
     */
    @Override
    public String toString()
    {
        String str = "";
        for(Shop el : save)
        {
            str += el.toString() + "\n";
        }
        return str;
    }

    /**
     * Print saves.
     */
    public void printSaves()
    {
        String str = "";
        for(Shop shop : save)
        {
            str += shop.toString();
        }
        System.out.println(str);
    }

    /**
     * Changed text.
     *
     * @throws Exception the exception
     */

    public void add()
    {
        Shop shop = new Shop();
        shop.add();
        save.add(shop);
    }
    public void remove()
    {
        printSaves();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of id: ");
        int id = sc.nextInt();
        save.remove(id - 1 );
        printSaves();
    }

    public void serialization(String savefile)
    {
        File file = new File(savefile);///pathname
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(save.size());
            System.out.println("size :" + save.size());
            for (Shop el : save)
            {
                oos.writeObject(el);
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializtion(String savefile) {
        //Array<Shop> container = new SaveArray<>();
        File file = new File(savefile);///pathname
        try {
            FileInputStream fis = new FileInputStream(file);///pathname
            ObjectInputStream ois = new ObjectInputStream(fis);
            Integer count = ois.readInt();
            for(int i = 0; i < count; i++)
            {
                save.add((Shop)ois.readObject());
            }

        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();	}
        catch (ClassNotFoundException e) {e.printStackTrace();	}
    }
}
