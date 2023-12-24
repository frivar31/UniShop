package Data.Entities;
import Data.Entities.Products.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingCart {
    private HashMap<Integer, Integer> cart;
    private double total;
    private long numberItems;
    private long numberPoints;
    /**
     * Constructeur par défaut de la classe ShoppingCart.
     */
    public ShoppingCart() {
        this.cart = new HashMap<>();
        this.total = 0.0;
        this.numberItems = 0;
        this.numberPoints = 0;
    }
    /**
     * Constructeur de la classe ShoppingCart.
     *
     * @param cart         Panier d'achats.
     * @param total        Coût total du panier.
     * @param numberItems  Nombre d'articles dans le panier.
     * @param numberPoints Nombre total de points attribués.
     */
    @JsonCreator
    public ShoppingCart(@JsonProperty("cart") HashMap<Integer, Integer> cart,
                        @JsonProperty("total") double total,
                        @JsonProperty("numberItems") long numberItems,
                        @JsonProperty("numberPoints") long numberPoints) {
        this.cart = cart;
        this.total = total;
        this.numberItems = numberItems;
        this.numberPoints = numberPoints;
    }

    /**
     * Retourne le panier d'achats.
     *
     * @return Le panier d'achats.
     */
    public HashMap<Integer, Integer> getCart() {
        return cart;
    }
    /**
     * Ajoute un produit au panier d'achats.
     *
     * @param id Identifiant du produit à ajouter.
     */
    public void add(int id) {
        Product product=Catalog.getProduct(id);
        this.cart.put(id, cart.getOrDefault(id, 0) + 1);
        total += product.getPrice();
        numberItems++;
        numberPoints += (int) (product.getPrice()) * product.getPoints();
    }
    /**
     * Met à jour la quantité d'un produit dans le panier d'achats.
     *
     * @param id       Identifiant du produit à mettre à jour.
     * @param quantity Nouvelle quantité du produit.
     */
    public void updateQuantity(int id, int quantity) {
        Product product=Catalog.getProduct(id);
        int delta = quantity - cart.get(id);
        total += delta * product.getPrice();
        numberItems += delta;
        numberPoints += (int) (product.getPrice()) * delta * product.getPoints();
        cart.replace(id, quantity);
    }
    /**
     * Supprime un produit du panier d'achats.
     *
     * @param id Identifiant du produit à supprimer.
     */
    public void deleteProduct(int id) {
        int qty = cart.get(id);
        Product product=Catalog.getProduct(id);
        total -= product.getPrice() * qty;
        numberItems -= qty;
        numberPoints -= (int) (product.getPrice()) * product.getPoints() * qty;
        cart.remove(product);
    }
    /**
     * Retourne le coût total du panier d'achats.
     *
     * @return Le coût total du panier d'achats.
     */
    public double getTotal() {
        return total;
    }
    /**
     * Retourne le nombre d'articles dans le panier d'achats.
     *
     * @return Le nombre d'articles dans le panier d'achats.
     */
    public long getNumberItems() {
        return numberItems;
    }
    /**
     * Retourne le nombre total de points attribués.
     *
     * @return Le nombre total de points attribués.
     */
    public long getNumberPoints() {
        return numberPoints;
    }
    /**
     * Vide le panier d'achats après un achat réussi.
     */
    public void clearCart() {
        // Clear the cart after a successful purchase
        cart.clear();
        total = 0.0;
        numberItems = 0;
        numberPoints = 0;
    }
    /**
     * Vérifie si un produit est présent dans le panier d'achats.
     *
     * @param id Identifiant du produit.
     * @return true si le produit est présent, false sinon.
     */
    public boolean containsItem(int id) {
        return cart.containsKey(id);
    }

    /**
     * Retourne une représentation textuelle du panier d'achats.
     *
     * @return Une chaîne de caractères représentant le panier d'achats.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int id : cart.keySet()) {
            Product product=Catalog.getProduct(id);
            sb.append(product.toString(cart.get(id))).append("\n");
        }
        sb.append("Number of items : ").append(numberItems).append("\n");
        sb.append("Total cost : ").append((double) Math.round(total * 100) / 100).append("$").append("\n");
        sb.append("Total points : ").append(numberPoints).append("\n");
        return sb.toString();
    }
    /**
     * Retourne une représentation textuelle d'un produit spécifique dans le panier d'achats.
     *
     * @param product Produit spécifique.
     * @param id      Identifiant du produit.
     * @return Une chaîne de caractères représentant le produit spécifique dans le panier d'achats.
     */
    public String toString(Product product, int id) {
        return product.toString(cart.get(id));

    }
    /**
     * Convertit le panier d'achats en une liste d'éléments de commande.
     *
     * @param orderNumber Numéro de commande associé au panier.
     * @param pseudo      Pseudo du client.
     * @return Une liste d'éléments de commande.
     */
    public ArrayList<OrderItem> convertToOrderItems(String orderNumber,String pseudo) {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        for (int id : cart.keySet()) {
            Product product=Catalog.getProduct(id);
            int quantity = cart.get(id);
            OrderItem orderItem = new OrderItem(id, quantity, Catalog.getProductSeller(id).getPseudo(),pseudo,"",false,false,false,null,false);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
