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
    @JsonIgnore
    private final ArrayList<OrderItem> orderItems;

    private final ArrayList<ReturnItem> returnItems;
    ArrayList<Product> products;

    @JsonCreator
    public Seller(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("email") String email,
                  @JsonProperty("returnItems") ArrayList<ReturnItem> returnItems,
                  @JsonProperty("pseudo") String pseudo,
                  @JsonProperty("number") Long number,
                  @JsonProperty("productsToSell") ArrayList<Product> productsToSell,
                  @JsonProperty("password") String password,
                  @JsonProperty("tickets") ArrayList<Ticket> tickets) {
        super(firstName, lastName, email, pseudo, number, password, tickets);
        products = productsToSell;
        this.orderItems = new ArrayList<OrderItem>();
        this.returnItems = returnItems;
    }

    public Seller(String firstName, String lastName, String email, String pseudo, long number, ArrayList<Product> products, String password) {
        super(firstName, lastName, email, pseudo, number, password, new ArrayList<Ticket>());
        this.returnItems = new ArrayList<>();
        this.orderItems = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        Catalog.catalogMap.put(product.getId(), new Object[]{product, this});
        products.add(product);
    }

    public void updateCatalog() {
        for (Product product : products) Catalog.catalogMap.put(product.getId(), new Object[]{product, this});
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

    public void addReturnItem(ReturnItem returnItem) {
        this.returnItems.add(returnItem);
    }

    public OrderItem getOrderItem(int productId) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProductId() == productId) return orderItem;
        }
        return null;
    }

    public ReturnItem getReturnItem(int productId) {
        for (ReturnItem returnItem : returnItems) {
            if (returnItem.getProductId() == productId) return returnItem;
        }
        return null;
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
            if(!item.isShipped()) inProd.add(item);
        }
        return inProd;
    }

    @JsonIgnore
    public ArrayList<OrderItem> getInShipping(){
        ArrayList<OrderItem> inShipping=new ArrayList<>();
        for(OrderItem item:orderItems){
            if(item.isShipped()&&!item.isDelivered()) inShipping.add(item);
        }
        return inShipping;
    }

    public void addTicket(Ticket ticket) {
        getTickets().add(ticket);
    }


}
