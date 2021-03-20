package ua.khpi.oop.kogutenko10;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The type Console file.
 */
public class ConsoleFile {

    /**
     * The constant in.
     */
    public static Scanner in = new Scanner(System.in);

    /**
     * Dialog out int.
     *
     * @return the int
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static int dialogOut() throws IOException, InterruptedException
    {
        System.out.print("\n\nsave collection to...\n"
                + "choice command:"
                + "\n 1 - location"
                + "\n 2 - directory files"
                + "\n 3 - out of directory"
                + "\n 4 - take file to check"
                + "\n 5 - create directory here"
                + "\n 6 - go to another way : "
                + "\n 7 - name of file which contain your collection's data"
                + "\n\n>>>");
        return in.nextInt();
    }

    /**
     * Menu fill out file.
     *
     * @param fileExtension the file extension
     * @return the file
     */
    public static File MenuFillOut(String fileExtension) ///функціє проводить координування по можливостям програм
    {
        File file = new File("D:/eclips-workspace/kogutenko-oleksandr/src/ua/khpi/oop/");
        while(true)///нескінченний цикл який дозволяє працювати програмі
        {
            try {
                int  key = dialogOut();

                switch(key)///пошук введеної команди
                {
                    case 1:
                        System.out.println("\nway: " + file.getPath()); //getAbsolutePath
                        // way: D:/...
                        break;
                    case 2:
                        int a = 0;
                        System.out.print("files: " + file.getPath() + "\n");
                        for (File file2 : file.listFiles())
                        {
                            if ( a % 5 == 0 )
                                System.out.print("\n");
                            a++;
                            System.out.printf("%-25s  ", file2.getName());
                        } ;///////////

                        /*
                        files:
                        file1   file4
                        file2   file5
                        file3   file6
                         */
                        break;
                    case 3:
                        file = file.getParentFile();
                        // cd ..
                        break;
                    case 4:
                        System.out.print("enter name of file: ");
                        file = new File(file.getAbsolutePath() + "/" + in.nextLine());
                        if (file.isFile() == true)
                            return file;
                        if(file.isDirectory() == true);
                        else  {
                            System.out.print("NOT FOUND");
                            file = file.getParentFile();
                        }


                        /*
                            enter name of file: file.txt
                            take file for reading
                         */
                        break;
                    case 5:
                        System.out.print("enter name of directory: ");
                        String s = in.nextLine();
                        File dir = new File(file.getPath()+"/" + in.nextLine());
                        System.out.println("create directory: " + dir.mkdirs());
                        /*
                        enter name of directory:  /somedirect
                        and make new direct here
                         */
                        break;
                    case 6:
                        System.out.print("enter way: ");
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
                        System.out.print("enter name of file without extension (.txt, .bin etc.):");
                        in.nextLine();
                        return new File(file.getAbsolutePath() + "/" + in.nextLine() + fileExtension);
                }
            }
            catch(Exception e)
            {
                System.out.println(" EROR EROR EROR EROR EROR EROR EROR EROR \n");
                //System.out.print("\n\n\nТрапилась помилка. Але тепер все добре!!\n\n");
                System.out.println(e);
            }
        }

    }


    /**
     * Dialog in int.
     *
     * @return the int
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static int dialogIn() throws IOException, InterruptedException
    {
        System.out.print("\n\ncreate collection from..."
                + "choice command:"
                + "\n 1 - location"
                + "\n 2 - directory files"
                + "\n 3 - go to another directory"
                + "\n 4 - out of directory"
                + "\n 5 - choice file to create collection"
                + "\n 6 - go to... :"
                + "\n\n>>>");
        return in.nextInt();
    }

    /**
     * Menu fill in file.
     *
     * @param fileExtension the file extension
     * @return the file
     */
    public static File MenuFillIn(String fileExtension) ///функціє проводить координування по можливостям програм
    {
        File file = new File("D:/eclips-workspace/kogutenko-oleksandr/src/ua/khpi/oop/");
        while(true)///нескінченний цикл який дозволяє працювати програмі
        {
            try {
                int  key = dialogIn();

                switch(key)///пошук введеної команди
                {
                    case 1:
                        System.out.println("\nway: " + file.getPath()); //getAbsolutePath
                        break;
                    case 2:
                        int a = 0;
                        System.out.print("files: \n" + file.getPath() + "\n");
                        for (File file2 : file.listFiles())
                        {
                            if ( a % 4 == 0)
                                System.out.print("\n");
                            a++;
                            System.out.printf("%-30s  ",file2.getName());
                        } ///////////
                        break;
                    case 3:
                        System.out.print("enter name o file:");
                        file = new File(file.getAbsolutePath() + "/" + in.nextLine());
                        if (file.isDirectory() == false)
                        {
                            System.out.print("NOT FOUND");
                            file = file.getParentFile();
                        }
                        break;
                    case 4:
                        file = file.getParentFile();
                        break;
                    case 5:
                        System.out.print("enter name of file without extension:  ");
                        in.nextLine();
                        file = new File(file.getAbsolutePath() + "/" + in.nextLine() + fileExtension);
                        if (file.isFile() == true) {
                            System.out.println("file found");
                            return file;
                        }else System.out.println("file NOT found");
                        break;
                    case 6:
                    {
                        System.out.print("enter way: ");
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
                System.out.println(e);
            }
        }
    }
}

