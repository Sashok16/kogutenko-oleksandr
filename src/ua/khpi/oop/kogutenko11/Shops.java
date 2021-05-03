package ua.khpi.oop.kogutenko11;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Shops.
 */
public class Shops implements Serializable {
    private int id;
    private String name;
    private String unit;
    private int count;
    private int price;
    private Date date = new Date();
    private Map<String,String> description = new HashMap<String, String>();

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

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
            str += entry.getKey() + " - " + entry.getValue() + ",";
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
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        Pattern p;
        Matcher m;
        String key = null, val = null, strOn = null;
        String[] str = description.split(",");
        String[] strAdd;
        int count = 0;
        for(int i = 0; i < str.length; i++) {
            strAdd = str[i].split(" - ");
            p = Pattern.compile("[\\w]{15}]");
            key = strAdd[0];
            m = p.matcher(key);
            if(m.matches()){
                key = "key";
            }
            val = strAdd[1];
            m = p.matcher(val);
            if(m.matches()){
                val = "val";
            }
            this.description.put(key,val);
        }
    }

    /**
     * Instantiates a new Shops.
     */
    public Shops(){
        id = 0;
        name = "";
        unit = "";
        count = 0;
        price = 0;
        //description = "";
    }

    /**
     * Instantiates a new Shops.
     *
     * @param str the str
     */
    public Shops(String str) {

    }

    /**
     * Instantiates a new Shops.
     *
     * @param id          the id
     * @param name        the name
     * @param unit        the unit
     * @param count       the count
     * @param price       the price
     * @param date        the date
     * @param description the description
     */
    public Shops(int id, String name, String unit, int count, int price, Date date, Map<String, String> description) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.count = count;
        this.price = price;
        this.date.setDate(date.getDay(),date.getMonth(),date.getYear());
        setDescription(description);
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

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date.setDate(date);
    }

    public String toString() {
        String info = "id: "          + id +
                   " | name: "        + name +
                   " | unit: "        + unit +
                   " | count: "       + count +
                   " | price: "       + price +
                   " | date: "        + date.GetDate() +
                   " | description: " + getDescriptionInfo() +
                   "\n";
        return info;
    }

    /**
     * Add.
     */
    public void add(){
        //regex!!!!!!
        Pattern p;
        Matcher m;
        boolean regexLoop = true;
        String id = null, name = null, unit = null, count = null, price = null, day = null, mon = null, year = null, key = null, val = null, date = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter info:\n");
        while(regexLoop) {
            System.out.print("Enter id: ");
            p = Pattern.compile("[0-9]+");
            id = sc.nextLine();
            m = p.matcher(id);
            if(m.matches()){
                setId(Integer.parseInt(id));
                regexLoop = false;
            } else {
                System.out.println("Enter info correctly!!! (only numbers)");
            }
        }

        regexLoop = true;
        while(regexLoop){
            System.out.print("Enter name: ");
            p = Pattern.compile("^[\\w]{3,15}$");
            name = sc.nextLine();
            m = p.matcher(name);
            if(m.matches()){
                setName(name);
                regexLoop = false;
            } else {
                System.out.println("Enter info correctly!!! (letters and '-' or '_')");
            }
        }

        regexLoop = true;
        while(regexLoop){
            System.out.print("Enter unit: ");
            p = Pattern.compile("kg|l|kg/l");
            unit = sc.nextLine();
            m = p.matcher(unit);
            if(m.matches()){
                setUnit(unit);
                regexLoop = false;
            } else {
                System.out.println("Enter info correctly!!! ('kg' or 'l' or 'kg/l')");
            }
        }
        regexLoop = true;
        while(regexLoop){
            System.out.print("Enter count: ");
            p = Pattern.compile("[0-9]+");
            count = sc.nextLine();
            m = p.matcher(count);
            if(m.matches()){
                setCount(Integer.parseInt(count));
                regexLoop = false;
            } else {
                System.out.println("Enter info correctly!!! (only numbers)");
            }
        }
        regexLoop = true;
        while(regexLoop){
            System.out.print("Enter price: ");
            p = Pattern.compile("[0-9]+");
            price = sc.nextLine();
            m = p.matcher(price);
            if(m.matches()){
                setPrice(Integer.parseInt(price));
                regexLoop = false;
            } else {
                System.out.println("Enter info correctly!!! (only numbers)");
            }
        }

        regexLoop = true;
        while(regexLoop){
            System.out.print("Enter date('dd-mm-yyyy' or 'dd/mm/yyyy' or 'dd.m.yyyy'): ");
            p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
            date = sc.nextLine();
            m = p.matcher(date);
            if(m.matches()){
                day = date.substring(0,2);
                mon = date.substring(3,5);
                year = date.substring(6,10);
                regexLoop = false;
            } else {
                System.out.println("Enter info correctly!!!");
            }
        }
        this.date.setDate(Integer.parseInt(day), Integer.parseInt(mon), Integer.parseInt(year));


        System.out.println("\nEnter some description: ");
        boolean check = true;
        while (check) {
            regexLoop = true;
            while(regexLoop){
                System.out.print("Enter key: ");
                p = Pattern.compile("[\\w]{3,15}");
                key = sc.nextLine();
                m = p.matcher(key);
                if(m.matches()){
                    while(regexLoop){
                        System.out.print("Enter val: ");
                        p = Pattern.compile("[\\w]{3,15}");
                        val = sc.nextLine();
                        m = p.matcher(val);
                        if(m.matches()){
                            this.description.put(key,val);
                            regexLoop = false;
                        } else{
                            System.out.println("Enter info correctly!!!\nOnly numbers, letters and '_'");
                        }
                    }
                }else{
                    System.out.println("Enter info correctly!!!\nOnly numbers, letters and '_' or '-'");
                }
            }
            System.out.print("Do you want to add mor one description? (0 - no, 1 - yes)\n>>> ");
            int answer = sc.nextInt();
            if (answer == 0)
            {
                check = false;
            }
        }
    }


}