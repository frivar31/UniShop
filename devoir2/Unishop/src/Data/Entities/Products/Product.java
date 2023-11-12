package Data.Entities.Products;

import java.util.Date;

public class Product {
    protected static int counter = 0;
    protected int id;
    protected String title;
    protected String desc;
    protected String category;
    protected String date;
    protected long quantity;
    protected double price;
    protected long points = 1;

    public Product(String title,
                   String desc,
                   String category,
                   String date,
                   long quantity,
                   double price,
                   long points) {
        this.id=counter++;
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.points = points;
    }
    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "{" +
                "\n- id='" + id + '\'' +
                "\n- title='" + title + '\'' +
                "\n- desc='" + desc + '\'' +
                "\n- category='" + category + '\'' +
                "\n- date=" + date +
                "\n- quantity=" + quantity +
                "\n- price=" + price +
                "\n- points=" + points +
                "\n}";
    }
}
