package Data.Entities.Users;

public class Client implements User{

    private ClientProfile clientProfile ;
    public Client(ClientProfile clientProfile) {
        this.clientProfile = clientProfile;
    }

    @Override
    public UserProfile getProfile() {
        return this.clientProfile ;
    }
}
