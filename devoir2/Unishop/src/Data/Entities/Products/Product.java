package Data.Entities.Products;

import Data.Entities.Catalog;

import java.util.Date;

public class Product {
    private static int counter = 0;
    private int id;
    private String title;
    private String desc;
    private String category;
    private Date date;
    private int quantity;
    private double price;
    private int points = 1;

    public Product(String title,
                   String desc,
                   String category,
                   Date date,
                   int quantity,
                   double price,
                   int points) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getquantity() {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", quantity=" + quantity +
                ", price=" + price +
                ", points=" + points +
                '}';
    }
}
