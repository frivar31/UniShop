package Data.Entities.Users;

import Data.Entities.*;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;


public class Seller extends User {
    ArrayList<Product> products;

    private  ArrayList<OrderItem> orderItems;
    private ArrayList<ReturnItem> returnItems ;

    @JsonCreator
    public Seller(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("email") String email,
                  @JsonProperty("orderItems") ArrayList<OrderItem> orderItems,
                  @JsonProperty("returnItems") ArrayList<ReturnItem> returnItems,
                  @JsonProperty("pseudo") String pseudo,
                  @JsonProperty("number") Long number,
                  @JsonProperty("productsToSell") ArrayList<Product> productsToSell,
                  @JsonProperty("password") String password) {
        super(firstName, lastName, email, pseudo, number,password);
        products = productsToSell;
        this.orderItems = orderItems ;
        this.returnItems = returnItems ;
    }

    public Seller(String firstName, String lastName, String email, String pseudo, long number, ArrayList<Product> products, String password) {
        super(firstName, lastName, email, pseudo, number, password);
        this.returnItems = new ArrayList<>() ;
        this.orderItems = new ArrayList<>() ;
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

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public ArrayList<ReturnItem> getReturnItems() {
        return returnItems;
    }

    /*public void setReturnItems(ArrayList<ReturnItem> returnItems) {
        this.returnItems = returnItems ;
    }*/

    public void addReturnItem(ReturnItem returnItem) {this.returnItems.add(returnItem) ;}

    public OrderItem getOrderItem(int productId) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProductId() == productId) return orderItem ;
        }
        return null ;
    }

    public ReturnItem getReturnItem(int productId) {
        for (ReturnItem returnItem : returnItems) {
            if (returnItem.getProductId() == productId) return returnItem ;
        }
        return null ;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
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


    @Override
    public String toString() {
        return "{" +
                "\n- firstName='" + this.getFirstName() + '\'' +
                "\n- lastName='" + this.getLastName() + '\'' +
                /*"\n- email='" + this.getEmail() + '\'' +*/
                "\n- pseudo='" + this.getPseudo() + '\'' +
                /*"\n- number=" + this.getNumber() +*/
                "\n}";
    }

    @Override
    public void displayActivityStat() {
    }
    @JsonIgnore
    public ArrayList<OrderItem> getInProduction(){
        ArrayList<OrderItem> inProd=new ArrayList<>();
        for(OrderItem item:orderItems){
            if(!item.getShipped()) inProd.add(item);
        }
        return inProd;
    }
    @JsonIgnore
    public ArrayList<OrderItem> getInShipping(){
        ArrayList<OrderItem> inShipping=new ArrayList<>();
        for(OrderItem item:orderItems){
            if(item.getShipped()&&!item.getDelivered()) inShipping.add(item);
        }
        return inShipping;
    }



}
