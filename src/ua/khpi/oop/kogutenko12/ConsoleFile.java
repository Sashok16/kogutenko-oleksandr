package ua.khpi.oop.kogutenko12;
import java.io.File;
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
     */
    public static int dialogOut()
    {
        System.out.print("---------------------------------------------------------"
                       + "\n\nsave collection to...\n"
                       + "choice command:"
                       + "\n 1 - location"
                       + "\n 2 - directory files"
                       + "\n 3 - out of directory"
                       + "\n 4 - take file to check"
                       + "\n 5 - create directory here"
                       + "\n 6 - go to another way : "
                       + "\n 7 - name of file which contain your collection's data"
                       + "\n\n>>> ");
        //regex
        String out = in.nextLine();
        //
        return Integer.parseInt(out);
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
                        }

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
                        //regex
                        String fileName_4 = in.nextLine();
                        //
                        file = new File(file.getAbsolutePath() + "/" + fileName_4);
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
                        //regex..
                        String direct = in.nextLine();
                        //
                        File dir = new File(file.getPath()+"/" + direct);
                        System.out.println("create directory: " + dir.mkdirs());
                        /*
                        enter name of directory:  /somedirect
                        and make new direct here
                         */
                        break;
                    case 6:
                        System.out.print("enter way: ");
                        //regex
                        String s3 = in.nextLine();
                        //
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
                        System.out.print("enter name of file without extension (.txt, .bin etc.): ");
                        in.nextLine();

                        //regex
                        String fileName_7 = in.nextLine();
                        //
                        return new File(file.getAbsolutePath() + "/" + fileName_7 + fileExtension);
                }
            }
            catch(Exception e)
            {
                System.out.println("EROR EROR EROR EROR EROR EROR EROR ERROR\n");
                System.out.println(e);
                break;
            }
        }
        return null;
    }


    /**
     * Dialog in int.
     *
     * @return the int
     */
    public static int dialogIn()
    {
        System.out.print("---------------------------------------------------------"
                       + "\n\ncreate collection from..."
                       + "choice command:"
                       + "\n 1 - location"
                       + "\n 2 - directory files"
                       + "\n 3 - go to another directory"
                       + "\n 4 - out of directory"
                       + "\n 5 - choice file to create collection"
                       + "\n 6 - go to... :"
                       + "\n\n>>> ");
        //regex
        String in_s = in.nextLine();
        //
        return Integer.parseInt(in_s);
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
                            System.out.printf("%-30s  ", file2.getName());
                        } ///////////
                        break;
                    case 3:
                        System.out.print("enter name o file: ");
                        //regex
                        String fileNameIn_3 = in.nextLine();
                        //
                        file = new File(file.getAbsolutePath() + "/" + fileNameIn_3);
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
                        System.out.print("enter name of file without extension: ");
                        in.nextLine();
                        //regex
                        String fileNameIn_5 = in.nextLine();
                        //
                        file = new File(file.getAbsolutePath() + "/" + fileNameIn_5 + fileExtension);
                        if (file.isFile() == true) {
                            System.out.println("file found");
                            return file;
                        }else System.out.println("file NOT found");
                        break;
                    case 6:
                    {
                        System.out.print("enter way: ");
                        //regex
                        String s3 = in.nextLine();
                        //
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
                break;
            }
        }
        return null;
    }
}

