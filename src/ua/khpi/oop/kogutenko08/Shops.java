package ua.khpi.oop.kogutenko08;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Shops implements Serializable {
    private int id;
    private String name;
    private String unit;
    private int count;
    private Date date = new Date();
    private Map<String,String> description = new HashMap<String, String>();

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public String getDescriptionInfo() {
        String str = "";
        for(Map.Entry<String, String> entry: description.entrySet())
            str += entry.getKey() + " - " + entry.getValue() + ", ";
        return str;
    }

    public void setDescription(String key, String val) {
        this.description.put(key,val);
    }

    public Shops()
    {
        id = 0;
        name = "";
        unit = "";
        count = 0;
        //description = "";
    }

    public Shops(int id, String name, String unit, int count, Date date, String keyD, String valD) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.count = count;
        this.date.setDate(date.getDay(),date.getMonth(),date.getYear());
        setDescription(keyD,valD);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString()
    {
        String info = "id: " + id + " | name: " + name + " | unit: " + unit + " | count: " + count + " | date: " + date.GetDate() + " | description: " + getDescriptionInfo() + "\n";
        return info;
    }

    public void add()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter info:\n");
        System.out.println("Enter id: ");
        setId(sc.nextInt());
        //this.id = sc.nextInt();
        System.out.println("Enter name: ");
        sc.nextLine();
        setName(sc.nextLine());
        //this.name = sc.nextLine();
        System.out.println("Enter unit: ");
        //sc.nextLine();
        setUnit(sc.nextLine());
        //this.unit = sc.nextLine();
        System.out.println("Enter count: ");
        setCount(sc.nextInt());
        //this.count = sc.nextInt();

        System.out.println("Enter date: \n day - ");
        int day = sc.nextInt();
        System.out.println("\nmon - ");
        int mon = sc.nextInt();
        System.out.println("\nyear - ");
        int year = sc.nextInt();
        date.setDate(day, mon, year);

        System.out.println("\nEnter some description: ");
        boolean check = true;
        while (check)
        {
            System.out.println("\nEnter key: ");
            sc.nextLine();
            String key = sc.nextLine();
            System.out.println("\nEnter val: ");
            //sc.nextLine();
            String val = sc.nextLine();
            this.description.put(key,val);
            System.out.println("Do you want to add mor one description? (0 - no, 1 - yes)\n> ");
            int answer = sc.nextInt();
            if (answer == 0)
            {
                check = false;
            }
        }
    }
}
