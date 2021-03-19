package ua.khpi.oop.kogutenko08;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleFile {

    public static Scanner in = new Scanner(System.in);


    public static int dialogOut() throws IOException, InterruptedException
    {
        System.out.print("\n\n Оберіть команду:"
                        +"\n 1 - Місце перебування"
                        +"\n 2 - Файли в директорії"
                        +"\n 3 - Вийти із директорії"
                        +"\n 4 - Вибрати файл на перевірку"
                        +"\n 5 - Створити директорію у цій дерикторії"
                        +"\n 6 - Перейти за адресою:"
                        +"\n 7 - Ім'я збереженого файлу у який збережімо колекцію"
                        +"\n\n>>>: ");
        return in.nextInt();
    }

    public static File MenuFillOut() ///функціє проводить координування по можливостям програм
    {
        File file = new File("D:/eclips-workspace/kogutenko-oleksandr/src/ua/khpi/oop/");
        while(true)///нескінченний цикл який дозволяє працювати програмі
        {
            try {
                int  key = dialogOut();

                switch(key)///пошук введеної команди
                {
                    case 1:
                        System.out.println("\n Шлях: " + file.getPath()); //getAbsolutePath
                        break;
                    case 2:
                        int a = 0;
                        System.out.print("Файли: " + file.getPath() + "\n");
                        for (File file2 : file.listFiles())
                        {
                            if ( a % 5 == 0 )
                                System.out.print("\n");
                            a++;
                            System.out.printf("%-25s  ", file2.getName());
                        } ;///////////
                    break;
                    case 3:
                        file = file.getParentFile();
                        break;
                    case 4:
                        System.out.print("Ведіть назву файлу: ");
                        in.nextLine();
                        file = new File(file.getAbsolutePath() + "/" + in.nextLine());
                        if (file.isFile() == true)
                            return file;
                        if(file.isDirectory() == true);
                        else  {
                            System.out.print("незнайдено");
                            file = file.getParentFile();
                        }
                    break;
                    case 5:
                        System.out.print("Ведіть назву директрії: ");
                        String s = in.nextLine();
                        File dir = new File(file.getPath()+"/" + in.nextLine());
                        System.out.println("Створення директорії: " + dir.mkdirs());
                    break;
                    case 6:
                        System.out.print("Ведіть адресу: ");
                        String s3 = in.nextLine();
                        s3 = in.nextLine();
                        String s2 = new String();
                        for (int i = 0; i < s3.length(); i++)
                        {
                             if (s3.charAt(i)=='\\')
                                 s2 += "/";
                             else
                                 s2 += s3.charAt(i);
                        }
                        file = new File(s2);
                    break;

                    case 7:
                        System.out.print("Ведіть назву файлу:");
                        in.nextLine();
                        return new File(file.getAbsolutePath() + "/" + in.nextLine());
                }
            }
            catch(Exception e)
            {
                System.out.println(" EROR EROR EROR EROR EROR EROR EROR EROR \n");
                System.out.print("\n\n\nТрапилась помилка. Але тепер все добре!!\n\n");
                System.out.println(e);
            }
        }

    }


    public static int dialogIn() throws IOException, InterruptedException
    {
        System.out.print("\n\n Оберіть команду:"
                +"\n 1 - Місце перебування"
                +"\n 2 - Файли в директорії"
                +"\n 3 - Перейти в директорію"
                +"\n 4 - Вийти із директорії"
                +"\n 5 - Відкрити файл для зчитування і створення колекції"
                +"\n 6 - Перейти за адресою:"
                +"\n\n>>>: ");

        return in.nextInt();
    }

    public static File MenuFillIn() ///функціє проводить координування по можливостям програм
    {
        File file = new File("D:/eclips-workspace/kogutenko-oleksandr/src/ua/khpi/oop/");
        while(true)///нескінченний цикл який дозволяє працювати програмі
        {
            try {
                int  key = dialogIn();

                switch(key)///пошук введеної команди
                {
                    case 1:
                        System.out.println("\nШлях: " + file.getPath()); //getAbsolutePath
                        break;
                    case 2:
                        int a = 0;
                        System.out.print("Файли: \n" + file.getPath() + "\n");
                        for (File file2 : file.listFiles())
                        {
                            if ( a % 4 == 0)
                                System.out.print("\n");
                            a++;
                            System.out.printf("%-30s  ",file2.getName());
                        } ///////////
                    break;
                    case 3:
                        System.out.print("Ведіть назву файлу:");
                        file = new File(file.getAbsolutePath() + "/" + in.nextLine());
                        if (file.isDirectory() == false)
                        {
                            System.out.print("Дирикторія незнайдена");
                            file = file.getParentFile();
                        }
                    break;
                    case 4:
                        file = file.getParentFile();
                        break;
                    case 5:
                        System.out.print("Ведіть назву файлу: ");
                        in.nextLine();
                        file = new File(file.getAbsolutePath() + "/" + in.nextLine());
                        if (file.isFile() == true)
                            return file;
                    break;
                    case 6:
                        {
                            System.out.print("Ведіть адресу: ");
                            String s3 = in.nextLine();
                            String s2 = new String();
                            for (int i=0; i < s3.length(); i++)
                            {
                                if (s3.charAt(i)=='\\')
                                    s2+="/";
                                else
                                    s2 += s3.charAt(i);
                            }
                            file = new File(s2);
                        }
                    break;
                }
            }
            catch(Exception e)
            {
                System.out.println(" EROR EROR EROR EROR EROR EROR EROR EROR EROR \n");
                System.out.println(e);}
        }
    }
}

