package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;

public class ReturnItem {
    private Product product;
    private int quantity;
    private Seller seller;
    private Boolean shipped;
    private Boolean delivered;

    public ReturnItem(Product product, int quantity, Seller seller) {
        this.product = product;
        this.quantity = quantity;
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product: " + product.getTitle() + ", Quantity: " + quantity;
    }
}
