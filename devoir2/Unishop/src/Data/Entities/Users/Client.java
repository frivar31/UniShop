package Data.Entities.Users;

import Data.Entities.Catalog;
import Data.Entities.Order;
import Data.Entities.Products.Product;
import Data.Entities.ShoppingCart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class Client extends User{

    private String shipAddress;
    private List<Order> orders;
    private ShoppingCart shoppingCart;

    public Client(String firstName,
                  String lastName,
                  String email,
                  String pseudo,
                  Long number,
                  String shipAddress) {
        super(firstName,lastName,email,pseudo,number);
        this.shipAddress = shipAddress;
        this.shoppingCart=new ShoppingCart();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public String getShipAddress() {
        return this.shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public ShoppingCart getShoppingCart(){
        return this.shoppingCart;
    }
    public Order buy(String address) {
            // Generate a unique order ID
            String orderID = UUID.randomUUID().toString();

            // Create an order using the current cart and customer information
            orders.add(new Order(orderID,shoppingCart.getCart(), Calendar.getInstance().getTime(),false,false,null,null,address));

            // Update the inventory
            Catalog.update(shoppingCart.getCart());


            // Clear the cart after the purchase
            shoppingCart.clearCart();
        return orders.get(-1);
    }
}
