package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItem {
    private int productId;
    private int quantity;
    private String sellerPseudo;
    private String clientPseudo;
    private String reason;
    private Boolean shipped;
    private Boolean delivered;
    private boolean returned ;

    @JsonCreator
    public OrderItem(@JsonProperty("productId") int productId,
                     @JsonProperty("quantity") int quantity,
                     @JsonProperty("sellerPseudo") String sellerPseudo,
                     @JsonProperty("clientPseudo") String clientPseudo,
                     @JsonProperty("reason") String reason,
                     @JsonProperty("delivered") Boolean delivered,
                     @JsonProperty("shipped") Boolean shipped,
                     @JsonProperty("returned") Boolean returned) {
        this.productId = productId;
        this.quantity = quantity;
        this.sellerPseudo = sellerPseudo;
        this.clientPseudo = clientPseudo;
        this.shipped = shipped;
        this.delivered = delivered;
        this.reason = reason;
    }

    public String getClientPseudo() {
        return clientPseudo;
    }

    public void setClientPseudo(String clientPseudo) {
        this.clientPseudo = clientPseudo;
    }

    public Boolean isShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public Boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
        this.clientPseudo = clientPseudo ;
        this.shipped = shipped ;
        this.delivered = delivered ;
        this.reason = reason ;
        this.returned = returned ;
    }

    public String getSellerPseudo() {
        return sellerPseudo;
    }


    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        Product product=Catalog.getProduct(getProductId());
        String state = "" ;
        String returnState = "" ;
        if (!isShipped()) state = "En production" ;
        else if(isShipped()) state = "En livraison" ;
        if(isDelivered()) state = "Livré" ;
        if(isReturned()) returnState = "Retourné" ;
        return "{" +
                "\n- titre='" + product.getTitle() + '\'' +
                "\n- quantité='" + getQuantity() + '\'' +
                "\n- id='" + getProductId() + '\'' +
                "\n- état='" + state + '\'' +
                "\n- " + returnState + '\'' +
                "\n}";
    }
}