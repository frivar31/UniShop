package Data.Entities.Users;

import Data.Entities.Products.Product;

import java.util.ArrayList;

public class Seller extends User {

    ArrayList<Product> products;

    public Seller(String firstName, String lastName, String email, String pseudo, Long number, ArrayList<Product> productsToSell) {
        super(firstName, lastName, email, pseudo, number);
        products = productsToSell;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean deleteProduct(Product product) {
        try {
            products.remove(product);
        } catch (Exception e) {
            return false;
        }
        ;
        return true;
    }
    public Product getProduct(int index){
        return products.get(index);
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        int index=0;
        for(Product product:products){
            sb.append("#").append(index).append(" ").append(product.toString());
            sb.append("/n");
        }
        return sb.toString();
    }
}
