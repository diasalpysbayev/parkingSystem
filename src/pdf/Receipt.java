package pdf;

import java.util.Date;

public class Receipt {
    private String purpose;
    private Date date = new Date();
    private String name;
    private int price;

    public Receipt(String purpose, int price, String name) {
        this.purpose = purpose;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPurpose() { return purpose;}

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
