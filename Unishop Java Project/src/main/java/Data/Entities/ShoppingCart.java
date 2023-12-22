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

    public ShoppingCart() {
        this.cart = new HashMap<>();
        this.total = 0.0;
        this.numberItems = 0;
        this.numberPoints = 0;
    }
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

    public HashMap<Integer, Integer> getCart() {
        return cart;
    }

    public void add(int id) {
        Product product=Catalog.getProduct(id);
        this.cart.put(id, cart.getOrDefault(id, 0) + 1);
        total += product.getPrice();
        numberItems++;
        numberPoints += (int) (product.getPrice()) * product.getPoints();
    }

    public void updateQuantity(int id, int quantity) {
        Product product=Catalog.getProduct(id);
        int delta = quantity - cart.get(id);
        total += delta * product.getPrice();
        numberItems += delta;
        numberPoints += (int) (product.getPrice()) * delta * product.getPoints();
        cart.replace(id, quantity);
    }

    public void deleteProduct(int id) {
        int qty = cart.get(id);
        Product product=Catalog.getProduct(id);
        total -= product.getPrice() * qty;
        numberItems -= qty;
        numberPoints -= (int) (product.getPrice()) * product.getPoints() * qty;
        cart.remove(product);
    }

    public double getTotal() {
        return total;
    }

    public long getNumberItems() {
        return numberItems;
    }

    public long getNumberPoints() {
        return numberPoints;
    }


    public void clearCart() {
        // Clear the cart after a successful purchase
        cart.clear();
        total = 0.0;
        numberItems = 0;
        numberPoints = 0;
    }

    public boolean containsItem(int id) {
        return cart.containsKey(id);
    }


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

    public String toString(Product product, int id) {
        return product.toString(cart.get(id));

    }

    public ArrayList<OrderItem> convertToOrderItems() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        for (int id : cart.keySet()) {
            Product product=Catalog.getProduct(id);
            int quantity = cart.get(id);
            OrderItem orderItem = new OrderItem(id, quantity, Catalog.getProductSeller(id).getPseudo(),"","",false,false,false);
            orderItems.add(orderItem);
        }

        return orderItems;
    }
}
