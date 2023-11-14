import Data.Entities.Catalog;
import Data.Entities.Products.Product;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Utils.PrintUtil;

import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Product laptop = new Product("Laptop", "Powerful laptop", "Electronics", "2023-11-12", 10, 1500.0, 20);
        Product smartphone = new Product("Smartphone", "High-end smartphone", "Electronics", "2023-11-12", 15, 800.0, 15);

        ArrayList<Product> seller1Products = new ArrayList<>();
        seller1Products.add(laptop);
        ArrayList<Product> seller2Products = new ArrayList<>();
        seller2Products.add(smartphone);

        Seller seller1 = new Seller("John", "DSDS", "afdssfasf", "fsaf344", 123456789L, seller1Products);
        Seller seller2 = new Seller("Jane", "GFFG", "asfsafsafs", "dsad344", 987654321L, seller2Products);


        List<Product> products = new ArrayList<>();
        products.add(laptop);
        products.add(smartphone);


        List<Seller> sellers1 = new ArrayList<>();
        sellers1.add(seller1);
        sellers1.add(seller2);

        Scanner scanner = new Scanner(System.in);
        App.getClientServiceInfo(scanner, products, sellers1);
    }
}




