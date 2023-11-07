package Data.Entities.Users;

public class ClientProfile extends UserProfile{

    private String shipAddress ;
    public ClientProfile(String firstName,
                    String lastName,
                    String email,
                    String pseudo,
                    int number,
                    String shipAddress) {
        super(firstName, lastName, email, pseudo, number);
        this.shipAddress = shipAddress ;
    }

    public String getShipAddress() { return this.shipAddress ;}

    public void setShipAddress(String shipAddress) { this.shipAddress = shipAddress ;}
}
