package Data.Entities.Users;

import Data.Entities.Products.Product;

import java.util.ArrayList;

public class Seller extends User{

    ArrayList<Product> products;

    public Seller(String firstName, String lastName, String email, String pseudo, Long number) {
        super(firstName, lastName, email, pseudo, number);
        products = new ArrayList<Product>();
    }
}
