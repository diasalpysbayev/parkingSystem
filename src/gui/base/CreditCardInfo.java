package gui.base;

import java.io.Serializable;

public class CreditCardInfo implements Serializable {
    private String number;
    private String month;
    private String year;
    private String name;

    public CreditCardInfo() {
    }

    public CreditCardInfo(String number, String month, String year, String name) {
        this.number = number;
        this.month = month;
        this.year = year;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return number + month + year + name;
    }
}
