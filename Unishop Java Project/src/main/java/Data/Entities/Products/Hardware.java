package Data.Entities.Products;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Hardware extends Product {

    private String brand;
    private String launchDate;
    private String model;
    private String subCategory;

    @JsonCreator
    public Hardware(@JsonProperty("title") String title,
                    @JsonProperty("desc") String desc,
                    @JsonProperty("category") String category,
                    @JsonProperty("date") String date,
                    @JsonProperty("initialQuantity") long initialQuantity,
                    @JsonProperty("price") double price,
                    @JsonProperty("points") long points,
                    @JsonProperty("brand") String brand,
                    @JsonProperty("model") String model,
                    @JsonProperty("launchDate") String launchDate, // Corrected parameter name
                    @JsonProperty("subCategory") String subCategory) {
        super(title, desc, category, date, initialQuantity, price, points);
        this.brand = brand;
        this.launchDate = launchDate;
        this.model = model;
        this.subCategory = subCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
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
                "\n- date de mise en vente=" + date +
                "\n- quantite=" + quantity +
                "\n- price=" + (double) Math.round(price * 100) / 100 + "$" +
                "\n- points=" + points +
                "\n- sous-categorie='" + subCategory + '\'' +
                "\n- marque='" + brand + '\'' +
                "\n- modele ='" + model + '\'' +
                "\n- date de lancement ='" + launchDate + '\'' +
                "\n}";
    }
}
