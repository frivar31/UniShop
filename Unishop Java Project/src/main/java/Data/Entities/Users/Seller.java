package Data.Entities.Users;

import Data.Entities.Catalog;
import Data.Entities.Products.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Seller extends User {
    ArrayList<Product> products;

    @JsonCreator
    public Seller(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("email") String email,
                  @JsonProperty("pseudo") String pseudo,
                  @JsonProperty("number") Long number,
                  @JsonProperty("productsToSell") ArrayList<Product> productsToSell,
                  @JsonProperty("password") String password) {
        super(firstName, lastName, email, pseudo, number,password);
        products = productsToSell;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        Catalog.catalogMap.put(product.getId(), new Object[]{product, this});
        products.add(product);
    }
    public void updateCatalog(){
        for(Product product:products) Catalog.catalogMap.put(product.getId(), new Object[]{product, this});
    }

    public boolean deleteProduct(Product product) {
        try {
            Catalog.catalogMap.remove(product.getId());
            products.remove(product);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Product getProduct(int index) {
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

    @Override
    public void displayActivityStat() {

    }


}
