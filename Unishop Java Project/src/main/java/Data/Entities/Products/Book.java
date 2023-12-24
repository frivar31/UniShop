package Data.Entities.Products;

import Data.Entities.ProductEvaluation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Classe représentant un livre en vente.
 */

public class Book extends Product {

    private String ISBN;

    private String author;

    private String editor;

    private String genre;

    private String pubDate;

    private long editorNum;

    private long volNum;
    /**
     * Constructeur de la classe Book.
     *
     * @param title       Le titre du livre.
     * @param desc        La description du livre.
     * @param date        La date de mise en vente du livre.
     * @param initialQuantity La quantité initiale du livre.
     * @param price       Le prix du livre.
     * @param points      Les points associés au livre.
     * @param ISBN        L'ISBN du livre.
     * @param author      L'auteur du livre.
     * @param editor      La maison d'édition du livre.
     * @param genre       Le genre du livre.
     * @param pubDate     La date de publication du livre.
     * @param editorNum   Le numéro d'édition du livre.
     * @param volNum      Le numéro de volume du livre.
     * @param evaluations Les évaluations du livre.
     * @param likes       La liste des likes pour le livre.
     */

    @JsonCreator
    public Book(@JsonProperty("title") String title,
                @JsonProperty("desc") String desc,
                @JsonProperty("date") String date,
                @JsonProperty("initialQuantity") long initialQuantity,
                @JsonProperty("price") double price,
                @JsonProperty("points") long points,
                @JsonProperty("isbn") String ISBN,
                @JsonProperty("author") String author,
                @JsonProperty("editor") String editor,
                @JsonProperty("genre") String genre,
                @JsonProperty("pubDate") String pubDate,
                @JsonProperty("editorNum") long editorNum,
                @JsonProperty("volNum") long volNum,
                @JsonProperty("evaluations") ArrayList<ProductEvaluation> evaluations,
                @JsonProperty("likes") ArrayList<String> likes
    ) {
        super(title, desc, date, initialQuantity, price, points,ProductType.Book,"","",evaluations,likes);
        this.ISBN = ISBN;
        this.author = author;
        this.editor = editor;
        this.genre = genre;
        this.pubDate = pubDate;
        this.editorNum = editorNum;
        this.volNum = volNum;
    }
    /**
     * Obtient l'ISBN du livre.
     *
     * @return L'ISBN du livre.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Modifie l'ISBN du livre.
     *
     * @param ISBN Le nouvel ISBN du livre.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Obtient l'auteur du livre.
     *
     * @return L'auteur du livre.
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Modifie l'auteur du livre.
     *
     * @param author Le nouvel auteur du livre.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * Obtient l'éditeur du livre.
     *
     * @return L'éditeur du livre.
     */

    public String getEditor() {
        return editor;
    }
    /**
     * Modifie l'éditeur du livre.
     *
     * @param editor Le nouvel éditeur du livre.
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * Obtient le genre du livre.
     *
     * @return Le genre du livre.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Modifie le genre du livre.
     *
     * @param genre Le nouveau genre du livre.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Obtient la date de publication du livre.
     *
     * @return La date de publication du livre.
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Modifie la date de publication du livre.
     *
     * @param pubDate La nouvelle date de publication du livre.
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * Obtient le numéro d'édition du livre.
     *
     * @return Le numéro d'édition du livre.
     */
    public long getEditorNum() {
        return editorNum;
    }

    /**
     * Modifie le numéro d'édition du livre.
     *
     * @param editorNum Le nouveau numéro d'édition du livre.
     */
    public void setEditorNum(long editorNum) {
        this.editorNum = editorNum;
    }

    /**
     * Obtient le numéro de volume du livre.
     *
     * @return Le numéro de volume du livre.
     */
    public long getVolNum() {
        return volNum;
    }

    /**
     * Modifie le numéro de volume du livre.
     *
     * @param volNum Le nouveau numéro de volume du livre.
     */
    public void setVolNum(long volNum) {
        this.volNum = volNum;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du livre.
     *
     * @return Une chaîne de caractères représentant le livre.
     */

    public String toString() {
        return "{" +
                "\n- id='" + id + '\'' +
                "\n- titre='" + title + '\'' +
                "\n- description='" + desc + '\'' +
                "\n- categorie='" + category + '\'' +
                "\n- date de mise en vente=" + date +
                "\n- quantite=" + quantity +
                "\n- prix=" + (double) Math.round(price * 100) / 100 + "$" +
                "\n- points=" + points +
                "\n- ISBN='" + ISBN + '\'' +
                "\n- auteur='" + author + '\'' +
                "\n- maision d'edition='" + editor + '\'' +
                "\n- genre='" + genre + '\'' +
                "\n- date de publication=" + pubDate +
                "\n- numero d'edition=" + editorNum +
                "\n- numero de volume=" + volNum +
                "\n- likes = '"+ likes.size()+ '\''+"}";
    }


}
