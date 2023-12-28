package Data.Entities.Products;

import Data.Entities.ProductEvaluation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
/**
 * Représente du matériel qui étend la classe de base des produits.
 */
public class Hardware extends Product {
    private String launchDate;
    private String subCategory;
    /**
     * Constructeur de la classe Hardware.
     *
     * @param title            Le titre du matériel.
     * @param desc             La description du matériel.
     * @param date             La date de mise en vente du matériel.
     * @param initialQuantity  La quantité initiale du matériel.
     * @param price            Le prix du matériel.
     * @param points           Les points associés au matériel.
     * @param brand            La marque du matériel.
     * @param model            Le modèle du matériel.
     * @param launchDate       La date de lancement du matériel.
     * @param subCategory      La sous-catégorie du matériel.
     * @param evaluations      Les évaluations du matériel.
     * @param likes            La liste des likes pour le matériel.
     */
    @JsonCreator
    public Hardware(@JsonProperty("title") String title,
                    @JsonProperty("desc") String desc,
                    @JsonProperty("date") String date,
                    @JsonProperty("initialQuantity") long initialQuantity,
                    @JsonProperty("price") double price,
                    @JsonProperty("points") long points,
                    @JsonProperty("brand") String brand,
                    @JsonProperty("model") String model,
                    @JsonProperty("launchDate") String launchDate, // Corrected parameter name
                    @JsonProperty("subCategory") String subCategory,
                    @JsonProperty("evaluations") ArrayList<ProductEvaluation> evaluations,
                    @JsonProperty("likes") ArrayList<String> likes) {
        super(title, desc, date, initialQuantity, price, points,ProductType.Hardware,model,brand,evaluations,likes);
        this.launchDate = launchDate;
        this.subCategory = subCategory;
    }

    /**
     * Obtient la date de lancement du matériel.
     *
     * @return La date de lancement du matériel.
     */
    public String getLaunchDate() {
        return launchDate;
    }

    /**
     * Modifie la date de lancement du matériel.
     *
     * @param launchDate La nouvelle date de lancement du matériel.
     */
    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    /**
     * Obtient la sous-catégorie du matériel.
     *
     * @return La sous-catégorie du matériel.
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Modifie la sous-catégorie du matériel.
     *
     * @param subCategory La nouvelle sous-catégorie du matériel.
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du matériel.
     *
     * @return Une chaîne de caractères représentant le matériel.
     */
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
                "\n- likes = '"+ likes.size()+ '\''+"}";
    }
}
