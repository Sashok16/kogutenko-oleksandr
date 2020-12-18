package ua.khpi.oop.kogutenko08;

import java.io.Serializable;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    Date()
    {
        this.day = 1;
        this.year = 1970;
        this.month=1;
    }



    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDate()
    {
        return day + "/" + month + "/" + year;
    }

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
        if(year > 1970 && year < 2021)
            setYear(year);
        else
            setYear(2020);
    }

}
