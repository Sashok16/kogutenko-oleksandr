package ua.khpi.oop.kogutenko07;

import java.io.*;

public class WriteObject {
    public static void main (String args[])
    {
        Date date = new Date();
        date.setDate(1,2,2002);
        Shop shop1 = new Shop(1 , "qwerty", "kg", 12, date, "some thing for sale");
        Shop shop2 = new Shop(2 , "asdfdg", "l", 13, date, "some thing for ...");
        Shop shop3 = new Shop(3 , "zxvcxv", "m", 123, date, "some thing ...");
        Shop shop4 = new Shop(4 , "fdgffg", "H", 112, date, "some ...");
        Shop shop5 = new Shop(5 , "qwweee", "E", 1, date, "some ...");
        Shop[] shops = {shop1,shop2,shop3,shop4,shop5};
        try{
            FileOutputStream fos = new FileOutputStream("shopsin7.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);


            oos.writeInt(shops.length);

            oos.writeObject(shop1);
            oos.writeObject(shop2);
            oos.writeObject(shop3);
            oos.writeObject(shop4);
            oos.writeObject(shop5);

            /*for (Shop shop : shops)
            {
                oos.writeObject(shop);
            }*/
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
