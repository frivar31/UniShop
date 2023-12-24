package Data.Entities.Users;
import Data.Entities.*;
import Data.Entities.Products.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * Classe représentant un vendeur dans le système.
 * Les vendeurs peuvent gérer leurs produits à vendre, suivre les commandes et gérer les retours.
 */
public class Seller extends User {
    @JsonIgnore
    private  final ArrayList<OrderItem> orderItems;
    private ArrayList<Product> products;
    @JsonIgnore
    private ArrayList<Ticket> tickets;
    /**
     * Constructeur de la classe Seller.
     * @param firstName Prénom du vendeur.
     * @param lastName Nom du vendeur.
     * @param email Adresse e-mail du vendeur.
     * @param returnItems Liste des articles à retourner.
     * @param pseudo Pseudo du vendeur.
     * @param number Numéro de téléphone du vendeur.
     * @param productsToSell Liste des produits à vendre.
     * @param password Mot de passe du vendeur.
     */
    @JsonCreator
    public Seller(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("email") String email,
                  @JsonProperty("pseudo") String pseudo,
                  @JsonProperty("number") Long number,
                  @JsonProperty("productsToSell") ArrayList<Product> productsToSell,
                  @JsonProperty("password") String password) {
        super(firstName, lastName, email, pseudo, number, password, new ArrayList<>());
        products = productsToSell;
        this.orderItems = new ArrayList<OrderItem>();
        this.tickets=new ArrayList<Ticket>();
    }
    /**
     * Constructeur alternatif de la classe Seller.
     * @param firstName Prénom du vendeur.
     * @param lastName Nom du vendeur.
     * @param email Adresse e-mail du vendeur.
     * @param pseudo Pseudo du vendeur.
     * @param number Numéro de téléphone du vendeur.
     * @param products Liste des produits à vendre.
     * @param password Mot de passe du vendeur.
     */
    public Seller(String firstName, String lastName, String email, String pseudo, long number, ArrayList<Product> products, String password) {
        super(firstName, lastName, email, pseudo, number, password, new ArrayList<Ticket>());
        this.orderItems = new ArrayList<>();
        this.products=products;
        this.tickets=new ArrayList<Ticket>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    /**
     * Ajoute un produit au catalogue du vendeur.
     * @param product Le produit à ajouter.
     */
    public void addProduct(Product product) {
        Catalog.catalogMap.put(product.getId(), new Object[]{product, this});
        products.add(product);
    }
    /**
     * Met à jour le catalogue du vendeur en ajoutant tous les produits à la carte.
     */
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

    /**
     * Ajoute un article de commande à la liste des articles de commande du vendeur.
     * @param orderItem L'article de commande à ajouter.
     */
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
    /**
     * Supprime un produit du catalogue du vendeur.
     * @param product Le produit à supprimer.
     * @return true si le produit a été supprimé avec succès, sinon false.
     */
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

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de l'objet Seller.
     * @return Une chaîne de caractères représentant l'objet Seller.
     */
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
    /**
     * Renvoie la liste des articles en cours de production du vendeur.
     * @return Une liste d'articles en cours de production.
     */
    @JsonIgnore
    public ArrayList<OrderItem> getInProduction(){
        ArrayList<OrderItem> inProd=new ArrayList<>();
        for(OrderItem item:orderItems){
            if(!item.isReturned() && !item.isShipped()) inProd.add(item);
        }
        return inProd;
    }
    /**
     * Renvoie la liste des articles en cours de livraison du vendeur.
     * @return Une liste d'articles en cours de livraison.
     */
    @JsonIgnore
    public ArrayList<OrderItem> getInShipping(){
        ArrayList<OrderItem> inShipping=new ArrayList<>();
        for(OrderItem item:orderItems){
            if(!item.isReturned() && item.isShipped()&&!item.isDelivered()) inShipping.add(item);
        }
        return inShipping;
    }


}
