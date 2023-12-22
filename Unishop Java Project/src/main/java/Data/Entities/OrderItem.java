package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;

public class OrderItem {
    private final int productId;
    private final String sellerPseudo;
    private int quantity;
    private String clientPseudo;
    private String reason;
    private Boolean shipped;
    private Boolean delivered;
    private boolean returned;
    private Date shipDate;

    public Boolean getShipped() {
        return shipped;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    @JsonCreator
    public OrderItem(@JsonProperty("productId") int productId,
                     @JsonProperty("quantity") int quantity,
                     @JsonProperty("sellerPseudo") String sellerPseudo,
                     @JsonProperty("clientPseudo") String clientPseudo,
                     @JsonProperty("reason") String reason,
                     @JsonProperty("delivered") Boolean delivered,
                     @JsonProperty("shipped") Boolean shipped,
                     @JsonProperty("returned") Boolean returned,
                     @JsonProperty("shipDate") Date shipDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.sellerPseudo = sellerPseudo;
        this.clientPseudo = clientPseudo;
        this.shipped = shipped;
        this.delivered = delivered;
        this.reason = reason;
        this.shipDate = shipDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
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
        shipDate=Calendar.getInstance().getTime();
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
        this.clientPseudo = clientPseudo;
        this.shipped = shipped;
        this.delivered = delivered;
        this.reason = reason;
        this.returned = returned;
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
        Product product = Catalog.getProduct(getProductId());
        String state = "";
        String returnState = "";
        if (!isShipped()) state = "En production";
        else if (isShipped()) state = "En livraison";
        if (isDelivered()) state = "Livré";
        if (isReturned()) returnState = "\n- " + "retourné";
        return "{" +
                "\n- titre='" + product.getTitle() + '\'' +
                "\n- quantité='" + getQuantity() + '\'' +
                "\n- id='" + getProductId() + '\'' +
                returnState+
                "\n- " + shipDate +
                "\n}";
    }
}