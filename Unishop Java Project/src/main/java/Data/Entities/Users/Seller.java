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
    private  final ArrayList<OrderItem> orderItems;
    private ArrayList<Product> products;

    @JsonCreator
    public Seller(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("email") String email,
                  @JsonProperty("pseudo") String pseudo,
                  @JsonProperty("number") Long number,
                  @JsonProperty("productsToSell") ArrayList<Product> productsToSell,
                  @JsonProperty("password") String password,
                  @JsonProperty("tickets") ArrayList<Ticket> tickets) {
        super(firstName, lastName, email, pseudo, number, password, tickets);
        products = productsToSell;
        this.orderItems = new ArrayList<OrderItem>();
    }

    public Seller(String firstName, String lastName, String email, String pseudo, long number, ArrayList<Product> products, String password) {
        super(firstName, lastName, email, pseudo, number, password, new ArrayList<Ticket>());
        this.orderItems = new ArrayList<>();
        this.products=products;
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

    public OrderItem getOrderItem(int productId) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProductId() == productId) return orderItem;
        }
        return null;
    }

    @JsonIgnore
    public ArrayList<OrderItem>  getReturnItems() {
        ArrayList<OrderItem> orderItems = new ArrayList<>() ;
        for(OrderItem orderItem : this.orderItems) {
            if(orderItem.isReturned() && orderItem.isShipped()) {
                orderItems.add(orderItem) ;
            }
        }
        return orderItems ;
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
            if(!item.isReturned() && !item.isShipped()) inProd.add(item);
        }
        return inProd;
    }

    @JsonIgnore
    public ArrayList<OrderItem> getInShipping(){
        ArrayList<OrderItem> inShipping=new ArrayList<>();
        for(OrderItem item:orderItems){
            if(!item.isReturned() && item.isShipped()&&!item.isDelivered()) inShipping.add(item);
        }
        return inShipping;
    }

    public void addTicket(Ticket ticket) {
        getTickets().add(ticket);
    }


}
