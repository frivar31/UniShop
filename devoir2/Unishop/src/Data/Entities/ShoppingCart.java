package Data.Entities;

import Data.Entities.Products.Product;

import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Product> cart;
    double total;
    int numberItems;

    public ShoppingCart() {
        this.cart = new ArrayList<Product>();
        this.total = 0.0;
        this.numberItems = 0;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void add(Product product) {
        this.cart.add(product);
        total+=product.getPrice();
        numberItems++;
    }
    public void delete(Product product){
        cart.remove(product);
        total-=product.getPrice();
        numberItems--;
    }

    public double getTotal() {
        return total;
    }

    public int getNumberItems() {
        return numberItems;
    }
}
