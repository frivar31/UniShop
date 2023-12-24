package Data.Entities.Products;

import Data.Entities.ProductEvaluation;
import Data.Entities.Type;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;


/**
 * Classe représentant une ressource d'apprentissage en vente.
 */
public class LearningResource extends Product {

    private String author;
    private String organisation;
    private String publishDate;
    private Type type;
    private Long editionNumber;
    private String ISBN;

    /**
     * Constructeur de la classe LearningResource.
     *
     * @param title          Le titre de la ressource d'apprentissage.
     * @param desc           La description de la ressource d'apprentissage.
     * @param date           La date de mise en vente de la ressource d'apprentissage.
     * @param initialQuantity La quantité initiale de la ressource d'apprentissage.
     * @param price          Le prix de la ressource d'apprentissage.
     * @param points         Les points associés à la ressource d'apprentissage.
     * @param ISBN           Le numéro ISBN de la ressource d'apprentissage.
     * @param author         L'auteur de la ressource d'apprentissage.
     * @param organisation   L'organisation liée à la ressource d'apprentissage.
     * @param publishDate    La date de publication de la ressource d'apprentissage.
     * @param type           Le type de la ressource d'apprentissage.
     * @param editionNumber  Le numéro d'édition de la ressource d'apprentissage.
     * @param evaluations    Les évaluations de la ressource d'apprentissage.
     * @param likes          La liste des likes pour la ressource d'apprentissage.
     */
    @JsonCreator
    public LearningResource(@JsonProperty("title") String title,
                            @JsonProperty("desc") String desc,
                            @JsonProperty("date") String date,
                            @JsonProperty("initialQuantity") long initialQuantity,
                            @JsonProperty("price") double price,
                            @JsonProperty("points") long points,
                            @JsonProperty("ISBN") String ISBN,
                            @JsonProperty("author") String author,
                            @JsonProperty("organisation") String organisation,
                            @JsonProperty("publishDate") String publishDate,
                            @JsonProperty("type") Type type,
                            @JsonProperty("editionNumber") Long editionNumber,
                            @JsonProperty("evaluations") ArrayList<ProductEvaluation> evaluations,
                            @JsonProperty("likes") ArrayList<String> likes) {
        super(title, desc, date, initialQuantity, price, points,ProductType.LearningResource,"","",evaluations,likes);
        this.ISBN = ISBN;
        this.author = author;
        this.organisation = organisation;
        this.publishDate = publishDate;
        this.type = type;
        this.editionNumber = editionNumber;
    }

    /**
     * Obtient l'auteur de la ressource d'apprentissage.
     *
     * @return L'auteur de la ressource d'apprentissage.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Modifie l'auteur de la ressource d'apprentissage.
     *
     * @param author Le nouvel auteur de la ressource d'apprentissage.
     */
    public void setAuteur(String author) {
        this.author = author;
    }

    /**
     * Obtient l'organisation liée à la ressource d'apprentissage.
     *
     * @return L'organisation liée à la ressource d'apprentissage.
     */
    public String getOrganisation() {
        return organisation;
    }

    /**
     * Modifie l'organisation liée à la ressource d'apprentissage.
     *
     * @param organisation La nouvelle organisation liée à la ressource d'apprentissage.
     */
    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    /**
     * Obtient la date de publication de la ressource d'apprentissage.
     *
     * @return La date de publication de la ressource d'apprentissage.
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * Modifie la date de publication de la ressource d'apprentissage.
     *
     * @param publishDate La nouvelle date de publication de la ressource d'apprentissage.
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Obtient le type de la ressource d'apprentissage.
     *
     * @return Le type de la ressource d'apprentissage.
     */
    public Type getType() {
        return type;
    }

    /**
     * Modifie le type de la ressource d'apprentissage.
     *
     * @param type Le nouveau type de la ressource d'apprentissage.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Obtient le numéro d'édition de la ressource d'apprentissage.
     *
     * @return Le numéro d'édition de la ressource d'apprentissage.
     */
    public Long getEditionNumber() {
        return editionNumber;
    }
    /**
     * Modifie le numéro d'édition de la ressource d'apprentissage.
     *
     * @param editionNumber Le nouveau numéro d'édition de la ressource d'apprentissage.
     */
    public void setEditionNumber(Long editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Obtient le numéro ISBN de la ressource d'apprentissage.
     *
     * @return Le numéro ISBN de la ressource d'apprentissage.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Modifie le numéro ISBN de la ressource d'apprentissage.
     *
     * @param ISBN Le nouveau numéro ISBN de la ressource d'apprentissage.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la ressource d'apprentissage.
     *
     * @return Une chaîne de caractères représentant la ressource d'apprentissage.
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
                "\n- ISBN='" + ISBN + '\'' +
                "\n- auteur='" + author + '\'' +
                "\n- organisation ='" + organisation + '\'' +
                //"\n- genre='" + genre + '\'' +
                "\n- date de publication =" + publishDate +
                "\n- numero d'edition =" + editionNumber +
                "\n- likes = '"+ likes.size()+ '\''+"}";
    }
}
