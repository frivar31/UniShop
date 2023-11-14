package Data.Entities.Products;

import Data.Entities.Type;

import java.util.Date;

public class LearningResource extends Product {

    private final String author;
    private final String organisation;
    private final String publishDate;
    private final Type type;
    private final Long editionNumber;
    private String ISBN;


    public LearningResource(String title,
                            String desc,
                            String category,
                            String date,
                            long initialQuantity,
                            double price,
                            long points,
                            String ISBN,
                            String author,
                            String organisation,
                            String publishDate,
                            Type type,
                            Long editionNumber) {
        super(title, desc, category, date, initialQuantity, price, points);
        this.ISBN = ISBN;
        this.author = author;
        this.organisation = organisation;
        this.publishDate = publishDate;
        this.type = type;
        this.editionNumber = editionNumber;
    }

    public String getauthor() {
        return author;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Type getType() {
        return type;
    }

    public Long geteditorNum() {
        return editionNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String toString() {
        return "{" +
                "\n- id='" + id + '\'' +
                "\n- titre='" + title + '\'' +
                "\n- description='" + desc + '\'' +
                "\n- categorie='" + category + '\'' +
                "\n- date de mise en vente=" + date +
                "\n- quantite=" + quantity +
                "\n- prix=" + price +
                "\n- points=" + points +
                "\n- ISBN='" + ISBN + '\'' +
                "\n- auteur='" + author + '\'' +
                "\n- organisation ='" + organisation + '\'' +
                //"\n- genre='" + genre + '\'' +
                "\n- date de publication =" + publishDate +
                "\n- numero d'edition =" + editionNumber +
                "\n}";
    }
}
