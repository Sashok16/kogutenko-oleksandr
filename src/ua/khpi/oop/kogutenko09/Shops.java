package ua.khpi.oop.kogutenko09;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * The type Shops.
 */
public class Shops implements Serializable {
    private int id;
    private String name;
    private String unit;
    private int count;
    private Date date = new Date();
    private Map<String,String> description = new HashMap<String, String>();

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public Map<String, String> getDescription() {
        return description;
    }

    /**
     * Gets description info.
     *
     * @return the description info
     */
    public String getDescriptionInfo() {
        String str = "";
        for(Map.Entry<String, String> entry: description.entrySet())
            str += entry.getKey() + " - " + entry.getValue() + ", ";
        return str;
    }

    /**
     * Sets description.
     *
     * @param key the key
     * @param val the val
     */
    public void setDescription(String key, String val) {
        this.description.put(key,val);
    }

    /**
     * Instantiates a new Shops.
     */
    public Shops(){
        id = 0;
        name = "";
        unit = "";
        count = 0;
        //description = "";
    }

    /**
     * Instantiates a new Shops.
     *
     * @param id    the id
     * @param name  the name
     * @param unit  the unit
     * @param count the count
     * @param date  the date
     * @param keyD  the key d
     * @param valD  the val d
     */
    public Shops(int id, String name, String unit, int count, Date date, String keyD, String valD) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.count = count;
        this.date.setDate(date.getDay(),date.getMonth(),date.getYear());
        setDescription(keyD,valD);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public String toString()
    {
        String info = "id: " + id + " | name: " + name + " | unit: "
                    + unit + " | count: " + count + " | date: " + date.GetDate()
                    + " | description: " + getDescriptionInfo() + "\n";
        return info;
    }

    /**
     * Add.
     */
    public void add()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter info:\n");

        System.out.println("Enter id: ");
        setId(sc.nextInt());

        System.out.println("Enter name: ");
        sc.nextLine();
        setName(sc.nextLine());

        System.out.println("Enter unit: ");
        setUnit(sc.nextLine());

        System.out.println("Enter count: ");
        setCount(sc.nextInt());

        System.out.println("Enter date: \nday - ");
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