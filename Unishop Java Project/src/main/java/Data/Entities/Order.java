package Data.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Order {

    private String orderNumber;
    private ArrayList<OrderItem> items;
    private Date orderDate;
    private Boolean delivered;
    private Boolean shipped;
    private Date shippedDate;
    private Date deliveryDate;
    private String address;

    @JsonCreator
    public Order(@JsonProperty("orderNumber") String orderNumber,
                 @JsonProperty("items") ArrayList<OrderItem> items,
                 @JsonProperty("orderDate") Date orderDate,
                 @JsonProperty("delivered") Boolean delivered,
                 @JsonProperty("shipped") Boolean shipped,
                 @JsonProperty("shippedDate") Date shippedDate,
                 @JsonProperty("deliveryDate") Date deliveryDate,
                 @JsonProperty("address") String address) {
        this.orderNumber = orderNumber;
        this.items = items;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.shipped = shipped;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
        this.address = address;
    }

    public String getAddress(){
        return address;
    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean containsProduct(int productId) {
        return items.stream().anyMatch(orderIt -> orderIt.getProductId() == productId) ;
    }

    public boolean containsQuantity(int productId, int quantity) {
        return items.stream().anyMatch(orderIt -> orderIt.getProductId() == productId && orderIt.getQuantity() >= quantity) ;
    }

    public OrderItem getItem(int productId) {
        for (OrderItem item : items) {
            if (item.getProductId() == productId) return item ;
        }
        return null ;
    }

    public void update(int productId,int returnQuantity) {
        for (OrderItem item : items) {
            if (item.getProductId() == productId) {
                int currQuantity = item.getQuantity() ;
                item.setQuantity(currQuantity-returnQuantity);
                return ;
            }
        }
    }
    @JsonIgnore
    public boolean isReturnable() {
        // Calculate the difference in milliseconds
        long diffInMilliseconds = Math.abs(Calendar.getInstance().getTime().getTime() - deliveryDate.getTime());
        // Convert milliseconds to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
        return daysDifference < 30 ;
    }

    public Boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {

        this.delivered = delivered;
        for(OrderItem item:items){
            item.setDelivered(true);
        }
    }

    public Boolean isShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("Order Number: ").append(orderNumber).append("\n");
        sb.append("Order Date: ").append(dateFormat.format(orderDate)).append("\n");
        sb.append("Delivered: ").append(delivered).append("\n");
        sb.append("Shipped: ").append(shipped).append("\n");

        if (shipped) {
            sb.append("Shipped Date: ").append(dateFormat.format(shippedDate)).append("\n");
        }

        if (delivered) {
            sb.append("Delivery Date: ").append(dateFormat.format(deliveryDate)).append("\n");
        }

        sb.append("Product: "+items);
        sb.append("\n");

        return sb.toString();
    }


}
