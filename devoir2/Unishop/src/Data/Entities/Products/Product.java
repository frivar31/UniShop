package Data.Entities.Products;

import java.util.Date;

public class Product {

    private String title;
    private String desc;
    private String category;
    private Date date;
    private int initialQuantity;
    private double price;
    private int points = 1;

    public Product(String title,
                   String desc,
                   String category,
                   Date date,
                   int initialQuantity,
                   double price,
                   int points) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.date = date;
        this.initialQuantity = initialQuantity;
        this.price = price;
        this.points = points;
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

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
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
        return "Product{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", initialQuantity=" + initialQuantity +
                ", price=" + price +
                ", points=" + points +
                '}';
    }
}
