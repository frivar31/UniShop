package Data.Entities.Products;

import java.util.Date;

public class LearningResource extends Product {

    private String ISBN;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LearningResource(String title,
                            String desc,
                            String category,
                            Date date,
                            int initialQuantity,
                            double price,
                            int points) {
        super(title, desc, category, date, initialQuantity, price, points);

    }
}
