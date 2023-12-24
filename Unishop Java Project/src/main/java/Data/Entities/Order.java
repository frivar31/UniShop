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
    private Boolean returned ;

    public Boolean isReturned() {
        return this.returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }


    @JsonCreator
    public Order(@JsonProperty("orderNumber") String orderNumber,
                 @JsonProperty("items") ArrayList<OrderItem> items,
                 @JsonProperty("orderDate") Date orderDate,
                 @JsonProperty("delivered") Boolean delivered,
                 @JsonProperty("shipped") Boolean shipped,
                 @JsonProperty("returned") Boolean returned,
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
        this.returned = returned ;
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
        int removePos = 0 ;
        for (int i = 0 ; i < items.size(); ++i) {
            if (items.get(i).getProductId() == productId) {
                int currQuantity = items.get(i).getQuantity() ;
                items.get(i).setQuantity(currQuantity-returnQuantity);
                if(items.get(i).getQuantity() == 0) {
                    removePos = i ;
                    break ;
                }
                else return ;
            }
        }
        items.remove(removePos) ;
    }
    @JsonIgnore
    public boolean isReturnable() {
        // Calculate the difference in milliseconds
        long diffInMilliseconds = Math.abs(Calendar.getInstance().getTime().getTime() - deliveryDate.getTime());
        // Convert milliseconds to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
        return daysDifference < 30  ;
    }





    public Boolean isDelivered() {
        for(OrderItem orderItem:items) if(!orderItem.isDelivered()) return false;
        return true;
    }
    @JsonIgnore
    public boolean isSignalable(){
        long diffInMilliseconds = Math.abs(Calendar.getInstance().getTime().getTime() - deliveryDate.getTime());
        // Convert milliseconds to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
        return daysDifference < 365 ;
    }

    public void setDelivered(Boolean delivered) {

        this.delivered = delivered;
        for(OrderItem item:items){
            item.setDelivered(true);
        }
    }

    public Boolean isShipped() {
        for(OrderItem orderItem:items) {
            if(!orderItem.isShipped()) return false;
        }
        this.shipped =  true ;
        //set ship date the max orderitem shipped date
        shippedDate = items.stream()
                .map(OrderItem::getShipDate)
                .max(Date::compareTo)
                .orElse(Calendar.getInstance().getTime());
        return this.shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
        for(OrderItem item:items){
            item.setShipped(true);
        }

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
        sb.append("Delivered: ").append(isDelivered()).append("\n");
        sb.append("Shipped: ").append(isShipped()).append("\n");

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

    public String  print() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("return Number: ").append(orderNumber).append("\n");
        sb.append("return Date: ").append(dateFormat.format(orderDate)).append("\n");
        sb.append("Delivered: ").append(isDelivered()).append("\n");
        sb.append("Shipped: ").append(isShipped()).append("\n");

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
