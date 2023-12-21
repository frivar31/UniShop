package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReturnItem extends OrderItem {

    @JsonCreator
    public ReturnItem(@JsonProperty("returnedProductId") int returnedProductId,
                     @JsonProperty("returnedQuantity") int returnedQuantity,
                     @JsonProperty("sellerPseudo") String sellerPseudo,
                      @JsonProperty("clientPseudo") String clientPseudo,
                      @JsonProperty("reason") String reason,
                      @JsonProperty("delivered") Boolean delivered,
                      @JsonProperty("shipped") Boolean shipped) {
        super(returnedProductId,returnedQuantity,sellerPseudo,clientPseudo,reason,delivered,shipped);
    }


    @Override
    public String toString() {
        Product product=Catalog.getProduct(getProductId());
        String state = "" ;
        if (!getShipped()) state = "En production" ;
        else if(getShipped()) state = "En livraison" ;
        else if (getDelivered()) state = "Livré" ;
        return "{" +
                "\n- titre='" + product.getTitle() + '\'' +
                "\n- quantité='" + getQuantity() + '\'' +
                "\n- id='" + getProductId() + '\'' +
                "\n- raison de retour='" + getReason() + '\'' +
                "\n- état='" + state + '\'' +
                "\n}";
    }
}
