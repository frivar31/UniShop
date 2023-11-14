package Data.Entities;

import Data.Entities.Products.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class ShoppingCart {
    private HashMap<Product,Integer> cart;
    private double total;
    private int numberItems;
    private int numberPoints;

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

    public void delete(Product product) {
        cart.remove(product);
        total -= product.getPrice();
        numberItems--;
        numberPoints -= product.getPoints();
    }

    public double getTotal() {
        return total;
    }

    public int getNumberItems() {
        return numberItems;
    }

    public int getNumberPoints() {
        return numberPoints;
    }


    public void clearCart() {
        // Clear the cart after a successful purchase
        cart.clear();
        total = 0.0;
        numberItems = 0;
        numberPoints = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : cart.keySet()) {
            sb.append(product.toString(cart.get(product))).append("\n");
        }
        sb.append("Number of items : ").append(numberItems).append("\n");
        sb.append("Total cost : ").append(total).append("\n");
        sb.append("Total points : ").append(numberPoints).append("\n");
        return sb.toString();
    }
}
