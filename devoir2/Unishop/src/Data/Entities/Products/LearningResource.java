package Data.Entities.Products;

import Data.Entities.Type;

import java.util.Date;

public class LearningResource extends Product {

    private final String auteur;
    private final String organisation;
    private final Date publishDate;
    private final Type type;
    private final String editionNumber;
    private String ISBN;


    public LearningResource(String title,
                            String desc,
                            String category,
                            Date date,
                            int initialQuantity,
                            double price,
                            int points,
                            String ISBN,
                            String auteur,
                            String organisation,
                            Date publishDate,
                            Type type,
                            String editionNumber) {
        super(title, desc, category, date, initialQuantity, price, points);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.organisation = organisation;
        this.publishDate = publishDate;
        this.type = type;
        this.editionNumber = editionNumber;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getOrganisation() {
        return organisation;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Type getType() {
        return type;
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
