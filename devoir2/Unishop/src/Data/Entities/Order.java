package Data.Entities;

import Data.Entities.Products.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Order {

    private String orderNumber;
    private HashMap<Product,Integer> items;
    private Date orderDate;
    private Boolean delivered;
    private Boolean shipped;
    private Date shippedDate;
    private Date deliveryDate;
    private String address;

    public Order(String orderNumber, HashMap<Product,Integer> items, Date orderDate, Boolean delivered, Boolean shipped, Date shippedDate, Date deliveryDate,String address) {
        this.orderNumber = orderNumber;
        this.items = items;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.shipped = shipped;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
        this.address=address;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public HashMap<Product,Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<Product,Integer> items) {
        this.items = items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Boolean getShipped() {
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

        sb.append("Items:\n");
        for (Product product : items.keySet()) {
            sb.append("- ").append(product.toString(items.get(product))).append("\n");
        }

        return sb.toString();
    }
}
