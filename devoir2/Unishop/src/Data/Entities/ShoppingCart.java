package Data.Entities;

import Data.Entities.Products.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class ShoppingCart {
    private HashMap<Product,Integer> cart;
    private double total;
    private long numberItems;
    private long numberPoints;

    public ShoppingCart() {
        this.cart = new HashMap<>();
        this.total = 0.0;
        this.numberItems = 0;
        this.numberPoints = 0;
    }

    public HashMap<Product,Integer> getCart() {
        return cart;
    }

    public void add(Product product) {
        this.cart.put(product,cart.getOrDefault(product,0)+1);
        total += product.getPrice();
        numberItems++;
        numberPoints += product.getPoints();
    }

    public void updateQuantity(Product product,int quantity) {
        int delta=quantity-cart.get(product);
        total += delta*product.getPrice();
        numberItems+=delta;
        numberPoints += delta*product.getPoints();
        cart.replace(product,quantity);
    }
    public void deleteProduct(Product product){
        int qty=cart.get(product);
        total -= product.getPrice()*qty;
        numberItems-=qty;
        numberPoints -= product.getPoints()*qty;
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
    public boolean containsItem(int id){
        return cart.containsKey(Catalog.getProduct(id));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : cart.keySet()) {
            sb.append(product.toString(cart.get(product))).append("\n");
        }
        sb.append("Number of items : ").append(numberItems).append("\n");
        sb.append("Total cost : ").append((double)Math.round(total * 100) / 100).append("$").append("\n");
        sb.append("Total points : ").append(numberPoints).append("\n");
        return sb.toString();
    }
    public String toString(Product product) {
        return product.toString(cart.get(product));

    }
}
