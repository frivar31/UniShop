package Data.Entities.Products;

import java.util.Date;

public class DesktopTool extends Product {
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

    private String brand ;
    private String model ;
    private String subCategory ;
    public DesktopTool(String title,
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
    public String toString() {
        return "{" +
                "\n- id='" + id + '\'' +
                "\n- titre='" + title + '\'' +
                "\n- description='" + desc + '\'' +
                "\n- categorie='" + category + '\'' +
                "\n- date de mise en vente='" + date + '\'' +
                "\n- quantite=" + quantity +
                "\n- prix=" + price +
                "\n- points=" + points +
                "\n- marque='" + brand + '\'' +
                "\n- modele='" + model + '\'' +
                "\n- sous-categorie='" + subCategory + '\'' +
                "\n}";
    }

}
