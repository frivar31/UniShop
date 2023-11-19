package Data.Entities.Products;

import Data.Entities.ProductEvaluation;
import Data.Entities.Users.Client;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    protected long discountPoint=0;
    protected long discountPrice=0;
    protected ArrayList<ProductEvaluation> evaluations;

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
        this.evaluations=new ArrayList<>();
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

    public void setquantity(long quantity) {
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

    public void setPoints(long points) {
        this.points = points;
    }

    public void addEvaluation(ProductEvaluation eval){
        evaluations.add(eval);
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
                "\n- price=" + (double)Math.round(price*100)/100+"$" +
                "\n- points=" + points +
                "\n}";
    }
    public String toString(int quantity){
        return "{" +
                "\n- id='" + id + '\'' +
                "\n- title='" + title + '\'' +
                "\n- desc='" + desc + '\'' +
                "\n- category='" + category + '\'' +
                "\n- date=" + date +
                "\n- quantity=" + quantity +
                "\n- price=" + (double)Math.round(price*100)/100+"$" +
                "\n- points=" + points +
                "\n}";
    }
    public void setPromotion(long points, double price){
    // TODO:
    }
}
