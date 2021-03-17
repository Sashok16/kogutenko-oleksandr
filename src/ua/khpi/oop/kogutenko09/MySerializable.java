//package ua.khpi.oop.kogutenko09;
//
//import java.beans.XMLDecoder;
//import java.beans.XMLEncoder;
//import java.io.*;
//import java.util.Map;
//
//public class MySerializable<T> implements Serializable {
//
//    public void serializationDefualt(T list)
//    {
//        try{
//            XMLEncoder encoder = new XMLEncoder(
//                    new BufferedOutputStream(
//                            new FileOutputStream( ua.khpi.oop.kogutenko08.ConsoleFile.MenuFillOut())));
//
//            encoder.writeObject(list.size());
//
//            for(T shop : list) {
//                encoder.writeObject(shop);
//                encoder.writeObject(shop.getDescription());
//            }
//
//            encoder.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deserializtion() {
//        //Array<Shop> container = new SaveArray<>();
//        File file = ua.khpi.oop.kogutenko08.ConsoleFile.MenuFillIn();///pathname
//        try {
//            FileInputStream fis = new FileInputStream(file);///pathname
//            ObjectInputStream ois = new ObjectInputStream(fis);
//
//            int count = ois.readInt();
//
//            for(int i = 0; i < count; i++)
//            {
//                Shops shops = (Shops)ois.readObject();
//                save.add(shops);
//            }
//            ois.close();
//        }
//        catch(FileNotFoundException e) {e.printStackTrace();}
//        catch (IOException e) {e.printStackTrace();	}
//        catch (ClassNotFoundException e) {e.printStackTrace();	}
//    }
//    public void deserializtionXML()
//    {
//        try{
//            XMLDecoder decoder = new XMLDecoder(
//                    new BufferedInputStream(
//                            new FileInputStream(ConsoleFile.MenuFillIn())
//                    )
//            );
//
//            int count = (int) decoder.readObject();
//
//            for(int i = 0; i < count; i++)
//            {
//                Shops shops = (Shops)decoder.readObject();
//                Object obj = decoder.readObject();
//                shops.setDescription((Map<String, String>) obj);
//                save.add(shops);
//            }
//            decoder.close();
//
//        }
//        catch(FileNotFoundException e) {e.printStackTrace();}
//    }
//
//}
