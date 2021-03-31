package ua.khpi.oop.teststruct;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;

public class Test extends Thread{
    public static void main(String[] args) throws Exception{
       Pattern p = Pattern.compile("([a-zA-Z]:)?(/[a-zA-Z0-9_-]+)?");
       Matcher matcher = p.matcher("D:/");
        System.out.println(matcher.matches());
    }
}
