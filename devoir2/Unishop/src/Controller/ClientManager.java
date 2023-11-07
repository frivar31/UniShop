package Controller;

import java.util.InputMismatchException;
import java.util.Map;

public class ClientManager implements UserManager {
    private Provider provider ;


    public boolean register(UserProfile userProfile) throws InputMismatchException {
        return false;
    }

    public boolean modifyProfile(UserProfile userProfile,
                                 Map<String, String> newValToOption) throws InputMismatchException {
        return false;
    }
}
