package Controller;

import Data.Entities.Users.User;

import java.util.InputMismatchException;
import java.util.Map;

public interface UserManager {
    public boolean register (User user)  throws InputMismatchException;

    public boolean modifyProfile(User user,
                                 Map<String,String> newValToOption) throws InputMismatchException ;


}
