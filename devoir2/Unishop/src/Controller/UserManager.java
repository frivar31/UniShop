package Controller;

import Data.Entities.Users.UserProfile;

import java.util.InputMismatchException;
import java.util.Map;

public interface UserManager {
    public boolean register (UserProfile userProfile)  throws InputMismatchException;

    public boolean modifyProfile(UserProfile userProfile,
                                 Map<String,String> newValToOption) throws InputMismatchException ;


}
