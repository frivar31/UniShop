package Data.Entities.Products;

import java.util.Date;

public class Hardware extends Product {

    private String brand ;
    private String lauchDate ;
    private String model ;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLauchDate() {
        return lauchDate;
    }

    public void setLauchDate(String lauchDate) {
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
                    String date,
                    long initialQuantity,
                    double price,
                    long points,
                    String brand,
                    String model,
                    String launchDate,
                    String subCategory) {
        super(title, desc, category, date, initialQuantity, price,points);
        this.brand = brand ;
        this.lauchDate = launchDate ;
        this.model = model ;
        this.subCategory = subCategory ;
    }
}
