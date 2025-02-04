package fitnessapp.dao;

import fitnessapp.model.User;
import fitnessapp.model.UserDetails;

public interface UserDetailsDao {

    UserDetails getUserById(int userId);
    UserDetails createUserDetails(UserDetails userDetails);

}
