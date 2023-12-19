package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItem {
    private int productId;
    private int quantity;
    private String sellerPseudo;
    private Boolean shipped;
    private Boolean delivered;



    @JsonCreator
    public OrderItem(@JsonProperty("productId") int productId,
                     @JsonProperty("quantity") int quantity,
                     @JsonProperty("sellerPseudo") String sellerPseudo) {
        this.productId = productId;
        this.quantity = quantity;
        this.sellerPseudo = sellerPseudo;
    }

    public String getSellerPseudo() {
        return sellerPseudo;
    }

    public void setSellerPseudo(String SellerPseudo) {
        this.sellerPseudo = SellerPseudo;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int id) {
        this.productId = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        Product product=Catalog.getProduct(productId);
        return product.getTitle() + ", Quantity: " + quantity+ ", Id: "+product.getId();
    }
}