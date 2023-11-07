package Data.Entities.Users;

import Data.Entities.Order;

import java.util.List;

public class Client {

    private String shipAddress;
    private List<Order> orders;
    private String firstName;
    private String lastName;
    private String email;
    private String pseudo;
    private int number;

    public Client(String firstName,
                  String lastName,
                  String email,
                  String pseudo,
                  int number,
                  String shipAddress) {
        this.shipAddress = shipAddress;
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
}
