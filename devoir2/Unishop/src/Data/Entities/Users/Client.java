package Data.Entities.Users;

import Data.Entities.Order;
import Data.Entities.Products.Product;
import Data.Entities.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

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
}
