package Data.Entities.Users;

import Data.Entities.Order;

import java.util.List;

public class Client extends User{

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
                  Long number,
                  String shipAddress) {
        super(firstName,lastName,email,pseudo,number);
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
