package Data.Entities.Products;

import java.util.Date;

public class Article extends Product {

    private String brand ;
    private String model ;
    private String subCategory ;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Article(String title,
                   String desc,
                   String category,
                   String date,
                   long initialQuantity,
                   double price,
                   long points,
                   String brand,
                   String model,
                   String subCategory) {
        super(title, desc, category, date, initialQuantity, price, points);
        this.brand = brand ;
        this.model = model ;
        this.subCategory = subCategory ;
    }
}
