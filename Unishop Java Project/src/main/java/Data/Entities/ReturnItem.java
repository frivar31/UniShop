package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnItem {
    private Map<Integer,Integer> returnedProductIdToQuantity ;

    private Map<Integer,List<String>> productIdToReturnReasons;
    private int quantity;

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

    private Boolean shipped;
    private Boolean delivered;

    @JsonCreator
    public ReturnItem(@JsonProperty("quantity") int quantity,
                      @JsonProperty("seller") Seller seller) {
        this.returnedProductIdToQuantity = new HashMap<>();
        this.productIdToReturnReasons = new HashMap<>() ;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
