package Data.Entities.Users;

import Data.Entities.*;
import Data.Entities.Products.Product;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Client extends User{

    private String shipAddress;
    private HashMap<String,Order> orders;
    private HashMap<Product, ProductEvaluation> evaluations;
    private ShoppingCart shoppingCart;
    private int points;

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public Client(String firstName,
                  String lastName,
                  String email,
                  String pseudo,
                  Long number,
                  String shipAddress) {
        super(firstName,lastName,email,pseudo,number);
        this.shipAddress = shipAddress;
        this.shoppingCart=new ShoppingCart();
        this.points=0;
        orders=new HashMap<>();
    }

    public HashMap<String,Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.put(order.getOrderNumber(),order);
    }

    public String getShipAddress() {
        return this.shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public ShoppingCart getShoppingCart(){
        return this.shoppingCart;
    }
    public Order buy(String address) {
        // Generate a unique order ID
        String orderID = UUID.randomUUID().toString();
        // Create an order using the current cart and customer information
        Order newOrder = new Order(orderID, shoppingCart.convertToOrderItems(), Calendar.getInstance().getTime(), false, false, null, null, address);
        orders.put(newOrder.getOrderNumber(),newOrder);
        points+= (int) shoppingCart.getNumberPoints();

        // Update the inventory
        Catalog.update(shoppingCart.getCart());

        // Clear the cart after the purchase
        this.shoppingCart=new ShoppingCart();
        return newOrder;
    }

    @Override
    public String toString() {
        return "{" +
                "\n- firstName='" + this.getFirstName() + '\'' +
                "\n- lastName='" + this.getLastName() + '\'' +
                "\n- email='" + this.getEmail() + '\'' +
                "\n- pseudo='" + this.getPseudo() + '\'' +
                "\n- number=" + this.getNumber() +
                "\n- shipAddress='" + this.getShipAddress() + '\'' +
                "\n}";
    }

    public void rateProduct(Product product,ProductEvaluation evaluation){
        if(!evaluations.containsKey(product)){
            evaluations.put(product,evaluation);
            product.addEvaluation(evaluation);
        }
    }
    public void removeRating(Product product){
        if(evaluations.containsKey(product)){
            product.removeEvaluation(evaluations.get(product));
        }
    }
    @Override
    public void displayActivityStat() {

    }
    public void follow(){
        // TODO
    }

    public void manageOrder(){
        // TODO
    }
    public void confirmOrderReception(String orderNumber){
        Order order = orders.get(orderNumber) ;
        if (order.isShipped() && !order.isDelivered()) {
            order.setDelivered(true);
            order.setDeliveryDate(Calendar.getInstance().getTime());
            System.out.println("Order confirmed. Delivery date: " + order.getDeliveryDate());
        } else {
            System.out.println("Cannot confirm order reception. The order is not shipped or is already confirmed.");
        }
    }
    public void complain(){
        // TODO
    }
    public void swapOrderItem(Product swapProduct){
        // TODO
    }

    public ReturnItem returnOrderItem(String orderNumber, OrderItem orderItem, int quantity){
        Order order = orders.get(orderNumber) ;
        // Calculate the difference in milliseconds
        long diffInMilliseconds = Math.abs(Calendar.getInstance().getTime().getTime() - order.getDeliveryDate().getTime());

        // Convert milliseconds to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);

        if (daysDifference < 30) System.out.println("La période de retour pour les articles est limitée à 30 jours à partir de la date de la commande.");
        else return new ReturnItem(orderItem.getProduct(),quantity,orderItem.getSeller());

        return null ;
    }

    public String getStatus(String orderNumber){
        Order order = orders.get(orderNumber) ;
        if(!order.isShipped()) return "En production";
        else if(order.isShipped()&&!order.isDelivered()) return "En livraison";
        return "Livré";
    }
}
