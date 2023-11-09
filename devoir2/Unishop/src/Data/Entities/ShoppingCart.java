package Data.Entities;

import Data.Entities.Products.Product;

import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Product> cart;
    double total;
    int numberItems;
    int numberPoints;

    public ShoppingCart() {
        this.cart = new ArrayList<Product>();
        this.total = 0.0;
        this.numberItems = 0;
        this.numberPoints=0;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void add(Product product) {
        this.cart.add(product);
        total+=product.getPrice();
        numberItems++;
        numberPoints+=product.getPoints();
    }
    public void delete(Product product){
        cart.remove(product);
        total-=product.getPrice();
        numberItems--;
        numberPoints-=product.getPoints();
    }

    public double getTotal() {
        return total;
    }

    public int getNumberItems() {
        return numberItems;
    }
    public int getNumberPoints(){
        return numberPoints;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Product product:cart){
            sb.append(product.toString()).append("\n");
        }
        sb.append("Number of items : "+numberItems).append("\n");
        sb.append("Total cost : "+total).append("\n");
        sb.append("Total points : "+numberPoints).append("\n");
        return sb.toString();
    }
}
