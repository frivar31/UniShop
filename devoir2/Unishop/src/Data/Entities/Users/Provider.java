package Data.Entities.Users;

public class Provider implements User{

    private ProviderProfile providerProfile ;
    public Provider(ProviderProfile providerProfile) {
        this.providerProfile = providerProfile;
    }
    @Override
    public UserProfile getProfile() {
        return this.providerProfile ;
    }

}
