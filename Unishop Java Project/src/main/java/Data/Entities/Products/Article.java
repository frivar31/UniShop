package Data.Entities.Products;
import Data.Entities.ProductEvaluation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Classe représentant un article en vente.
 */

public class Article extends Product {
    private String subCategory;

    /**
     * Constructeur de la classe Article.
     *
     * @param title       Le titre de l'article.
     * @param desc        La description de l'article.
     * @param date        La date de mise en vente de l'article.
     * @param initialQuantity La quantité initiale de l'article.
     * @param price       Le prix de l'article.
     * @param points      Les points associés à l'article.
     * @param brand       La marque de l'article.
     * @param model       Le modèle de l'article.
     * @param subCategory La sous-catégorie de l'article.
     * @param evaluations Les évaluations de l'article.
     * @param likes       La liste des likes pour l'article.
     */
    @JsonCreator
    public Article(@JsonProperty("title") String title,
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
        super(title, desc, date, initialQuantity, price, points,ProductType.Article,model,brand,evaluations,likes);
        this.subCategory = subCategory;

    }
    /**
     * Obtient la sous-catégorie de l'article.
     *
     * @return La sous-catégorie de l'article.
     */
    public String getSubCategory() {
        return subCategory;
    }
    /**
     * Modifie la sous-catégorie de l'article.
     *
     * @param subCategory La nouvelle sous-catégorie de l'article.
     */

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'article.
     *
     * @return Une chaîne de caractères représentant l'article.
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
                "\n- likes = '"+ likes.size()+ '\''+"}";
    }
}
