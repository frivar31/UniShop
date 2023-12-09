package Data.Entities.Users;

import Data.Entities.*;
import Data.Entities.Products.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Client extends User {
    private String shipAddress;
    private HashMap<String, Order> orders;
    @JsonIgnore
    private HashMap<Product, ProductEvaluation> evaluations;
    private ShoppingCart shoppingCart;
    private int points;
    private HashSet<Client> followers;
    private HashSet<Client> following;

    public Client(String firstName,
                  String lastName,
                  String email,
                  String pseudo,
                  Long number,
                  String shipAddress,
                  String password) {
        super(firstName, lastName, email, pseudo, number, password);
        this.shipAddress = shipAddress;
        this.shoppingCart = new ShoppingCart();
        this.points = 0;
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
        orders = new HashMap<>();
    }
    @JsonCreator
    public Client(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("email") String email,
                  @JsonProperty("pseudo") String pseudo,
                  @JsonProperty("number") Long number,
                  @JsonProperty("password") String password,
                  @JsonProperty("cart") ShoppingCart cart,
                  @JsonProperty("shipAddress") String shipAddress,
                  @JsonProperty("orders") HashMap<String, Order> orders,
                  @JsonProperty("points") int points,
                  @JsonProperty("followers") HashSet<Client> followers,
                  @JsonProperty("following") HashSet<Client> following) {
        super(firstName, lastName, email, pseudo, number, password);
        this.shipAddress = shipAddress;
        this.orders = orders;
        this.evaluations = new HashMap<Product, ProductEvaluation>();
        this.shoppingCart = cart;
        this.points = points;
        this.followers = followers;
        this.following = following;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public HashMap<String, Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.put(order.getOrderNumber(), order);
    }

    public String getShipAddress() {
        return this.shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public Order buy(String address) {
        // Generate a unique order ID
        String orderID = UUID.randomUUID().toString();
        // Create an order using the current cart and customer information
        Order newOrder = new Order(orderID, shoppingCart.convertToOrderItems(), Calendar.getInstance().getTime(), false, false, null, null, address);
        orders.put(newOrder.getOrderNumber(), newOrder);
        points += (int) shoppingCart.getNumberPoints();

        // Update the inventory
        Catalog.update(shoppingCart.getCart());

        // Clear the cart after the purchase
        this.shoppingCart = new ShoppingCart();
        return newOrder;
    }

    @Override
    public String toString() {
        return "{" +
                "\n- firstName='" + this.getFirstName() + '\'' +
                "\n- lastName='" + this.getLastName() + '\'' +
                /*"\n- email='" + this.getEmail() + '\'' +*/
                "\n- pseudo='" + this.getPseudo() + '\'' +
                /*"\n- number=" + this.getNumber() +
                "\n- shipAddress='" + this.getShipAddress() + '\'' +
                "\n- followers='" + this.followers.size() + '\'' +
                "\n- following='" + this.following.size() + '\'' +*/
                "\n- points='" + this.getPoints() + '\'' +
                "\n}";
    }

    public void rateProduct(Product product, ProductEvaluation evaluation) {
        if (!evaluations.containsKey(product)) {
            evaluations.put(product, evaluation);
            product.addEvaluation(evaluation);
        }
    }

    public void removeRating(Product product) {
        if (evaluations.containsKey(product)) {
            product.removeEvaluation(evaluations.get(product));
        }
    }

    @Override
    public void displayActivityStat() {

    }

    public void follow() {
        // TODO
    }

    public void manageOrder() {
        // TODO
    }

    public void confirmOrderReception(String orderNumber) {
        Order order = orders.get(orderNumber);
        if (order.isShipped() && !order.isDelivered()) {
            order.setDelivered(true);
            order.setDeliveryDate(Calendar.getInstance().getTime());
            System.out.println("Order confirmed. Delivery date: " + order.getDeliveryDate());
        } else {
            System.out.println("Cannot confirm order reception. The order is not shipped or is already confirmed.");
        }
    }

    public void complain() {
        // TODO
    }

    public void swapOrderItem(Product swapProduct) {
        // TODO
    }

    public ReturnItem returnOrderItem(String orderNumber, OrderItem orderItem, int quantity) {
        Order order = orders.get(orderNumber);
        // Calculate the difference in milliseconds
        long diffInMilliseconds = Math.abs(Calendar.getInstance().getTime().getTime() - order.getDeliveryDate().getTime());

        // Convert milliseconds to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);

        if (daysDifference < 30)
            System.out.println("La période de retour pour les articles est limitée à 30 jours à partir de la date de la commande.");
        //else return new ReturnItem(orderItem.getProduct(), quantity, orderItem.getSeller());

        return null;
    }

    public String getStatus(String orderNumber) {
        Order order = orders.get(orderNumber);
        if (!order.isShipped()) return "En production";
        else if (order.isShipped() && !order.isDelivered()) return "En livraison";
        return "Livré";
    }

    public void follow(Client otherClient) {
        if (!following.contains(otherClient)) {
            following.add(otherClient);
            points += 5;
            otherClient.followedBy(this); // Pour que les deux clients gagnent des points
        } else System.out.println("Vous suivez déja cette personne");
    }

    public void unfollow(Client otherClient) {
        if (following.contains(otherClient)) {
            following.remove(otherClient);
            points -= 5;
            otherClient.unfollowedBy(this); // Pour que les deux clients perdent des points
        } else System.out.println("Vous ne suivez pas cette personne");
    }

    private void followedBy(Client follower) {
        followers.add(follower);
        points = +5;
    }

    private void unfollowedBy(Client unfollower) {
        followers.remove(unfollower);
        points -= 5;
    }

    public void displayPointsRanking() {
        if (!following.isEmpty()) {
            List<Client> sortedFollowing = following.stream()
                    .sorted()
                    .toList();
            int userRank = sortedFollowing.indexOf(this) + 1;
            System.out.println("Votre classement en points parmi les personnes que vous suivez :");
            for (int i = 0; i < sortedFollowing.size(); i++) {
                Client follower = sortedFollowing.get(i);
                System.out.println((i + 1) + ". " + follower.getPseudo() + " - Points : " + follower.getPoints());
            }

            System.out.println("Votre classement : " + userRank + " - Points : " + this.getPoints());
        } else System.out.println("Vous n'avez pas d'abonné donc vous êtes le premier ;)");

    }

    public HashMap<Product, ProductEvaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(HashMap<Product, ProductEvaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public void displayLikedProductsByFollowing() {
        for (Client followingClient : following) {
            System.out.println("Items liké par: " + followingClient.getPseudo());
            for (Product product : followingClient.getEvaluations().keySet()) {
                System.out.println(product);
            }
        }
    }

}
