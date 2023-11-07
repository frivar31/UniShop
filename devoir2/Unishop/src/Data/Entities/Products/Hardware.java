package Data.Entities.Products;

import java.util.Date;

public class Hardware extends Product {

    private String brand ;
    private Date lauchDate ;
    private String model ;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getLauchDate() {
        return lauchDate;
    }

    public void setLauchDate(Date lauchDate) {
        this.lauchDate = lauchDate;
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

    private String subCategory ;
    public Hardware(String title,
                    String desc,
                    String category,
                    Date date,
                    int initialQuantity,
                    double price,
                    int points,
                    String brand,
                    String model,
                    Date launchDate,
                    String subCategory) {
        super(title, desc, category, date, initialQuantity, price, points);
        this.brand = brand ;
        this.lauchDate = launchDate ;
        this.model = model ;
        this.subCategory = subCategory ;
    }
}
