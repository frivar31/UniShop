package Data.Entities.Products;
import Data.Entities.ProductEvaluation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class DesktopTool extends Product {
    private String subCategory;
    /**
     * Constructeur de la classe DesktopTool.
     *
     * @param title            Le titre de l'outil de bureau.
     * @param desc             La description de l'outil de bureau.
     * @param date             La date de mise en vente de l'outil de bureau.
     * @param initialQuantity  La quantité initiale de l'outil de bureau.
     * @param price            Le prix de l'outil de bureau.
     * @param points           Les points associés à l'outil de bureau.
     * @param brand            La marque de l'outil de bureau.
     * @param model            Le modèle de l'outil de bureau.
     * @param subCategory      La sous-catégorie de l'outil de bureau.
     * @param evaluations      Les évaluations de l'outil de bureau.
     * @param likes            La liste des likes pour l'outil de bureau.
     */

    @JsonCreator
    public DesktopTool(@JsonProperty("title") String title,
                       @JsonProperty("desc") String desc,
                       @JsonProperty("date") String date,
                       @JsonProperty("initialQuantity") long initialQuantity,
                       @JsonProperty("price") double price,
                       @JsonProperty("points") long points,
                       @JsonProperty("brand") String brand,
                       @JsonProperty("model") String model,
                       @JsonProperty("subCategory") String subCategory,
                       @JsonProperty("evaluations") ArrayList<ProductEvaluation> evaluations,
                       @JsonProperty("likes") ArrayList<String> likes) {
        super(title, desc, date, initialQuantity, price, points, ProductType.DesktopTool,model,brand,evaluations,likes);
        this.subCategory = subCategory;
    }
    /**
     * Obtient la sous-catégorie de l'outil de bureau.
     *
     * @return La sous-catégorie de l'outil de bureau.
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Modifie la sous-catégorie de l'outil de bureau.
     *
     * @param subCategory La nouvelle sous-catégorie de l'outil de bureau.
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'outil de bureau.
     *
     * @return Une chaîne de caractères représentant l'outil de bureau.
     */
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
                "\n- likes = '"+ likes.size()+ '\''+"}";
    }

}
