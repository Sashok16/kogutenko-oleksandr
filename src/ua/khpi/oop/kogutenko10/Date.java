package ua.khpi.oop.kogutenko10;
import java.io.Serializable;

/**
 * The type Date.
 */
public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    /**
     * Instantiates a new Date.
     */
    Date()
    {
        this.day = 1;
        this.year = 1970;
        this.month=1;
    }

    /**
     * Instantiates a new Date.
     *
     * @param day  the day
     * @param mon  the mon
     * @param year the year
     */
    Date(int day, int mon, int year)
    {
        this.day = day;
        this.year = year;
        this.month = mon;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets day.
     *
     * @param day the day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate()
    {
        return day + "." + month + "." + year;
    }

    /**
     * Get date string.
     *
     * @return the string
     */
    public String GetDate() {
        String ss = "";
        Integer day = getDay(), mon = getMonth(), year = getYear();
        String dayS = day.toString(), monS = mon.toString(), yearS = year.toString();
        if (day < 10 && mon >= 10) {
            ss = ss + "0" + dayS + "." + monS + "." + yearS;
        }
        else if (mon < 10 && day >= 10) {
            ss = ss + dayS + "." + "0" + monS + "." + yearS;
        }
        else if (mon < 10 && day < 10) {
            ss = ss + "0" + dayS + "." + "0" + monS + "." + yearS;
        }
        else {
            ss = ss + dayS + "." + monS + "." + yearS;
        }
        return ss;
    }

    /**
     * Sets date.
     *
     * @param day  the day
     * @param mon  the mon
     * @param year the year
     */
    public void setDate(int day, int mon, int year)
    {
        if(day > 0 && day <= 31)
            setDay(day);
        else
            setDay(1);
        if(mon > 0 && mon <= 12)
            setMonth(mon);
        else
            setMonth(1);
        if(year > 1970 && year < 2022)
            setYear(year);
        else
            setYear(2021);
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date)
    {
       String[] dateBuff = date.split("\\.");
       setDate(
               Integer.parseInt(dateBuff[0]),
               Integer.parseInt(dateBuff[1]),
               Integer.parseInt(dateBuff[2])
       );
    }

}
