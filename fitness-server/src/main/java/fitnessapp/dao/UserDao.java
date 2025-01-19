package fitnessapp.dao;

import fitnessapp.model.RegisterUserDto;
import fitnessapp.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
