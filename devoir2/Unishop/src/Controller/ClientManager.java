package Controller;

import Data.Entities.Users.Client;
import Data.Entities.Users.User;

import java.util.InputMismatchException;
import java.util.Map;

public class ClientManager implements UserManager {

    @Override
    public boolean register(User user) throws InputMismatchException {
        return false;
    }

    @Override
    public boolean modifyProfile(User user, Map<String, String> newValToOption) throws InputMismatchException {
        return false;
    }
}
