package Data.Entities.Products;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DesktopTool extends Product {
    private String brand;
    private String model;
    private String subCategory;

    @JsonCreator
    public DesktopTool(@JsonProperty("title") String title,
                       @JsonProperty("desc") String desc,
                       @JsonProperty("category") ProductType category,
                       @JsonProperty("date") String date,
                       @JsonProperty("initialQuantity") long initialQuantity,
                       @JsonProperty("price") double price,
                       @JsonProperty("points") long points,
                       @JsonProperty("brand") String brand,
                       @JsonProperty("model") String model,
                       @JsonProperty("subCategory") String subCategory) {
        super(title, desc, date, initialQuantity, price, points);
        this.brand = brand;
        this.model = model;
        this.subCategory = subCategory;
        this.category = category ;
    }

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

    public String toString() {
        return "{" +
                "\n- id='" + id + '\'' +
                "\n- titre='" + title + '\'' +
                "\n- description='" + desc + '\'' +
                "\n- categorie='" + category + '\'' +
                "\n- date de mise en vente='" + date + '\'' +
                "\n- quantite=" + quantity +
                "\n- price=" + (double) Math.round(price * 100) / 100 + "$" +
                "\n- points=" + points +
                "\n- marque='" + brand + '\'' +
                "\n- modele='" + model + '\'' +
                "\n- sous-categorie='" + subCategory + '\'' +
                "\n}";
    }

}
