package Data.Entities.Users;

import Data.Entities.Catalog;
import Data.Entities.Products.Product;

import java.util.ArrayList;


public class Seller extends User {


    ArrayList<Product> products;
    int id;

    public Seller(String firstName, String lastName, String email, String pseudo, Long number, ArrayList<Product> productsToSell) {
        super(firstName, lastName, email, pseudo, number);
        products=new ArrayList<Product>();
        for(Product product:productsToSell) addProduct(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        Catalog.catalogMap.put(product.getId(),new Object[]{product,this});
        products.add(product);
    }

    public boolean deleteProduct(Product product) {
        try {
            Catalog.catalogMap.remove(product.getId());
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
    /*@Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Product product:products){
            sb.append(product.toString()).append("\n");
        }
        return sb.toString();
    }*/

    @Override
    public String toString() {
        return "{" +
                "\n- firstName='" + this.getFirstName() + '\'' +
                "\n- lastName='" + this.getLastName() + '\'' +
                "\n- email='" + this.getEmail() + '\'' +
                "\n- pseudo='" + this.getPseudo() + '\'' +
                "\n- number=" + this.getNumber() +
                "\n}";
    }
}
