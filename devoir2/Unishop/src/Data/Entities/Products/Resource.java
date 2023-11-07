package Data.Entities.Products;

import Data.Entities.Products.Product;

import java.util.Date;

public class Resource extends Product {

    private String ISBN
    public Resource(String title,
                    String desc,
                    String category,
                    Date date,
                    int initialQuantity,
                    double price,
                    int points) {
        super(title, desc, category, date, initialQuantity, price, points);

    }
}
