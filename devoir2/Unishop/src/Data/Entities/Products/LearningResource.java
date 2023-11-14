package Data.Entities.Products;

import Data.Entities.Type;

public class LearningResource extends Product {

    private String auteur;
    private String organisation;
    private String publishDate;
    private Type type;
    private Long editionNumber;
    private String ISBN;


    public LearningResource(String title,
                            String desc,
                            String category,
                            String date,
                            long initialQuantity,
                            double price,
                            long points,
                            String ISBN,
                            String auteur,
                            String organisation,
                            String publishDate,
                            Type type,
                            Long editionNumber) {
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

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Long editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
