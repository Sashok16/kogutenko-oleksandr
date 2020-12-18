package ua.khpi.oop.kogutenko07;


import java.io.Serializable;
import java.util.Scanner;

public class Shop implements Serializable {
    private int id;
    private String name;
    private String unit;
    private int count;
    private Date date = new Date();
    private String description;

    public Shop()
    {
        id = 0;
        name = "";
        unit = "";
        count = 0;
        description = "";
    }

    public Shop(int id, String name, String unit, int count, Date date, String description) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.count = count;
        this.date.setDate(date.getDay(),date.getMonth(),date.getYear());
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString()
    {
        String info = "id: " + id + " | name: " + name + " | unit: " + unit + " | count: " + count + " | date: " + date.GetDate() + " | description: " + description + "\n";
        return info;
    }

    public void add()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter info:\n");
        System.out.println("Enter id: ");
        this.id = sc.nextInt();
        System.out.println("Enter name: ");
        sc.nextLine();
        this.name = sc.nextLine();
        System.out.println("Enter unit: ");
        this.name = sc.nextLine();
        System.out.println("Enter count: ");
        this.count = sc.nextInt();
        System.out.println("Enter date: \n day - ");
        int day = sc.nextInt();
        System.out.println("\nmon - ");
        int mon = sc.nextInt();
        System.out.println("\nyear - ");
        int year = sc.nextInt();
        date.setDate(day, mon, year);
        System.out.println("\nEnter some description: ");
        sc.nextLine();
        this.description = sc.nextLine();
    }

}
