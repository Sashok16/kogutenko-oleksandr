package ua.khpi.oop.kogutenko16.src.sample.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Model class for a Shops.
 *
 * @author Marco Jakob
 */
public class Shops {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty unit;
    private IntegerProperty count;
    private IntegerProperty price;
    private ObjectProperty<LocalDate> date;
    private Map<String, String> description = new HashMap<String, String>();

    /**
     * Default constructor.
     */
    public Shops() {
        this(0, null, null, 0, 0, new Date(1,1,1970), null );
    }

    /**
     * Constructor with some initial data.
     * 
     * @param id
     * @param name
     */
    public Shops(Integer id, String name, String unit, Integer count, Integer price, Date date, Map<String, String> description) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.unit = new SimpleStringProperty(unit);
        this.count = new SimpleIntegerProperty(count);
        this.price = new SimpleIntegerProperty(price);
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()));
        setDescription(description);
    }

    public synchronized void setDescription(Map<String, String> description) {
        this.description = description;
    }
    public synchronized void setDescription(String key, String val) {
        this.description.put(key, val);
    }
    public synchronized void setDescription(String description) {
        Pattern p;
        Matcher m;
        String key = null, val = null, strOn = null;
        String[] str = description.split(",");
        String[] strAdd;
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            strAdd = str[i].split(" - ");
            p = Pattern.compile("[\\w]{15}]");
            key = strAdd[0].trim();
            m = p.matcher(key);
            if (m.matches()) {
                key = "key";
            }
            val = strAdd[1].trim();
            m = p.matcher(val);
            if (m.matches()) {
                val = "val";
            }
            this.description.put(key, val);
        }
    }
    public synchronized Map<String, String> getDescription() {
        return description;
    }
    public synchronized String getDescriptionInfo() {
        String str = "";
        if(description != null)
            for (Map.Entry<String, String> entry : description.entrySet())
                str += entry.getKey() + " - " + entry.getValue() + "\n";
        return str;
    }

    public synchronized int getPrice() {
        return price.get();
    }
    public synchronized void setPrice(int price) {
        this.price.set(price);
    }

    public synchronized int getId() {
        return id.get();
    }
    public synchronized StringProperty idProperty() {
        return new SimpleStringProperty(Integer.toString(id.get()));
    }
    public synchronized void setId(int id) {
        this.id.set(id);
    }

    public synchronized String getName() {
        return name.get();
    }
    public synchronized void setName(String name) {
        this.name.set(name);
    }
    public synchronized StringProperty nameProperty() {
        return name;
    }

    public synchronized String getUnit() {
        return unit.get();
    }
    public synchronized void setUnit(String unit) {
        this.unit.set(unit);
    }
    public synchronized StringProperty unitProperty() {
        return unit;
    }

    public synchronized int getCount() {
        return count.get();
    }
    public synchronized void setCount(int count) {
        this.count.set(count);
    }
    public synchronized IntegerProperty countProperty() {
        return count;
    }

    public synchronized LocalDate getDate() {
        return date.get();
    }

    public synchronized void setDate(LocalDate date) {
        this.date.set(date);
    }

    public synchronized ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public synchronized String toString() {
        String info = String.format(
                "id: %-3d | name: %-10s | unit: %-4s | count: %-8d | price: %-8d | date: %s | description: %s\n",
                id.get(),        name,         unit,        count.get(),        price.get(),        date.toString(), getDescriptionInfo()
        );
        return info;
    }
}