package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItem {
    private int productId;
    private int quantity;
    private String sellerPseudo;
    private String clientPseudo ;

    private String reason ;

    public String getClientPseudo() {
        return clientPseudo;
    }

    public void setClientPseudo(String clientPseudo) {
        this.clientPseudo = clientPseudo;
    }

    private Boolean shipped;

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    private Boolean delivered;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonCreator
    public OrderItem(@JsonProperty("productId") int productId,
                     @JsonProperty("quantity") int quantity,
                     @JsonProperty("sellerPseudo") String sellerPseudo,
                     @JsonProperty("clientPseudo") String clientPseudo,
                     @JsonProperty("reason") String reason,
                     @JsonProperty("delivered") Boolean delivered,
                     @JsonProperty("shipped") Boolean shipped) {
        this.productId = productId;
        this.quantity = quantity;
        this.sellerPseudo = sellerPseudo;
        this.clientPseudo = clientPseudo ;
        this.shipped = shipped ;
        this.delivered = delivered ;
        this.reason = reason ;
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