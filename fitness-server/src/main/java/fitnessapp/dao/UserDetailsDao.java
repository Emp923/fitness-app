package fitnessapp.dao;

import fitnessapp.model.User;
import fitnessapp.model.UserDetails;

import java.util.List;

public interface UserDetailsDao {

    List<UserDetails> getUserDetails();
    UserDetails getUserById(int userId);
    UserDetails createUserDetails(UserDetails userDetails);

}
