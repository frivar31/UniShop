package Data.Entities.Users;

import Data.Entities.*;
import Data.Entities.Products.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * La classe Client représente un utilisateur du système qui peut effectuer des achats, évaluer des produits,
 * gérer son panier, suivre d'autres utilisateurs, etc.
 */
public class Client extends User {
    private final HashMap<String, Order> orders;
    private final HashMap<String, Return> returns;
    private String shipAddress;
    private ArrayList<ProductEvaluation> evaluations;
    private ShoppingCart shoppingCart;
    private int points;
    private HashSet<String> followers;
    private HashSet<String> following;
    private ArrayList<Integer> likedProduct;
    private ArrayList<String> likedSeller;
    /**
     * Constructeur pour créer une nouvelle instance de Client avec les informations de base.
     *
     * @param firstName    Le prénom du client.
     * @param lastName     Le nom de famille du client.
     * @param email        L'adresse e-mail du client.
     * @param pseudo       Le pseudonyme unique du client.
     * @param number       Le numéro associé au client.
     * @param shipAddress  L'adresse de livraison du client.
     * @param password     Le mot de passe associé au compte du client.
     */
    public Client(String firstName,
                  String lastName,
                  String email,
                  String pseudo,
                  Long number,
                  String shipAddress,
                  String password) {
        super(firstName, lastName, email, pseudo, number, password, new ArrayList<Ticket>());
        this.shipAddress = shipAddress;
        this.shoppingCart = new ShoppingCart();
        this.points = 0;
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
        this.orders = new HashMap<>();
        this.likedProduct = new ArrayList<>();
        this.returns = new HashMap<>();

    }
    /**
     * Constructeur pour la désérialisation de l'instance de Client à partir du format JSON.
     *
     * @param firstName     Le prénom du client.
     * @param lastName      Le nom de famille du client.
     * @param email         L'adresse e-mail du client.
     * @param pseudo        Le pseudonyme unique du client.
     * @param number        Le numéro associé au client.
     * @param password      Le mot de passe associé au compte du client.
     * @param cart          Le panier du client.
     * @param shipAddress   L'adresse de livraison du client.
     * @param orders        Les commandes effectuées par le client.
     * @param returns       Les retours effectués par le client.
     * @param points        Les points de récompense du client.
     * @param followers     Les utilisateurs qui suivent ce client.
     * @param following     Les utilisateurs que ce client suit.
     * @param evaluations   Les évaluations de produits effectuées par le client.
     * @param likedProduct  Les produits que le client a aimés.
     * @param likedSeller   Les vendeurs que le client a aimés.
     * @param tickets       Les tickets associés au compte du client.
     */

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
                  @JsonProperty("returns") HashMap<String, Return> returns,
                  @JsonProperty("points") int points,
                  @JsonProperty("followers") HashSet<String> followers,
                  @JsonProperty("following") HashSet<String> following,
                  @JsonProperty("evaluations") ArrayList<ProductEvaluation> evaluations,
                  @JsonProperty("likedProduct") ArrayList<Integer> likedProduct,
                  @JsonProperty("likedSeller") ArrayList<String> likedSeller,
                  @JsonProperty("tickets") ArrayList<Ticket> tickets) {
        super(firstName, lastName, email, pseudo, number, password, tickets);
        this.shipAddress = shipAddress;
        this.orders = orders;
        this.evaluations = evaluations;
        this.shoppingCart = cart;
        this.points = points;
        this.followers = followers;
        this.following = following;
        this.likedProduct = likedProduct;
        this.returns = returns;
        this.likedSeller = likedSeller;
    }
    /**
     * Obtient la liste des produits que le client a aimés.
     *
     * @return La liste des identifiants des produits aimés par le client.
     */
    public ArrayList<Integer> getLikedProduct() {
        return likedProduct;
    }

    public void setLikedProduct(ArrayList<Integer> likedProduct) {
        this.likedProduct = likedProduct;
    }

    public void addLikedProduct(int id) {
        likedProduct.add(id);
    }

    public HashSet<String> getFollowers() {
        return followers;
    }

    public void setFollowers(HashSet<String> followers) {
        this.followers = followers;
    }

    public HashSet<String> getFollowing() {
        return following;
    }

    public void setFollowing(HashSet<String> following) {
        this.following = following;
    }

    /**
     * Obtient la liste des vendeurs que le client a aimés.
     *
     * @return La liste des pseudonymes des vendeurs aimés par le client.
     */
    public ArrayList<String> getLikedSeller() {
        return likedSeller;
    }

    public void setLikedSeller(ArrayList<String> likedSeller) {
        this.likedSeller = likedSeller;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void removePoints(int points) {
        this.points -= points;
    }

    public HashMap<String, Order> getOrders() {
        return orders;
    }

    public HashMap<String, Return> getReturns() {
        return returns;
    }

    public Return getReturn(String returnNumber) {
        return returns.get(returnNumber);
    }

    public void addReturn(Return ret) {
        this.returns.put(ret.getOrderNumber(), ret);
    }

    public Order getOrder(String orderNumber) {
        return orders.get(orderNumber);
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

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Affiche une représentation textuelle des informations du client.
     *
     * @return Une chaîne de caractères représentant les informations du client.
     */
    @Override
    public String toString() {
        return "{" +
                "\n- firstName='" + this.getFirstName() + '\'' +
                "\n- lastName='" + this.getLastName() + '\'' +
                /*"\n- email='" + this.getEmail() + '\'' +*/
                "\n- pseudo='" + this.getPseudo() + '\'' +
                /*"\n- number=" + this.getNumber() +
                "\n- shipAddress='" + this.getShipAddress() + '\'' +*/
                "\n- followers='" + this.followers.size() + '\'' +
                "\n- following='" + this.following.size() + '\'' +
                "\n- points='" + this.getPoints() + '\'' +
                "\n}";
    }
    /**
     * Évalue un produit donné. Si l'utilisateur a déjà évalué le produit, un message est affiché.
     * @param product Le produit à évaluer.
     * @param evaluation L'évaluation du produit.
     */
    public void rateProduct(Product product, ProductEvaluation evaluation) {
        for (ProductEvaluation eval : evaluations) {
            if (eval.getProductId() == product.getId()) {
                System.out.println("Vous avez déja évalué ce produit");
                return;
            }
        }
        evaluations.add(evaluation);
        product.addEvaluation(evaluation);
    }
    /**
     * Supprime l'évaluation d'un produit. Si l'utilisateur n'a pas évalué le produit, un message est affiché.
     * @param product Le produit duquel l'évaluation doit être retirée.
     */
    public void removeRating(Product product) {
        for (ProductEvaluation eval : evaluations) {
            if (eval.getProductId() == product.getId()) {
                product.removeEvaluation(eval);
                evaluations.remove(eval);
                System.out.println("Evaluation retiré");
                return;
            }
        }
        System.out.println("Vous n'avez pas d'évaluation sur ce produit");
    }
    @Override
    public void displayActivityStat() {

    }
    public void manageOrder() {
        // TODO
    }
    /**
     * Confirme la réception d'une commande. Si la commande n'est pas expédiée ou est déjà confirmée, un message est affiché.
     * @param orderNumber Le numéro de commande à confirmer.
     */
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
    /**
     * Demande le retour d'un article de commande. La demande de retour est soumise à une période de 30 jours à partir de la date de la commande.
     * @param orderNumber Le numéro de commande concerné.
     * @param orderItem L'article de commande à retourner.
     * @param quantity La quantité à retourner.
     * @return Un objet ReturnItem représentant la demande de retour.
     */
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
    /**
     * Obtient le statut d'une commande en fonction de son numéro. Les statuts possibles sont "En production", "En livraison" ou "Livré".
     * @param orderNumber Le numéro de commande.
     * @return Le statut de la commande.
     */
    public String getStatus(String orderNumber) {
        Order order = orders.get(orderNumber);
        if (!order.isShipped()) return "En production";
        else if (order.isShipped() && !order.isDelivered()) return "En livraison";
        return "Livré";
    }
    /**
     * Permet à l'utilisateur de suivre un autre client. Si l'utilisateur suit déjà le client, un message est affiché.
     * Gagne également 5 points et informe l'autre client de ce suivi.
     * @param otherClient Le client à suivre.
     */
    public void follow(Client otherClient) {
        if (!following.contains(otherClient.getPseudo())) {
            following.add(otherClient.getPseudo());
            points += 5;
            otherClient.followedBy(this.getPseudo()); // Pour que les deux clients gagnent des points
        } else System.out.println("Vous suivez déja cette personne");
    }
    /**
     * Permet à l'utilisateur d'arrêter de suivre un autre client. Si l'utilisateur ne suit pas le client, un message est affiché.
     * Perd également 5 points et informe l'autre client de la cessation du suivi.
     * @param otherClient Le client à ne plus suivre.
     */
    public void unfollow(Client otherClient) {
        if (following.contains(otherClient.getPseudo())) {
            following.remove(otherClient.getPseudo());
            points -= 5;
            otherClient.unfollowedBy(this.getPseudo()); // Pour que les deux clients perdent des points
        } else System.out.println("Vous ne suivez pas cette personne");
    }

    /**
     * Notifie le client du suivi d'un autre client.
     *
     * @param follower Le pseudonyme du client qui suit ce client.
     *                Ajoute le suiveur à la liste des followers et ajoute des points au client.
     */
    private void followedBy(String follower) {
        followers.add(follower);
        points = +5;
    }
    /**
     * Notifie le client de l'arrêt du suivi par un autre client.
     *
     * @param unfollower Le pseudonyme du client qui ne suit plus ce client.
     *                   Retire le suiveur de la liste des followers et retire des points au client.
     */
    private void unfollowedBy(String unfollower) {
        followers.remove(unfollower);
        points -= 5;
    }
    public ArrayList<ProductEvaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(ArrayList<ProductEvaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public void addEvaluation(ProductEvaluation productEvaluation) {
        this.evaluations.add(productEvaluation);
    }
    /**
     * Vérifie si le client a déjà aimé un vendeur spécifique.
     *
     * @param pseudo Le pseudonyme du vendeur à vérifier.
     * @return True si le client a déjà aimé ce vendeur, sinon False.
     */
    public boolean alreadyLikedSeller(String pseudo) {
        return likedSeller.contains(pseudo);
    }
    /**
     * Ajoute un vendeur à la liste des vendeurs aimés par le client.
     *
     * @param pseudo Le pseudonyme du vendeur à ajouter.
     */
    public void addLikedSeller(String pseudo) {
        likedSeller.add(pseudo);
    }



}
